package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class UserRepository {
    private lateinit var mUUIDs: LiveData<List<String>>
    lateinit var users:UserTable

    var db: UserRoomDatabase? = null
    var mUserDao:UserDao?=null

    companion object {
        private var instance: UserRepository? = null

        @Synchronized
        fun getInstance(application: Application?): UserRepository? {
            if (instance == null) {
                instance = UserRepository(application!!)
            }
            return instance
        }

    }

    constructor(application: Application) {
        db = UserRoomDatabase.getInstance(application)

        if (db != null) {

            // Populate all the data our app will need
            mUserDao = db!!.userDao()

        }

    }

    /* Methods for Login View Model */
    fun getCustomListDataItems(): LiveData<List<CustomListDataItem>> {
        return mUserDao!!.getCustomListDataItems()
    }

    /* Method for Create User Profile View Model */
    suspend fun insertAll(user: UserTable?) {
        mUserDao!!.insertAll(user)
    }

    /* Methods for Edit User Profile View Model */
    suspend fun updateUser(uuid: String, firstName: String, lastName : String, age : Int, sex : Int, feet : Int, inches : Int,  lbs : Int, city : String, country : String, recommendedDailyCalories : Int, isActive : Boolean, goalType : Int, lbsPerWeek : Int, bmi : Float, bmr : Int ) {
        mUserDao!!.updateUser(uuid, firstName, lastName, age, sex, feet, inches, lbs, city, country, recommendedDailyCalories, isActive, goalType, lbsPerWeek, bmi, bmr)
    }

    fun getBBMI(uuid: String): LiveData<Float?> {
        return mUserDao?.getBMI(uuid)!!
    }

    fun getRecommendedDailyCalories(uuid: String): LiveData<Int?> {
        return mUserDao?.getRecommendedDailyCalories(uuid)!!
    }

    fun getUser(uuid:String): LiveData<UserTable>{
        var user : LiveData<UserTable> = mUserDao?.getUser(uuid)!!
        return user
    }


}





