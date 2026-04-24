package com.junemon.multiplatform_masterclass.android.di

import com.junemon.multiplatform_masterclass.viewModel.ArticleViewModel
import com.junemon.multiplatform_masterclass.viewModel.NewsSourceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }
    viewModel { NewsSourceViewModel(get()) }
}