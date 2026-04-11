package com.junemon.multiplatform_masterclass.articles

import com.junemon.multiplatform_masterclass.BaseViewModel
import com.junemon.multiplatform_masterclass.articles.common.DomainResult
import com.junemon.multiplatform_masterclass.articles.model.Article
import com.junemon.multiplatform_masterclass.articles.model.MockArticles
import kotlinx.coroutines.delay
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

    init {
        getArticle()
    }

    private fun getArticle() {
        scope.launch {
            delay(1500)
            _articleState.update {
                DomainResult.Data(MockArticles.mockArticles)
            }
        }
    }
}