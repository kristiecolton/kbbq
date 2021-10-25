package com.example.lifestyle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface UserDao {
    //I think this line would work for just one user as well
    // or multiple hence the vararg
    //you need to make a class ROOMDBMANGER.user which requires all the columns
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: UserTable?)

    //uses a primary key UUID to find user, returns the number of columns changed
    //can take multiple users for deletion needs UUIDS
    @Delete
    fun delete(vararg user: UserTable)

    //uses a primary key UUID to find user, returns the number of columns changed
//    @Update
//    fun updateUser(vararg users: UserTable)

    @Query("UPDATE user_table SET first_name = :firstName, last_name = :lastName, age = :age, sex = :sex, feet= :feet, inches = :inches, lbs = :lbs, city = :city, country = :country, recommended_daily_calories = :recommendedDailyCalories, is_active = :isActive, goal_type = :goalType, lbs_per_week = :lbsPerWeek, bmi = :bmi, bmr = :bmr WHERE uuid LIKE :uuid ")
    suspend fun updateUser(uuid: String, firstName: String, lastName : String, age : Int, sex : Int, feet : Int, inches : Int,  lbs : Int, city : String, country : String, recommendedDailyCalories : Int, isActive : Boolean, goalType : Int, lbsPerWeek : Int, bmi : Float, bmr : Int ): Int

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserTable>

    @Query("SELECT * FROM user_table WHERE UUID = :uuid")
    fun getUser(uuid: String): LiveData<UserTable>

    @Query("SELECT UUID FROM user_table")
    fun getAllUUIDs(): LiveData<List<String>>

    // A method to return the uuid, first name, and last name of all our users
    // (to be used in the recycle view oh the login activity)
    @Query("SELECT UUID, FIRST_NAME, LAST_NAME FROM user_table")
    fun getCustomListDataItems(): LiveData<List<CustomListDataItem>>

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
    fun getBMI(uuid: String): LiveData<Float?>

    //Same problem as before where i returned a string in the DBgetter
    @Query("SELECT recommended_daily_calories FROM user_table WHERE UUID = :uuid")
    fun getRecommendedDailyCalories(uuid: String): LiveData<Int?>

}


