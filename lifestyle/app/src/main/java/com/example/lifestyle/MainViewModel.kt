package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel(application: Application?) : AndroidViewModel(application!!) {
    // A list that contains the first name, last name, and uuid of all users in the db
    private val mUserRepository: UserRepository
    // private val m: MutableLiveData<List<CustomListDataItem>>

    init {
        mUserRepository = UserRepository.getInstance(application)!!
       //  mCustomListDataItems = mUserRepository.getCustomListDataItems()
    }

//    fun getCustomListDataItems() : MutableLiveData<List<CustomListDataItem>> {
//        return mCustomListDataItems
//    }

}
