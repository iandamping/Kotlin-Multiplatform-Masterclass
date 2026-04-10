package com.junemon.multiplatform_masterclass.android.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.junemon.multiplatform_masterclass.articles.ArticleViewModel
import com.junemon.multiplatform_masterclass.model.Article
import com.junemon.multiplatform_masterclass.model.DataResult

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel,
    deviceInfoClick: () -> Unit,
) {
    val articleData by viewModel.articleState.collectAsStateWithLifecycle()

    Column(modifier) {
        ArticleAppBar(deviceInfoClick = deviceInfoClick)

        when (val article = articleData) {
            is DataResult.Data<List<Article>> -> ArticleItem(
                modifier = modifier,
                articles = article.data
            )

            is DataResult.Error -> CenterItemContent(
                centerContent = {
                    Text(
                        text = article.message,
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                }
            )

            DataResult.Loading -> CenterItemContent(centerContent = {
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
    deviceInfoClick: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Text("Articles")
    }, actions = {
        IconButton(onClick = deviceInfoClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "About Device Button",
            )
        }
    })
}

@Composable
fun CenterItemContent(
    modifier: Modifier = Modifier,
    centerContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            centerContent.invoke()
        }
    )
}