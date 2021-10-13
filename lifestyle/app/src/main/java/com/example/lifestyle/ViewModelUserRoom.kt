package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.*

class ViewModelUserRoom(application: Application) : AndroidViewModel(application!!)
{

    private var roomRepository:Repository?=null
    lateinit var  uuidList: LiveData<List<String>>

    init {
        roomRepository = Repository(application)
        uuidList= roomRepository!!.getAllUsers()
    }

      fun insertMultipleUsers(users: RoomUserClass.User) {
         roomRepository?.insert(users)
    }

     fun getAllUsers():  LiveData<List<String>> {
        return  uuidList
    }
    fun getBmi(uuid: String):  Int? {
        return  roomRepository?.getBmi(uuid)
    }
//
//    suspend fun deleteUser(user: RoomUserClass.User) {
//        dao.delete(user)
//    }
}