package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StepCounterViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mUserRepository: UserRepository
    private var muuid : String = ""

    init {
        mUserRepository = UserRepository.getInstance(application)!!
    }

    fun setUUID(uuid:String) {
        muuid = uuid
    }


}