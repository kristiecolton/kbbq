package com.example.lifestyle

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    //I think this line would work for just one user as well
    // or multiple hence the vararg
    //you need to make a class ROOMDBMANGER.user which requires all the columns
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(vararg users: UserTable?)

    //uses a primary key UUID to find user, returns the number of columns changed
    //can take multiple users for deletion needs UUIDS
    @Delete
    fun delete(vararg user: UserTable)

    //uses a primary key UUID to find user, returns the number of columns changed
    @Update
    fun updateUser(vararg users: UserTable)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserTable>

    @Query("SELECT * FROM user_table WHERE UUID = :uuid")
    fun getUser(uuid: String): UserTable

    @Query("SELECT UUID FROM user_table")
    fun getAllUUIDs(): LiveData<List<String>>

    @Query("SELECT LAST_NAME FROM user_table WHERE UUID = :uuid")
    fun getLastName(uuid: String): String

    @Query("SELECT PROFILE_PICTURE FROM user_table WHERE UUID = :uuid")
    fun getProfilePictureURI(uuid: String): String?

    //I returned a string in the other one this may cause problems
    @Query("SELECT RECOMMENDED_DAILY_CALORIES FROM user_table WHERE UUID = :uuid")
    fun getCals(uuid: String): Int?


    @Query("SELECT FIRST_NAME FROM user_table WHERE UUID = :uuid")
    fun getFirstName(uuid: String): String


    //Same problem as before where i returned a string in the DBgetter
    @Query("SELECT BMI FROM user_table WHERE UUID = :uuid")
    fun getBMI(uuid: String): Float?

}


