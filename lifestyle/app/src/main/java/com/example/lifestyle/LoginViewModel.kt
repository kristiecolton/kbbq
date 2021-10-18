package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class LoginViewModel(application: Application?) : AndroidViewModel(application!!) {
    // A list that contains the first name, last name, and uuid of all users in the db
    private val mCustomListDataItems: MutableLiveData<List<CustomListDataItem>>
    private val mUserRepository: UserRepository

    init {
        mUserRepository = UserRepository.getInstance(application)!!
        mCustomListDataItems = mUserRepository.getCustomListDataItems()
    }

    fun getCustomListDataItems() : MutableLiveData<List<CustomListDataItem>> {
        return mCustomListDataItems
    }

}
