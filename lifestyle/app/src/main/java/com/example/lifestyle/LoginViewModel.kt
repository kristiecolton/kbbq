package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class LoginViewModel(application: Application?) : AndroidViewModel(application!!) {
    // A list that contains the first name, last name, and uuid of all users in the db
    private var mCustomListDataItems: LiveData<List<CustomListDataItem>>? = null
    private val mUserRepository: UserRepository

    init {
        mUserRepository = UserRepository.getInstance(application)!!
    }

    fun getCustomListDataItems() : LiveData<List<CustomListDataItem>> {
        if (mCustomListDataItems == null) {
            mCustomListDataItems = mUserRepository.getCustomListDataItems()
        }
        return mCustomListDataItems!!
    }

}
