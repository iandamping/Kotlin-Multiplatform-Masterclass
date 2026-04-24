package com.junemon.multiplatform_masterclass.android.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourceItem(
    modifier: Modifier = Modifier,
    isRefresh: Boolean,
    isRefreshing: (Boolean) -> Unit,
    newsSources: List<NewsSource>
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
                items(newsSources) { news ->
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = news.name, style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = news.desc, style = MaterialTheme.typography.bodyMedium,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${news.country} - ${news.language}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }

        })
}