package com.junemon.multiplatform_masterclass.core.di

import com.junemon.multiplatform_masterclass.core.data.local.source.NewsSourceLocalDataSource
import com.junemon.multiplatform_masterclass.core.data.local.source.NewsSourceLocalDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.remote.source.dataSource.NewsSourceRemoteDataSource
import com.junemon.multiplatform_masterclass.core.data.remote.source.dataSource.NewsSourceRemoteDataSourceImpl
import com.junemon.multiplatform_masterclass.core.data.remote.source.service.NewsSourceService
import com.junemon.multiplatform_masterclass.core.data.remote.source.service.NewsSourceServiceImpl
import com.junemon.multiplatform_masterclass.core.domain.repository.NewsSourceRepository
import com.junemon.multiplatform_masterclass.core.domain.repository.NewsSourceRepositoryImpl
import org.koin.dsl.module

val newsSourceModule = module {
    single<NewsSourceService> { NewsSourceServiceImpl(get()) }
    single<NewsSourceRemoteDataSource> { NewsSourceRemoteDataSourceImpl(get()) }
    single<NewsSourceLocalDataSource> { NewsSourceLocalDataSourceImpl(get(), get()) }
    single<NewsSourceRepository> { NewsSourceRepositoryImpl(get(), get()) }
}