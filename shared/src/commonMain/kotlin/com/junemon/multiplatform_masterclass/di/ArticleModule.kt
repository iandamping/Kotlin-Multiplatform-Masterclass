package com.junemon.multiplatform_masterclass.di

import com.junemon.multiplatform_masterclass.articles.data.ArticleRemoteDataSource
import com.junemon.multiplatform_masterclass.articles.data.ArticleRemoteDataSourceImpl
import com.junemon.multiplatform_masterclass.articles.data.ArticleRepository
import com.junemon.multiplatform_masterclass.articles.data.ArticleRepositoryImpl
import com.junemon.multiplatform_masterclass.articles.service.ArticleService
import com.junemon.multiplatform_masterclass.articles.service.ArticleServiceImpl
import org.koin.dsl.module

val articleModule = module {
    single<ArticleService> { ArticleServiceImpl(get()) }
    single<ArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
}