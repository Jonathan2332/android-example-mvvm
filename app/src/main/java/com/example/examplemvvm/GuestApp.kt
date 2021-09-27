package com.example.examplemvvm

import android.app.Application
import com.example.examplemvvm.di.appModule
import com.example.examplemvvm.di.repositoryModule
import com.example.examplemvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GuestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GuestApp)

            modules(appModule, repositoryModule, viewModelModule)
        }
    }
}