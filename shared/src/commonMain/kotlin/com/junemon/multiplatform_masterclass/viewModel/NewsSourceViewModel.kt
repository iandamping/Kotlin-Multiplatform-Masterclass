package com.junemon.multiplatform_masterclass.viewModel

import com.junemon.multiplatform_masterclass.BaseViewModel
import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.common.DomainResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSource
import com.junemon.multiplatform_masterclass.core.domain.repository.NewsSourceRepository
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
class NewsSourceViewModel(private val repository: NewsSourceRepository) :
    BaseViewModel() {

    private val _isRefreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshState: StateFlow<Boolean> = _isRefreshState.asStateFlow()

    val newsSourcesState: StateFlow<DomainResult<List<NewsSource>>> = isRefreshState
        .flatMapLatest { isForce ->
            flow {
                emit(DomainResult.Loading)
                emit(
                    when (val data = repository.getNewsSource(isForceRefresh = isForce)) {
                        is DataResult.Data<List<NewsSource>> -> {
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
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = DomainResult.Loading
        )


    fun setRefreshState(isRefresh: Boolean) {
        _isRefreshState.update { isRefresh }
    }
}