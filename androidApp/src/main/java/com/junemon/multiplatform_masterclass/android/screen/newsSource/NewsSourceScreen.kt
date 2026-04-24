package com.junemon.multiplatform_masterclass.android.screen.newsSource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSource
import com.junemon.multiplatform_masterclass.viewModel.NewsSourceViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NewsSourceScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsSourceViewModel = getViewModel<NewsSourceViewModel>(),
    backClick: () -> Unit
) {
    val newsSourcesData by viewModel.newsSourcesState.collectAsStateWithLifecycle()
    val isRefresh by viewModel.isRefreshState.collectAsStateWithLifecycle()

    Column(modifier) {
        NewsSourceAppBar(backClick = backClick)

        when (val newsSources = newsSourcesData) {
            is DomainResult.Data<List<NewsSource>> -> {
                NewsSourceItem(
                    isRefresh = isRefresh,
                    newsSources = newsSources.data,
                    isRefreshing = { isRefresh ->
                        viewModel.setRefreshState(isRefresh)
                    }
                )
            }

            is DomainResult.Error -> CenterItemContent(
                centerContent = {
                    Text(
                        text = newsSources.message,
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
fun NewsSourceAppBar(
    modifier: Modifier = Modifier,
    backClick: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Text("News Sources")
    }, navigationIcon = {
        IconButton(onClick = backClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description"
            )
        }
    })
}
