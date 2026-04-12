package com.junemon.multiplatform_masterclass.android.di

import com.junemon.multiplatform_masterclass.articles.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }
}