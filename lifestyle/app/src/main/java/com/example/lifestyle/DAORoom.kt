package com.example.lifestyle

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAORoom {


//    //I think this line would work for just one user as well
//    // or multiple hence the vararg
//    //you need to make a class ROOMDBMANGER.user which requires all the columns
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     fun insertAll(vararg users: RoomUserClass.User?)
//
//    //uses a primary key UUID to find user returns the number of columns changed
//    //can take multiple users for deletion needs UUIDS
//    @Delete
//    fun delete(vararg user: RoomUserClass.User)
//
//    //uses a primary key UUID to find user returns the number of columns changed
//    @Update
//    fun updateUsers(vararg users: RoomUserClass.User)
//
//    @Query("SELECT * FROM user")
//    fun getAllUsers(): List<RoomUserClass.User>
//
//    @Query("SELECT * FROM user WHERE UUID = :uuid")
//    fun getUser(uuid: String): RoomUserClass.User
//
//    @Query("SELECT UUID FROM user")
//    fun getAllUIDDs(): LiveData<List<String>>
//
//    @Query("SELECT LAST_NAME FROM user WHERE UUID = :uuid")
//    fun getLastName(uuid: String): String
//
//    @Query("SELECT PROFILE_PICTURE FROM user WHERE UUID = :uuid")
//    fun getProfilePictureURI(uuid: String): String?
//
//
//    //I returned a string in the other one this may cause problems
//    @Query("SELECT RECOMMENDED_DAILY_CALORIES FROM user WHERE UUID = :uuid")
//    fun getCals(uuid: String): Int?
//
//
//    @Query("SELECT FIRST_NAME FROM user WHERE UUID = :uuid")
//    fun getFirstName(uuid: String): String
//
//
////    Same problem as before where i returned a string in the DBgetter
//    @Query("SELECT BMI FROM user WHERE UUID = :uuid")
//    fun getBMI(uuid: String): Float?

}


