package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.LiveData

import android.os.AsyncTask





class Repository {


    private lateinit var mUidds: LiveData<List<String>>
    var db: RoomDBClass.UsersDatabase? = null
    var mDaoRoom:DAORoom?=null
    lateinit var users:RoomUserClass.User
    constructor(application: Application) {
        db = RoomDBClass.UsersDatabase.getInstance(application)

        if (db != null) {
             mDaoRoom = db!!.userDao()
            mUidds= mDaoRoom!!.getAllUIDDs()
        }

    }

    fun getAllUsers(): LiveData<List<String>> {
        return mUidds
    }
    fun getBmi(uuid: String): Int? {
        return mDaoRoom?.getBMI(uuid)
    }

    fun getOneUser(uuid:String): RoomUserClass.User {
        users= mDaoRoom?.getUser(uuid)!!
        return users
    }

     fun insert(user: RoomUserClass.User?) {
         mDaoRoom!!.insertAll(user)
    }



}





