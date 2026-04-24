package com.junemon.multiplatform_masterclass.core.di

import com.junemon.multiplatform_masterclass.createSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val settingsModule = module {
    single<Settings> { createSettings() }
}