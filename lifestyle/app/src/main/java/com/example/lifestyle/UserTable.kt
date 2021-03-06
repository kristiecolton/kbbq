package com.example.lifestyle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* Initializing the table with the correct columns */
@Entity(tableName = "user_table")
data class UserTable(
    @PrimaryKey
    @ColumnInfo(name = "UUID") val COLUMN_UUID: String,
    @ColumnInfo(name = "FIRST_NAME") val COLUMN_FIRST_NAME: String,
    @ColumnInfo(name = "LAST_NAME") val COLUMN_LAST_NAME: String,
    @ColumnInfo(name = "AGE") val COLUMN_AGE: Int?,
    @ColumnInfo(name = "SEX") val COLUMN_SEX: Int?,
    @ColumnInfo(name = "FEET") val COLUMN_FEET: Int?,
    @ColumnInfo(name = "INCHES") val COLUMN_INCHES: Int?,
    @ColumnInfo(name = "LBS") val COLUMN_LBS: Int?,
    @ColumnInfo(name = "CITY") val COLUMN_CITY: String?,
    @ColumnInfo(name = "COUNTRY") val COLUMN_COUNTRY: String?,
    @ColumnInfo(name = "PROFILE_PICTURE") val COLUMN_PROFILE_PICTURE: String?,
    @ColumnInfo(name = "BACKGROUND_PICTURE") val COLUMN_BACKGROUND_PICTURE: String?,
    @ColumnInfo(name = "GOAL_TYPE") val COLUMN_GOAL_TYPE: Int?,
    @ColumnInfo(name = "LBS_PER_WEEK") val COLUMN_LBS_PER_WEEK: Int?,
    @ColumnInfo(name = "RECOMMENDED_DAILY_CALORIES") val COLUMN_RECOMMENDED_DAILY_CALORIES: Int?,
    @ColumnInfo(name = "IS_ACTIVE") val COLUMN_IS_ACTIVE: Boolean?,
    @ColumnInfo(name = "BMR") val COLUMN_BMR: Int?,
    @ColumnInfo(name = "BMI") val COLUMN_BMI: Float?,
)


