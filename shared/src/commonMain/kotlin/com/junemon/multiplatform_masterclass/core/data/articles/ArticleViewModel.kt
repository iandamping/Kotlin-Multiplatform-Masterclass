package com.junemon.multiplatform_masterclass.core.data.articles

import com.junemon.multiplatform_masterclass.BaseViewModel
import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.common.DomainResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.Article
import com.junemon.multiplatform_masterclass.core.data.articles.repository.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    private val _isRefreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshState: StateFlow<Boolean> = _isRefreshState.asStateFlow()

    val articleState: StateFlow<DomainResult<List<Article>>> = isRefreshState
        .flatMapLatest { isForce ->
            flow {
                emit(DomainResult.Loading)
                emit(
                    when (val data = repository.getArticles(isForceRefresh = isForce)) {
                        is DataResult.Data<List<Article>> -> {
                            setRefreshState(false)
                            DomainResult.Data(data.data)
                        }

                        is DataResult.Error -> {
                            setRefreshState(false)
                            DomainResult.Error(data.message)
                        }
                    }
                )
            }
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DomainResult.Loading
        )


    fun setRefreshState(isRefresh: Boolean) {
        _isRefreshState.update { isRefresh }
    }
}