package com.example.examplemvvm.di

import com.example.examplemvvm.ui.form.FormViewModel
import com.example.examplemvvm.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        HomeViewModel(repository = get())
    }

    single {
        FormViewModel(repository = get())
    }
}