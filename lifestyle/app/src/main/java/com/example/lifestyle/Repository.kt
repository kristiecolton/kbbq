package com.example.lifestyle

import androidx.lifecycle.MutableLiveData

object Repository {

    var user: UserModel? = null

    fun addUser(user: UserModel, dbManager: DBManager): Boolean {
        if (dbManager.addUser(user)) {
            this.user = user
            return true
        }
        return false
    }

    fun updateUser(user: UserModel, dbManager: DBManager): Boolean {
        if (dbManager.updateUser(user)) {
            this.user = user
            return true
        }
        return false
    }

    fun getUserFromLocalDatabase(): UserModel? {
        return user
    }




}