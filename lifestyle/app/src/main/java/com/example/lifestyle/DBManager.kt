package com.example.lifestyle

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.Nullable
/* A class for managing the user database  */
class DBManager : SQLiteOpenHelper  {
    /* Static constants - for convenience */
    public final val USER_TABLE : String = "USER";
    public final val COLUMN_UUID : String = "UUID";
    public final val COLUMN_FIRST_NAME : String = "FIRST_NAME";
    public final val COLUMN_LAST_NAME : String = "LAST_NAME";
    public final val COLUMN_AGE : String = "AGE";
    public final val COLUMN_SEX : String = "SEX";
    public final val COLUMN_FEET : String = "FEET";
    public final val COLUMN_INCHES : String = "INCHES";
    public final val COLUMN_LBS : String = "LBS";
    public final val COLUMN_CITY : String = "CITY";
    public final val COLUMN_COUNTRY : String = "COUNTRY";
    public final val COLUMN_PROFILE_PICTURE : String = "PROFILE_PICTURE";
    public final val COLUMN_BACKGROUND_PICTURE : String = "BACKGROUND_PICTURE";
    public final val COLUMN_GOAL_TYPE : String = "GOAL_TYPE";
    public final val COLUMN_LBS_PER_WEEK : String = "LBS_PER_WEEK";
    public final val COLUMN_RECOMMENDED_DAILY_CALORIES : String = "RECOMMENDED_DAILY_CALORIES";
    public final val COLUMN_IS_ACTIVE : String = "IS_ACTIVE";
    public final val COLUMN_BMR : String = "BMR";
    public final val COLUMN_BMI : String = "BMI";

    /* Class constructor- creates a database called "user.db" */
    constructor(@Nullable context: Context) : super(context, "user.db",null, 1) {

    }

    /* Method to create a table inside our database. This method only gets called once after the
     creation of the database */
    override fun onCreate(db: SQLiteDatabase?) {
        var createTableStatement : String = "CREATE TABLE `USER` (\n" +
                "\t`UUID` TEXT NOT NULL,\n" +
                "\t`FIRST_NAME` TEXT NOT NULL,\n" +
                "\t`LAST_NAME` TEXT NOT NULL,\n" +
                "\t`AGE` INT unsigned DEFAULT NULL,\n" +
                "\t`SEX` INT unsigned DEFAULT NULL,\n" +
                "\t`FEET` INT unsigned DEFAULT NULL,\n" +
                "\t`INCHES` INT unsigned DEFAULT NULL,\n" +
                "\t`LBS` INT unsigned DEFAULT NULL,\n" +
                "\t`CITY` TEXT DEFAULT '',\n" +
                "\t`COUNTRY` TEXT DEFAULT '',\n" +
                "\t`PROFILE_PICTURE` TEXT DEFAULT '',\n" +
                "\t`BACKGROUND_PICTURE` TEXT DEFAULT '',\n" +
                "\t`GOAL_TYPE` INT DEFAULT NULL,\n" +
                "\t`LBS_PER_WEEK` INT DEFAULT NULL,\n" +
                "\t`RECOMMENDED_DAILY_CALORIES` INT unsigned DEFAULT NULL,\n" +
                "\t`IS_ACTIVE` BOOL DEFAULT FALSE,\n" +
                "\t`BMR` INT DEFAULT NULL,\n" +
                "\t`BMI` FLOAT unsigned DEFAULT NULL,\n" +
                "\tPRIMARY KEY (`UUID`)\n" +
                ");";

        // Executes the SQL command
        db!!.execSQL(createTableStatement);

    }

    /* Method called when table schemas are modified for different versions of the app.
     It's safe to ignore for now. */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    /* Adds a  user to the user table. Returns true if the user was added successfully,
     false otherwise */
    fun addUser(user: UserData) : Boolean {
        var db : SQLiteDatabase = this.writableDatabase;
        var cv : ContentValues = ContentValues();

        cv.put(COLUMN_UUID, user.uuid);
        cv.put(COLUMN_FIRST_NAME, user.firstName);
        cv.put(COLUMN_LAST_NAME, user.lastName);
        cv.put(COLUMN_AGE, user.age);
        cv.put(COLUMN_SEX, user.sex);
        cv.put(COLUMN_FEET, user.feet);
        cv.put(COLUMN_INCHES, user.inches);
        cv.put(COLUMN_LBS, user.lbs);
        cv.put(COLUMN_CITY, user.city);
        cv.put(COLUMN_COUNTRY, user.country);
        cv.put(COLUMN_PROFILE_PICTURE, user.profilePicture);
        cv.put(COLUMN_BACKGROUND_PICTURE, user.backgroundPicture);
        cv.put(COLUMN_GOAL_TYPE, user.goalType);
        cv.put(COLUMN_LBS_PER_WEEK, user.lbsPerWeek);
        cv.put(COLUMN_RECOMMENDED_DAILY_CALORIES, user.recommendedDailyCalories);
        cv.put(COLUMN_BMR, user.BMI);
        cv.put(COLUMN_BMI, user.BMI);

        var insertionResult : Long = db.insert(USER_TABLE, null, cv);
        // Return false if insertionResult == -1, true otherwsie
        return insertionResult != -1L
    }

