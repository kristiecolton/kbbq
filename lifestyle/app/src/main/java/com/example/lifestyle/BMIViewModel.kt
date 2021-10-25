package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BMIViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mUserRepository: UserRepository
    private var muuid : String = ""

    init {
        mUserRepository = UserRepository.getInstance(application)!!
    }

    fun setUUID(uuid:String) {
        muuid = uuid
    }

    fun getBMI() : LiveData<Float?> {
        return mUserRepository.getBBMI(muuid)
    }

}

