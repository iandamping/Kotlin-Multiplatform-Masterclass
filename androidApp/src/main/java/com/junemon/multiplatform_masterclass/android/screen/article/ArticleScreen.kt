package com.junemon.multiplatform_masterclass.android.screen.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.junemon.multiplatform_masterclass.android.screen.common.CenterItemContent
import com.junemon.multiplatform_masterclass.core.data.common.DomainResult
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.Article
import com.junemon.multiplatform_masterclass.viewModel.ArticleViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = getViewModel<ArticleViewModel>(),
    newsSourceClick: () -> Unit,
    deviceInfoClick: () -> Unit,
) {
    val articleData by viewModel.articleState.collectAsStateWithLifecycle()
    val isRefresh by viewModel.isRefreshState.collectAsStateWithLifecycle()

    Column(modifier) {
        ArticleAppBar(newsSourceClick = newsSourceClick, deviceInfoClick = deviceInfoClick)

        when (val article = articleData) {
            is DomainResult.Data<List<Article>> -> ArticleItem(
                modifier = modifier,
                articles = article.data,
                isRefresh = isRefresh,
                isRefreshing = { isRefresh ->
                    viewModel.setRefreshState(isRefresh)
                }
            )

            is DomainResult.Error -> CenterItemContent(
                centerContent = {
                    Text(
                        text = article.message,
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                }
            )

            DomainResult.Loading -> CenterItemContent(centerContent = {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleAppBar(
    modifier: Modifier = Modifier,
    newsSourceClick: () -> Unit,
    deviceInfoClick: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Text("Articles")
    }, actions = {
        IconButton(onClick = newsSourceClick) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "News Sources"
            )
        }

        IconButton(onClick = deviceInfoClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "About Device Button",
            )
        }
    })
}

