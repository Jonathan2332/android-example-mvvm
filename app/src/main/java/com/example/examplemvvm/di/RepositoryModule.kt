package com.example.examplemvvm.di

import com.example.examplemvvm.database.repository.GuestRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        GuestRepository(dao = get())
    }
}