package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.LiveData


class Repository {
    private lateinit var mUidds: LiveData<List<String>>
    var db: UserRoomDatabase? = null
    var mUserDao:UserDao?=null
    lateinit var users:UserTable
    constructor(application: Application) {
        db = UserRoomDatabase.getInstance(application)

        if (db != null) {
             mUserDao = db!!.userDao()
            mUidds= mUserDao!!.getAllUUIDs()
        }

    }

    fun getAllUsers(): LiveData<List<String>> {
        return mUidds
    }
    fun getBmi(uuid: String): Float? {
        return mUserDao?.getBMI(uuid)
    }

    fun getOneUser(uuid:String): UserTable{
        users= mUserDao?.getUser(uuid)!!
        return users
    }

     fun insert(user: UserTable?) {
         mUserDao!!.insertAll(user)
    }



}





