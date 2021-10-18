package com.example.lifestyle

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class UserRepository {
    private lateinit var mUUIDs: LiveData<List<String>>
    lateinit var users:UserTable

    var db: UserRoomDatabase? = null
    var mUserDao:UserDao?=null


    /* Login View Model Data */
    // private var mCustomListData = MutableLiveData<CustomListData>()
    private var mCustomListDataItems = MutableLiveData<List<CustomListDataItem>>()

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

            // Convert LiveData object to MutableLiveData object (the DAO cannot return Mutable Live
            // Data, so we must manually cast it to Mutable Live Data in the Repository
            var customListDataItems : LiveData<List<CustomListDataItem>> = mUserDao!!.getCustomListDataItems()
            mCustomListDataItems = MutableLiveData<List<CustomListDataItem>>(customListDataItems.value)
            // Load all the data we'll need for the various view models

//            // Get a list of names to create a CustomListData object (used in the Login View Model)
//            val uuids : ArrayList<String> = mUserDao!!.getAllUUIDs().toCollection(ArrayList())
//            var names : ArrayList<String> = ArrayList()
//
//            uuids.forEach{
//                val firstName : String = mUserDao!!.getFirstName(it)
//                val lastName : String = mUserDao!!.getLastName(it)
//                val fullName = firstName + " " + lastName
//
//                names.add(fullName)
//
//            }
            // mCustomListData = CustomListData(names, uuids)
//            mCustomListData = MutableLiveData<CustomListData>(CustomListData(names, uuids))

        }

    }

    /* Methods for Login View Model */

//    fun getAllUUIDs(): LiveData<List<String>> {
//        return mUUIDs
//    }

    fun getCustomListDataItems(): MutableLiveData<List<CustomListDataItem>> {
        return mCustomListDataItems
    }

    fun getBmi(uuid: String): Float? {
        return mUserDao?.getBMI(uuid)
    }

    fun getOneUser(uuid:String): UserTable{
        users= mUserDao?.getUser(uuid)!!
        return users
    }

     fun insert(user: UserTable?) {
         mUserDao!!.insertAll(user)
    }



}





