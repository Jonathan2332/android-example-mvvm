package com.example.examplemvvm.database.dao

import androidx.room.*
import com.example.examplemvvm.model.Guest
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(guest: Guest): Long

    @Query("SELECT * FROM guest")
    fun getAll(): Flow<List<Guest>>
}