    /* Returns the user with the given uuid */
    fun getUser(uuid: String) : UserData {
        var query : String = "SELECT * FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        // if there are any results from the query
        if (cursor.moveToFirst()) {
            var uuid : String = cursor.getString(0);
            var firstName : String = cursor.getString(1);
            var lastName : String = cursor.getString(2);
            var age : Int = cursor.getInt(3);
            var sex : Int = cursor.getInt(4);
            var feet : Int = cursor.getInt(5);
            var inches : Int = cursor.getInt(6);
            var lbs : Int = cursor.getInt(7);
            var city : String = cursor.getString(8);
            var country : String = cursor.getString(9);
            var profilePicture : String = cursor.getString(10);
            var backgroundPicture : String = cursor.getString(11);
            var goalType : Int = cursor.getInt(12);
            var lbsPerWeek: Int = cursor.getInt(13);
            var isActive : Boolean = cursor.getInt(14) == 1;
            var recommendedDailyCalories : Int = cursor.getInt(15);
            var BMR : Int = cursor.getInt(16);
            var BMI : Float = cursor.getFloat(16);

            var user : UserData = UserData(uuid, firstName, lastName, age, sex, feet, inches, lbs, city, country, profilePicture, backgroundPicture,
                goalType, lbsPerWeek, isActive, recommendedDailyCalories, BMR, BMI);

            return user;
        } else {
            print("No user found.");
            // Return default UserData object
            return UserData();
        }
    }

    /* Updates a  user's info in the db */
    fun updateUser(user: UserData) : Boolean {
        var db : SQLiteDatabase = this.writableDatabase;
        var cv : ContentValues = ContentValues();

        cv.put(COLUMN_FIRST_NAME, user.firstName);
        cv.put(COLUMN_LAST_NAME, user.lastName);
        cv.put(COLUMN_AGE, user.age);
        cv.put(COLUMN_SEX, user.sex);
        cv.put(COLUMN_FEET, user.feet);
        cv.put(COLUMN_INCHES, user.inches);
        cv.put(COLUMN_LBS, user.lbs);
        cv.put(COLUMN_CITY, user.city);
        cv.put(COLUMN_COUNTRY, user.country);
        cv.put(COLUMN_PROFILE_PICTURE, user.profilePicture);
        cv.put(COLUMN_BACKGROUND_PICTURE, user.backgroundPicture);
        cv.put(COLUMN_GOAL_TYPE, user.goalType);
        cv.put(COLUMN_LBS_PER_WEEK, user.lbsPerWeek);

        // Update user's biometric data (BMR, recommmended daily calories, BMI)
        val bmr : Int = UserData.calculateBMR(user.lbs, user.feet, user.inches, user.age, user.sex, user.isActive)
        cv.put(COLUMN_BMR, bmr);

        val recommendedDailyCalories : Int = UserData.calculateRecommendedDailyCalories(bmr, user.goalType, user.lbsPerWeek)
        cv.put(COLUMN_RECOMMENDED_DAILY_CALORIES, recommendedDailyCalories);

        val bmi : Float = UserData.calculateBMI(user.lbs, user.feet, user.inches)
        cv.put(COLUMN_BMI, bmi);

        val whereClause : String = COLUMN_UUID+" = " + user.uuid

        var updateResult : Int = db.update(USER_TABLE, cv, COLUMN_UUID + "= ?", arrayOf(user.uuid))
        // Return false if insertionResult == -1, true otherwsie
        return updateResult != -1
    }

    fun getLastName(uuid: String) : String {
        var query : String = "SELECT LAST_NAME FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        var lastName : String;
        // if there are any results from the query
        if (cursor.moveToFirst()) {
            lastName = cursor.getString(2);
        } else {
           lastName = ""
           lastName = ""
           lastName = ""
        }
        return lastName
    }

    fun getProfilePictureURI(uuid: String) : String {
        var query : String = "SELECT "  + COLUMN_PROFILE_PICTURE + " FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        var uri : String;
        // if there are any results from the query
        if (cursor.moveToFirst()) {
            uri = cursor.getString(10);
        } else {
            uri = ""

        }
        return uri
    }

    fun getCals(uuid: String) : String {
        var query : String = "SELECT LAST_NAME FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        var cals : Int;
        // if there are any results from the query
        if (cursor.moveToFirst()) {
            cals = cursor.getInt(14);
        } else {
            cals = 0

        }
        return cals.toString()
    }

    fun getFirstName(uuid: String) : String {
        var query : String = "SELECT LAST_NAME FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        var lastName : String;
        // if there are any results from the query
        if (cursor.moveToFirst()) {
            lastName = cursor.getString(1);
        } else {
            lastName = ""
            lastName = ""
            lastName = ""
        }
        return lastName
    }

    fun getBMI(uuid: String) : String {
        var query : String = "SELECT LAST_NAME FROM " + USER_TABLE + "WHERE UUID = " + uuid;
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("SELECT * FROM USER WHERE UUID = ?", arrayOf(uuid));

        var BMI : Int;
        // if there are any results from the query
        if (cursor.moveToFirst()) {
            BMI = cursor.getInt(17);
        } else {
            BMI = 0

        }
        return BMI.toString()
    }

    fun getAllUuids(): MutableList<String> {
        var query : String = "SELECT UUID FROM " + USER_TABLE
        var db : SQLiteDatabase = this.readableDatabase;
        var cursor : Cursor = db.rawQuery("select UUID from USER",null);

        var UUIDList : MutableList<String> = ArrayList()
        // if there are any results from the query
        while (!cursor.isAfterLast)
        {
            if (cursor.moveToNext()) {
                val name = cursor.getString(0)
                UUIDList.add(name)
            }

        }
        return UUIDList
    }

    /* Deletes a user with the given uuid from the database  */
    fun deleteUser(uuid : String) : Boolean {
        var db : SQLiteDatabase = this.readableDatabase;
        return db.delete(USER_TABLE, COLUMN_UUID + "= ?", arrayOf(uuid)) > 0;
    }




}