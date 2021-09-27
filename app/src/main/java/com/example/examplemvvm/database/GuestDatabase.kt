package com.example.examplemvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examplemvvm.BuildConfig
import com.example.examplemvvm.database.converter.Converter
import com.example.examplemvvm.database.dao.GuestDAO
import com.example.examplemvvm.model.Guest

@Database(entities = [Guest::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDao(): GuestDAO
}