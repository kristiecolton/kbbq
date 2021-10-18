package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CreateUserProfileViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mUserRepository: UserRepository

    init {
        mUserRepository = UserRepository.getInstance(application)!!
    }

    fun insertAll(user: UserTable?) {
        viewModelScope.launch {
            mUserRepository.insertAll(user)
        }
    }

}
