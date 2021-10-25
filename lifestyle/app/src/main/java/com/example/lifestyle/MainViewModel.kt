package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mUserRepository: UserRepository
    private var mUser: LiveData<UserTable>? = null
    private var muuid : String = ""

    init {
        mUserRepository = UserRepository.getInstance(application)!!
    }

    fun setUUID(uuid:String) {
        muuid = uuid
    }

    fun getUser() : LiveData<UserTable> {
        if (mUser == null ) {
            mUser = mUserRepository.getUser(muuid)
        }
        return mUser!!
    }

    fun updateUser(uuid: String, firstName: String, lastName : String,  age : Int, sex : Int,  feet : Int,
                   inches : Int,  lbs : Int, city : String, country : String, isActive : Boolean,
                   goalType : Int, lbsPerWeek : Int) {
        viewModelScope.launch {
            // calcualte bmi
            val bmi : Float = HealthMetricUtils.calculateBMI(lbs, feet, inches)
            // calculate bmr
            val bmr : Int = HealthMetricUtils.calculateBMR(lbs, feet, inches, age, sex, isActive)
            // calculate recommended daily calories
            val recommendedDailyCalories : Int = HealthMetricUtils.calculateRecommendedDailyCalories(bmr, goalType, lbsPerWeek)

            mUserRepository.updateUser(
                uuid, firstName, lastName, age, sex, feet, inches, lbs, city, country,
                recommendedDailyCalories, isActive, goalType, lbsPerWeek, bmi, bmr
            )
        }
    }


}
