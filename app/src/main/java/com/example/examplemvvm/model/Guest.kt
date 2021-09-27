package com.example.examplemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examplemvvm.constants.EGuestStatus

@Entity(tableName = "guest")
data class Guest(@PrimaryKey(autoGenerate = true) val id: Long? = null,
                 val name: String,
                 val presence: EGuestStatus)
