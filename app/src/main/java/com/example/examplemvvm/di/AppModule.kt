package com.example.examplemvvm.di

import androidx.room.Room
import com.example.examplemvvm.database.GuestDatabase
import org.koin.dsl.module

val appModule = module {

    //Database
    single {
        Room.databaseBuilder(get(), GuestDatabase::class.java, "guests.db").build()
    }

    //DAO
    single {
        get<GuestDatabase>().guestDao()
    }
}