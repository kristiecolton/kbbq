package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ViewModelUserRoom(application: Application) : AndroidViewModel(application!!)
{

    private var userRepository:UserRepository?=null

    init {
        userRepository = UserRepository(application)
    }


////
//    suspend fun deleteUser(user: RoomUserClass.User) {

//    fun getBmi(uuid: String):  Float? {
//        return  userRepository?.getBmi(uuid)
//    }
//
//    suspend fun deleteUser(user: UserTable) {

//        dao.delete(user)
//    }
}