package com.junemon.multiplatform_masterclass.core.data.articles

import com.junemon.multiplatform_masterclass.BaseViewModel
import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.common.DomainResult
import com.junemon.multiplatform_masterclass.core.data.articles.repository.ArticleRepository
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    private val _articleState: MutableStateFlow<DomainResult<List<Article>>> =
        MutableStateFlow(DomainResult.Loading)

    val articleState: StateFlow<DomainResult<List<Article>>>
        get() = _articleState.asStateFlow()


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