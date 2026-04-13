package com.junemon.multiplatform_masterclass.android.di

import app.cash.sqldelight.db.SqlDriver
import com.junemon.multiplatform_masterclass.db.DatabaseDriverFactory
import com.junemon.multiplatform_masterclass.db.MultiplatformMasterclassDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    //MultiplatformMasterclassDatabase need SqlDriver
    single<MultiplatformMasterclassDatabase> { MultiplatformMasterclassDatabase(get()) }
}