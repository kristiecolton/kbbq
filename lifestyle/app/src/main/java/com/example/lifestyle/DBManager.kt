package com.example.lifestyle

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.Nullable



// Represents the recommended calories per day for the user
private var recommendedDailyCalories : Int = 0;
// Represents the user’s Basal Metabolic Rate
private var BMR : Int = 0;
// Represents the user’s Body Mass Index
private var BMI : Int = 0;

class DBManager : SQLiteOpenHelper  {

    public final val USER_TABLE : String = "USER_TABLE";
    public final val UUID : String = "UUID";
    public final val FIRST_NAME : String = "FIRST_NAME";
    public final val LAST_NAME : String = "LAST_NAME";
    public final val AGE : String = "AGE";
    public final val FEET : String = "FEET";
    public final val INCHES : String = "INCHES";
    public final val LBS : String = "LBS";
    public final val CITY : String = "CITY";
    public final val COUNTRY : String = "COUNTRY";
    public final val PROFILE_PICTURE : String = "PROFILE_PICTURE";
    public final val BACKGROUND_PICTURE : String = "BACKGROUND_PICTURE";
    public final val GOAL_TYPE : String = "GOAL_TYPE";
    public final val LBS_PER_WEEK : String = "LBS_PER_WEEK";
    public final val RECOMMENDED_DAILY_CALORIES : String = "RECOMMENDED_DAILY_CALORIES";
    public final val BMR : String = "BMR";
    public final val BMI : String = "BMI";

    public final val TEXT_NOT_NULL : String = " TEXT NOT NULL ";














    // Constructor that creates a database called "user.db"
    constructor(@Nullable context: Context) : super(context, "user.db",null, 1) {

    }
    // Called when the db is being created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
        var createTableStatement : String = "CREATE TABLE `USER` (\n" +
                "\t`UUID` TEXT NOT NULL,\n" +
                "\t`FIRST_NAME` TEXT NOT NULL,\n" +
                "\t`LAST_NAME` TEXT NOT NULL,\n" +
                "\t`AGE` INT unsigned DEFAULT NULL,\n" +
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
                "\t`BMR` INT DEFAULT NULL,\n" +
                "\t`BMI` FLOAT unsigned DEFAULT NULL,\n" +
                "\tPRIMARY KEY (`UUID`)\n" +
                ");";

        // Executes the SQL command
        db!!.execSQL(createTableStatement);

    }
    // Called when app changes versions, where version change causes change to the database strucutre
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }



}