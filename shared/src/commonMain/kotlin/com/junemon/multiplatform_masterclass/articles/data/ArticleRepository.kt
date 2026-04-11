package com.junemon.multiplatform_masterclass.articles.data

import com.junemon.multiplatform_masterclass.articles.common.DataResult
import com.junemon.multiplatform_masterclass.articles.model.Article
import com.junemon.multiplatform_masterclass.articles.model.ArticleData
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

class ArticleRepository(private val remoteData: ArticleRemoteDataSource) {

    suspend fun getArticles(): DataResult<List<Article>> {
        return when (val remoteData = remoteData.getArticles()) {
            is DataResult.Data<List<ArticleData>> -> DataResult.Data(remoteData.data.map { article ->
                Article(
                    title = article.title,
                    description = article.desc ?: "Click to find out more",
                    date = getDaysAgoString(article.date),
                    imageUrl = article.imageUrl
                        ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
                )
            })

            is DataResult.Error -> DataResult.Error(remoteData.message)
        }
    }

    private fun getDaysAgoString(date: String): String {
        val systemTZ = TimeZone.currentSystemDefault()

        // 1. Ambil tanggal hari ini berdasarkan zona waktu sistem
        val today = Clock.System.todayIn(systemTZ)

        // 2. Parsing input string ke Instant, lalu konversi ke LocalDate
        // Pastikan string input dalam format ISO 8601 (contoh: 2023-10-27T10:00:00Z)
        val targetDate = Instant.parse(date)
            .toLocalDateTime(systemTZ)
            .date

        // 3. Hitung selisih hari (today - targetDate)
        // daysUntil menghasilkan nilai negatif jika targetDate di masa lalu
        val days = targetDate.daysUntil(today)

        return when {
            days > 1 -> "$days days ago"
            days == 1 -> "Yesterday"
            days == 0 -> "Today"
            else -> "In the future" // Untuk menangani jika tanggal lebih dari hari ini
        }
    }
}