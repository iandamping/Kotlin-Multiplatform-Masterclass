package com.junemon.multiplatform_masterclass.core.di

import com.junemon.multiplatform_masterclass.core.data.local.article.ArticleLocalDataSource
import com.junemon.multiplatform_masterclass.core.data.local.article.ArticleLocalDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.remote.article.dataSource.ArticleRemoteDataSource
import com.junemon.multiplatform_masterclass.core.data.remote.article.dataSource.ArticleRemoteDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.remote.article.service.ArticleService
import com.junemon.multiplatform_masterclass.core.data.remote.article.service.ArticleServiceImpl
import com.junemon.multiplatform_masterclass.core.domain.repository.ArticleRepository
import com.junemon.multiplatform_masterclass.core.domain.repository.ArticleRepositoryImpl
import org.koin.dsl.module

val articleModule = module {
    single<ArticleService> { ArticleServiceImpl(get()) }
    single<ArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(get()) }
    single<ArticleLocalDataSource> { ArticleLocalDataSourceImpl(database = get(), sharedPreferences = get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get(), get()) }
}