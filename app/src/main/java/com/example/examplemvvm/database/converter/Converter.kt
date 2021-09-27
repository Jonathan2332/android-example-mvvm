package com.example.examplemvvm.database.converter

import androidx.room.TypeConverter
import com.example.examplemvvm.constants.EGuestStatus

class Converter {

    @TypeConverter
    fun fromGuestStatus(guestStatus: EGuestStatus) = guestStatus.status

    @TypeConverter
    fun toGuestStatus(status: String) = EGuestStatus.getStatus(status)
}