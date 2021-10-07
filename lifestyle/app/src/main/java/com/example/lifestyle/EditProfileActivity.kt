package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.lang.Exception
import android.view.Gravity
import android.widget.*

import android.content.Intent

import android.net.Uri
import android.view.ViewGroup
import androidx.core.net.toUri


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
        mDeleteProfileButton = findViewById<Button>(R.id.delete_profile_btn)
        mDeleteProfileButton.setOnClickListener(this)

        // Get the Edit Profile Picture button
        mEditProfilePictureButton = findViewById(R.id.edit_profile_edit_profile_picture_btn)
        mEditProfilePictureButton.setOnClickListener(this)

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

        // Set the user's profile picture
        var profile_pic_uri : Uri = user.profilePicture.toUri()
        mProfilePicture.setImageURI(profile_pic_uri)

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

        if (user.goalType == -1) { // lose weight
            mWeightGoal_rgroup.check(mLoseWeight_rbtn.id)
        } else if (user.goalType == 0) {  // maintain weight
            mWeightGoal_rgroup.check(mMaintainWeight_rbtn.id)
        } else { // gain weight
            mWeightGoal_rgroup.check(mGainWeight_rbtn.id)
        }

        if (user.goalType == 0) {
            val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
            // Changes the height and width to the specified *pixels*
            params.height = 0
            mLbsPerWeek_linear_layout.setLayoutParams(params)
        }
        else if ((user.goalType == -1) || (user.goalType == 1)) {
            val params: ViewGroup.LayoutParams = mLbsPerWeek_linear_layout.getLayoutParams()
            // Changes the height and width to the specified *pixels*
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            mLbsPerWeek_linear_layout.setLayoutParams(params)

            if (user.lbsPerWeek == 1) { // lose/gain 1 lb per week
                mLbsPerWeek_rgroup.check(mOneLb_rbtn.id)
            } else {
                mLbsPerWeek_rgroup.check(mTwoLbs_rbtn.id)
            }
        }

    }

    override fun onClick(v: View?) {
        if (v != null)
            when (v.id)
            {
                R.id.edit_profile_save_btn -> {
                    Log.d("LOG", "SAVE BUTTON IS PRESSED")
                    // Saves the current edit text input to the activity (not to the database)
                    saveUserInput()
                    // Saves the changes to the database
                    var updateSuccessful : Boolean = Repository.updateUser(this.user, mDBManager)

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
//                    val getImage = registerForActivityResult(
//                        ActivityResultContracts.GetContent()
//                    ) { uri: Uri? ->
//                        this.mProfilePicture?.setImageURI(uri)
//                        //  Update the user model variable. When Save button gets taped, it saves the user model  associated with the activity
//                        this.user.profilePicture = uri.toString()
//                    }
//                    getImage.launch("image/*")

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
                R.id.delete_profile_btn -> {
                    // Delete the user's profile from the database
                    mDBManager.deleteUser(user.uuid)

                    // Head back to login page
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

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
        if (mLoseWeight_rbtn.isChecked) {
            this.user.goalType = -1
        } else if (mMaintainWeight_rbtn.isChecked) {
            this.user.goalType = 0
        } else {
            this.user.goalType = 1
        }

        var lbsPerWeek : Int = 0
        if (mOneLb_rbtn.isChecked) {
            lbsPerWeek = 1
        } else if (mTwoLbs_rbtn.isChecked) {
            lbsPerWeek = 2
        }
        this.user.lbsPerWeek = lbsPerWeek

        return
    }

   
}