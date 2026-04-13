package com.junemon.multiplatform_masterclass.android

import android.app.Application
import com.junemon.multiplatform_masterclass.android.di.databaseModule
import com.junemon.multiplatform_masterclass.android.di.viewModelModule
import com.junemon.multiplatform_masterclass.core.di.sharedCommonMainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MultiplatformMasterclassApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MultiplatformMasterclassApp)
            loadKoinModules(sharedCommonMainModules + viewModelModule + databaseModule)
        }
    }
}