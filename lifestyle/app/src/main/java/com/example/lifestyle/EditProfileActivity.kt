package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import java.lang.Exception

class EditProfileActivity : AppCompatActivity() {

    lateinit var user : UserModel

    // UI Elements
    lateinit var mFirstName_et : EditText
    lateinit var mLastName_et : EditText
    lateinit var mAge_et : EditText
    lateinit var mHeightFeet_et : EditText
    lateinit var mHeightInches_et : EditText
    lateinit var mWeight_et : EditText
    lateinit var mSex_rgroup : RadioGroup
    lateinit var mSex_female_rbtn : RadioButton
    lateinit var mSex_male_rbtn : RadioButton
    lateinit var mCity_et : EditText
    lateinit var mCountry_et : EditText
    lateinit var mAreYouActive_rgroup : RadioGroup
    lateinit var mIsActive_rbtn : RadioButton
    lateinit var mIsNotActive_rbtn : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Get the UI edit text fields
        mFirstName_et = findViewById<EditText>(R.id.edit_profile_first_name_et);
        mLastName_et = findViewById<EditText>(R.id.edit_profile_last_name_et);
        mAge_et = findViewById<EditText>(R.id.edit_profile_age_et);
        mHeightFeet_et = findViewById<EditText>(R.id.edit_profile_height_feet_et);
        mHeightInches_et = findViewById<EditText>(R.id.edit_profile_height_inches_et);
        mWeight_et = findViewById<EditText>(R.id.edit_profile_weight_et);
        mSex_rgroup = findViewById(R.id.edit_profile_sex_radio_group);
        mSex_female_rbtn = findViewById(R.id.edit_profile_female_rbtn)
        mSex_male_rbtn = findViewById(R.id.edit_profile_male_rbtn)
        mCity_et = findViewById(R.id.edit_profile_city_et)
        mCountry_et = findViewById(R.id.edit_profile_country_et)
        mAreYouActive_rgroup = findViewById(R.id.edit_profile_are_you_active_radio_group);
        mIsActive_rbtn = findViewById(R.id.edit_profile_is_active_rbtn)
        mIsNotActive_rbtn = findViewById(R.id.edit_profile_is_not_active_rbtn)

        // Get the user's uuid from previous activity
        val uuid : String? = intent.getExtras()?.getString("uuid")
        try {
            // Create a DBManager object
            var dbManager: DBManager = DBManager(this);
            // Get the user's info from the database
            this.user = dbManager.getUser(uuid!!)

        } catch (e: Exception) {
            this.user = UserModel()
        }

        // Set the text with user's details
        mFirstName_et.setText(user.firstName)
        mLastName_et.setText(user.lastName)
        mAge_et.setText(user.age.toString())
        mHeightFeet_et.setText(user.feet.toString())
        mHeightInches_et.setText(user.inches.toString())
        mWeight_et.setText(user.lbs.toString())
        if (user.sex == 1) { // user is female
            mSex_rgroup.check(mSex_female_rbtn.id)
        } else { // user is male
            mSex_rgroup.check(mSex_male_rbtn.id)
        }
        mCity_et.setText(user.city)
        mCountry_et.setText(user.country)
        if (user.isActive) { // user is active
            mAreYouActive_rgroup.check(mIsActive_rbtn.id)
        } else { // user is sedentary (not active)
            mAreYouActive_rgroup.check(mIsNotActive_rbtn.id)
        }

    }
}