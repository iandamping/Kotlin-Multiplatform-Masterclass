package com.junemon.multiplatform_masterclass.core.di

import com.junemon.multiplatform_masterclass.core.data.articles.local.dataSource.ArticleLocalDataSource
import com.junemon.multiplatform_masterclass.core.data.articles.local.dataSource.ArticleLocalDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.articles.remote.dataSource.ArticleRemoteDataSource
import com.junemon.multiplatform_masterclass.core.data.articles.remote.dataSource.ArticleRemoteDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.articles.repository.ArticleRepository
import com.junemon.multiplatform_masterclass.core.data.articles.repository.ArticleRepositoryImpl
import com.junemon.multiplatform_masterclass.core.data.articles.remote.service.ArticleService
import com.junemon.multiplatform_masterclass.core.data.articles.remote.service.ArticleServiceImpl
import org.koin.dsl.module

val articleModule = module {
    single<ArticleService> { ArticleServiceImpl(get()) }
    single<ArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(get()) }
    single<ArticleLocalDataSource> { ArticleLocalDataSourceImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get(),get()) }
}