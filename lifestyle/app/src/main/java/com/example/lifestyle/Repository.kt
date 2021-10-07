package com.example.lifestyle

import android.util.Log
import androidx.lifecycle.MutableLiveData

object Repository {

    private lateinit var user: UserModel

    fun addUser(user: UserModel, dbManager: DBManager): Boolean {
        if (dbManager.addUser(user)) {
            this.user = user
            return true
        }
        return false
    }

    fun updateUser(user: UserModel, dbManager: DBManager): Boolean {
        if (dbManager.updateUser(user)) {

            // get user from database after it has been updated
            this.user = dbManager.getUser(user.uuid)

            return true
        }
        return false
    }

    fun getUserFromLocalDatabase(): UserModel? {
        return user
    }




}