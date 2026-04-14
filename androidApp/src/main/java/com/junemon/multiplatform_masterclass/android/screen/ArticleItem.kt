@file:OptIn(ExperimentalMaterial3Api::class)

package com.junemon.multiplatform_masterclass.android.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.junemon.multiplatform_masterclass.android.R
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.Article

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    isRefresh: Boolean,
    isRefreshing: (Boolean) -> Unit,
    articles: List<Article>
) {

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefresh,
        onRefresh = {
            isRefreshing(true)
        },
        content = {
            LazyColumn(
                modifier = modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(articles) { article ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.placeholder_image),
                        error = painterResource(id = R.drawable.ic_no_data),
                        contentDescription = stringResource(R.string.article_image),
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = article.title, style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = article.description, style = MaterialTheme.typography.bodyMedium,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = article.date, style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    )
}