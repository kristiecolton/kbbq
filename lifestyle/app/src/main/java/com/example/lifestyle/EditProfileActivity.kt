package com.example.lifestyle

import android.app.Activity
import android.app.Instrumentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.lang.Exception
import android.view.Gravity
import android.widget.*
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.provider.MediaStore


class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    // The database manager
    lateinit var mDBManager: DBManager

    // The user's info
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

    lateinit var mProfilePicture : ImageView
    lateinit var mSaveButton : Button

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

        mProfilePicture = findViewById(R.id.edit_profile_profile_picture)
        // Get the save button
        mSaveButton = findViewById<Button>(R.id.edit_profile_save_btn)
        mSaveButton.setOnClickListener(this)

        // Get the user's uuid from previous activity
        val uuid : String? = intent.getExtras()?.getString("uuid")

        // Create a DBManager object
        mDBManager = DBManager(this);

        try {

            // Get the user's info from the database
            this.user = mDBManager.getUser(uuid!!)

        } catch (e: Exception) {
            this.user = UserModel()
        }

        if (user.profilePicture == "") {
            mProfilePicture.setImageResource(R.drawable.ic_user)
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

    override fun onClick(v: View?) {
        if (v != null)
            when (v.id)
            {
                R.id.edit_profile_save_btn -> {
                    Log.d("LOG", "SAVE BUTTON IS PRESSED")
                    saveUserInput()
                    var updateSuccessful : Boolean = mDBManager.updateUser(this.user)
                    if (updateSuccessful) {
                        val toast = Toast.makeText(this, "Changes Successfully Saved", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    } else {
                        val toast = Toast.makeText(this, "Something Went Wrong.", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }

                }
                R.id.edit_profile_edit_profile_picture_btn -> {

                }

            }

    }

    /* Update the user : UserModel member variable with the current
     info inside the edit text fields*/
    fun saveUserInput() {
        this.user.firstName = mFirstName_et.text.toString()
        this.user.lastName = mLastName_et.text.toString()
        this.user.age = mAge_et.text.toString().toInt()
        this.user.feet = mHeightFeet_et.text.toString().toInt()
        this.user.inches = mHeightInches_et.text.toString().toInt()
        this.user.lbs = mWeight_et.text.toString().toInt()
        if (mSex_female_rbtn.isChecked) {
            this.user.sex = 1
        } else {
            this.user.sex = 0
        }
        this.user.city = mCity_et.text.toString()
        this.user.country = mCountry_et.text.toString()
        this.user.isActive = mIsActive_rbtn.isChecked
        return
    }

   
}