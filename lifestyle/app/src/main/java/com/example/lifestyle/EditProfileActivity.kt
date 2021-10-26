package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Gravity
import android.widget.*

import android.content.Intent

import android.net.Uri
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    // The database manager
    lateinit var mDBManager: DBManager

    // The user's info
    lateinit var user : UserData

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
    lateinit var mWeightGoal_rgroup : RadioGroup
    lateinit var mLoseWeight_rbtn : RadioButton
    lateinit var mMaintainWeight_rbtn : RadioButton
    lateinit var mGainWeight_rbtn : RadioButton
    lateinit var mLbsPerWeek_linear_layout : LinearLayout
    lateinit var mLbsPerWeek_rgroup : RadioGroup
    lateinit var mOneLb_rbtn : RadioButton
    lateinit var mTwoLbs_rbtn : RadioButton


    lateinit var mProfilePicture : ImageView
    lateinit var mSaveButton : Button
    lateinit var mEditProfilePictureButton : Button
    lateinit var mDeleteProfileButton : Button

    private var mEditProfileViewModel: EditProfileViewModel? = null

    private var muuid : String? = ""

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
        mWeightGoal_rgroup = findViewById(R.id.edit_profile_weight_goal_radio_group)
        mLoseWeight_rbtn = findViewById(R.id.edit_profile_lose_weight_rbtn)
        mMaintainWeight_rbtn = findViewById(R.id.edit_profile_maintain_weight_rbtn)
        mGainWeight_rbtn = findViewById(R.id.edit_profile_gain_weight_rbtn)

        // set click listeners for weight goal buttons
        mLoseWeight_rbtn.setOnClickListener(this)
        mMaintainWeight_rbtn.setOnClickListener(this)
        mGainWeight_rbtn.setOnClickListener(this)


        mLbsPerWeek_linear_layout = findViewById(R.id.edit_profile_lbs_per_week_linear_layout)
        mLbsPerWeek_rgroup = findViewById(R.id.edit_profile_lbs_per_week_radio_group)
        mOneLb_rbtn = findViewById(R.id.edit_profile_one_lb_rbtn)
        mTwoLbs_rbtn = findViewById(R.id.edit_profile_two_lbs_rbtn)

        mProfilePicture = findViewById(R.id.edit_profile_profile_picture)

        // Get the save button
        mSaveButton = findViewById<Button>(R.id.edit_profile_save_btn)
        mSaveButton.setOnClickListener(this)

        // Get the delete profile button
        //mDeleteProfileButton = findViewById<Button>(R.id.delete_profile_btn)
        //mDeleteProfileButton.setOnClickListener(this)

        // Get the Edit Profile Picture button
        mEditProfilePictureButton = findViewById(R.id.edit_profile_edit_profile_picture_btn)
        mEditProfilePictureButton.setOnClickListener(this)

        // Get the user's uuid from previous activity
        muuid = intent.getExtras()?.getString("uuid")

        // Create the view model
        mEditProfileViewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        // Set the uuid for the view model
        mEditProfileViewModel!!.setUUID(muuid!!)
        //Set the observer
        mEditProfileViewModel!!.getUser().observe(this, nameObserver)

    }

    // Create an observer that watches the LiveData<List<UserTable>> object
    val nameObserver: Observer<UserTable> =
        Observer<UserTable> { user ->
            if (user != null) {
                if (user.COLUMN_PROFILE_PICTURE == "") {
                    mProfilePicture.setImageResource(R.drawable.ic_user)
                }
                var imageView=findViewById<ImageView>(R.id.edit_profile_profile_picture)
//                user.COLUMN_PROFILE_PICTURE?.let { Log.d("LOG", it) }
//                Log.d("LOG", "user.profilePicture")
                imageView.setImageBitmap(ImageEnDeCoder.decodeImage(user.COLUMN_PROFILE_PICTURE))

                // Set the text with user's details
                mFirstName_et.setText(user.COLUMN_FIRST_NAME)
                mLastName_et.setText(user.COLUMN_LAST_NAME)
                mAge_et.setText(user.COLUMN_AGE.toString())
                mHeightFeet_et.setText(user.COLUMN_FEET.toString())
                mHeightInches_et.setText(user.COLUMN_INCHES.toString())
                mWeight_et.setText(user.COLUMN_LBS.toString())
                if (user.COLUMN_SEX == 1) { // user is female
                    mSex_rgroup.check(mSex_female_rbtn.id)
                } else { // user is male
                    mSex_rgroup.check(mSex_male_rbtn.id)
                }
                mCity_et.setText(user.COLUMN_CITY)
                mCountry_et.setText(user.COLUMN_COUNTRY)
                if (user.COLUMN_IS_ACTIVE!!) { // user is active
                    mAreYouActive_rgroup.check(mIsActive_rbtn.id)
                } else { // user is sedentary (not active)
                    mAreYouActive_rgroup.check(mIsNotActive_rbtn.id)
                }

                if (user.COLUMN_GOAL_TYPE == -1) { // lose weight
                    mWeightGoal_rgroup.check(mLoseWeight_rbtn.id)
                } else if (user.COLUMN_GOAL_TYPE == 0) {  // maintain weight
                    mWeightGoal_rgroup.check(mMaintainWeight_rbtn.id)
                } else { // gain weight
                    mWeightGoal_rgroup.check(mGainWeight_rbtn.id)
                }

                if (user.COLUMN_GOAL_TYPE == 0) {
                    val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
                    // Changes the height and width to the specified *pixels*
                    params.height = 0
                    mLbsPerWeek_linear_layout.setLayoutParams(params)
                } else if ((user.COLUMN_GOAL_TYPE == -1) || (user.COLUMN_GOAL_TYPE == 1)) {
                    val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
                    // Changes the height and width to the specified *pixels*
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    mLbsPerWeek_linear_layout.setLayoutParams(params)

                    if (user.COLUMN_LBS_PER_WEEK == 1) { // lose/gain 1 lb per week
                        mLbsPerWeek_rgroup.check(mOneLb_rbtn.id)
                    } else {
                        mLbsPerWeek_rgroup.check(mTwoLbs_rbtn.id)
                    }
                }
            }
        }

    override fun onClick(v: View?) {
        if (v != null)
            when (v.id)
            {
                R.id.edit_profile_save_btn -> {
                    // Saves the current edit text input to the activity (not to the database)
                    saveUserInput()
                    val toast = Toast.makeText(this, "Changes Successfully Saved", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()

                    // Head back to main page
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }
                R.id.edit_profile_edit_profile_picture_btn -> {
                    // TODO

                }
                R.id.edit_profile_lose_weight_rbtn -> {
                    val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
                    // Changes the height and width to the specified *pixels*
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    mLbsPerWeek_linear_layout.setLayoutParams(params)

                }
                R.id.edit_profile_maintain_weight_rbtn -> {
                    val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
                    // Changes the height and width to the specified *pixels*
                    params.height = 0
                    mLbsPerWeek_linear_layout.setLayoutParams(params)
                }
                R.id.edit_profile_gain_weight_rbtn -> {
                    val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
                    // Changes the height and width to the specified *pixels*
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    mLbsPerWeek_linear_layout.setLayoutParams(params)
                }
                /*R.id.delete_profile_btn -> {
                    // Delete the user's profile from the database
                    mDBManager.deleteUser(user.uuid)

                    // Head back to login page
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }*/

            }

    }

    /* Update the user : UserData member variable with the current
     info inside the edit text fields*/
    fun saveUserInput() {

        var sex : Int
        if (mSex_female_rbtn.isChecked) {
            sex = 1
        } else {
            sex = 0
        }

        var goalType : Int
        if (mLoseWeight_rbtn.isChecked) {
            goalType = -1
        } else if (mMaintainWeight_rbtn.isChecked) {
            goalType = 0
        } else {
            goalType = 1
        }

        var lbsPerWeek : Int = 0
        if (mOneLb_rbtn.isChecked) {
            lbsPerWeek = 1
        } else if (mTwoLbs_rbtn.isChecked) {
            lbsPerWeek = 2
        }

        mEditProfileViewModel!!.updateUser(muuid!!, mFirstName_et.text.toString(),
            mLastName_et.text.toString(), mAge_et.text.toString().toInt(), sex,
            mHeightFeet_et.text.toString().toInt(), mHeightInches_et.text.toString().toInt(),
            mWeight_et.text.toString().toInt(), mCity_et.text.toString(), mCountry_et.text.toString(),
            mIsActive_rbtn.isChecked, goalType, lbsPerWeek)

    }

   
}