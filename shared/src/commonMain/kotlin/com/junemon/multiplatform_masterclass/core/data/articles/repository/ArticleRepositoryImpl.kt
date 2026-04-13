package com.junemon.multiplatform_masterclass.core.data.articles.repository

import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.local.dataSource.ArticleLocalDataSource
import com.junemon.multiplatform_masterclass.core.data.articles.remote.dataSource.ArticleRemoteDataSource
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.Article
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticleData
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Duration.Companion.hours

class ArticleRepositoryImpl(
    private val remoteData: ArticleRemoteDataSource,
    private val localData: ArticleLocalDataSource
) : ArticleRepository {

    private val databaseTimeout = 24.hours.inWholeMilliseconds

    private fun isLocalDataExpired(): Boolean {
        val count = localData.getArticlesCount()
        val lastSync = localData.getLastUpdate()

        return count == 0L || (Clock.System.now()
            .toEpochMilliseconds() - lastSync) > databaseTimeout
    }

    override suspend fun getArticles(): DataResult<List<Article>> {
        return if (isLocalDataExpired()) {
            when (val remoteData = remoteData.getArticles()) {
                is DataResult.Data<List<ArticleData>> -> {
                    localData.insertArticles(remoteData.data)
                    localData.setLastUpdate(Clock.System.now().toEpochMilliseconds())

                    DataResult.Data(articlesMapper(localData.getArticles()))
                }

                is DataResult.Error -> DataResult.Error(remoteData.message)
            }
        } else {
            DataResult.Data(articlesMapper(localData.getArticles()))
        }
    }

    private fun articlesMapper(rawData: List<ArticleData>): List<Article> {
        return rawData.map { article ->
            Article(
                title = article.title,
                description = article.desc ?: "Click to find out more",
                date = getDaysAgoString(article.date),
                imageUrl = article.imageUrl
                    ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
            )
        }
    }

    private fun getDaysAgoString(date: String): String {
        val systemTZ = TimeZone.currentSystemDefault()

        // 1.Get today's date based on system time zone
        val today = Clock.System.todayIn(systemTZ)

        // 2.Parse the input string to Instant, then convert to LocalDate, M
        // Ensure the input string is in ISO 8601 format (e.g., 2023-10-27T10:00:00Z).
        val targetDate = Instant.parse(date)
            .toLocalDateTime(systemTZ)
            .date

        // 3.Calculate the difference in days (today - targetDate)
        // daysUntil returns a negative value if the target Date is in the past.
        val days = targetDate.daysUntil(today)

        return when {
            days > 1 -> "$days days ago"
            days == 1 -> "Yesterday"
            days == 0 -> "Today"
            else -> "In the future" // Untuk menangani jika tanggal lebih dari hari ini
        }
    }
}

