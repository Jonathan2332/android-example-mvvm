package com.example.examplemvvm.database.repository

import com.example.examplemvvm.database.dao.GuestDAO
import com.example.examplemvvm.model.Guest

class GuestRepository(private val dao: GuestDAO) {

    val getAll = dao.getAll()

    suspend fun insert(guest: Guest) = dao.insert(guest)
}