package com.junemon.multiplatform_masterclass.articles

import com.junemon.multiplatform_masterclass.BaseViewModel
import com.junemon.multiplatform_masterclass.articles.common.DataResult
import com.junemon.multiplatform_masterclass.articles.common.DomainResult
import com.junemon.multiplatform_masterclass.articles.data.ArticleRepository
import com.junemon.multiplatform_masterclass.articles.data.DataProvider.provideArticleRemoteDataSource
import com.junemon.multiplatform_masterclass.articles.data.DataProvider.provideArticleRepository
import com.junemon.multiplatform_masterclass.articles.data.DataProvider.provideHttpClient
import com.junemon.multiplatform_masterclass.articles.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel : BaseViewModel() {

    private val _articleState: MutableStateFlow<DomainResult<List<Article>>> =
        MutableStateFlow(DomainResult.Loading)

    val articleState: StateFlow<DomainResult<List<Article>>>
        get() = _articleState.asStateFlow()

    private val repository: ArticleRepository = provideArticleRepository(
        provideArticleRemoteDataSource(
            provideHttpClient()
        )
    )

    init {
        getArticle()
    }

    private fun getArticle() {
        scope.launch {
            when (val data = repository.getArticles()) {
                is DataResult.Data<List<Article>> -> _articleState.update {
                    DomainResult.Data(data.data)
                }

                is DataResult.Error -> _articleState.update {
                    DomainResult.Error(data.message)
                }
            }
        }
    }


}