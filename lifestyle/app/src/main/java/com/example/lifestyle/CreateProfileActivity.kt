package com.example.lifestyle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateProfileActivity : AppCompatActivity(),View.OnClickListener
{

    lateinit var submitButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        submitButton=findViewById(R.id.submit_btn) as Button;
        submitButton.setOnClickListener(this);



    }


    override fun onClick(v: View?)
    {
        if (v != null)
        {
            when (v.id)
            {
                R.id.submit_btn->
                {
                    // Get first name from edit text
                    val txtFirstName =
                        findViewById(R.id.first_name_et) as EditText
                    val firstName = txtFirstName.text.toString()

                    // Get last name from edit text
                    val txtLastName =
                        findViewById(R.id.last_name_et) as EditText
                    val lastName = txtLastName.text.toString()

                    // Get age from edit text
                    val txtAge =
                        findViewById(R.id.age_et) as EditText
                    var age : Int;
                    if (txtAge.text.toString()=="")
                    {
                        age = 0;
                    }else
                    {
                         age = txtAge.text.toString().toInt()
                    }

                    // Get feet from edit text
                    val txtFeet =
                        findViewById(R.id.feet_et) as EditText
                    var feet: Int;
                    if (txtFeet.text.toString()=="")
                    {
                        feet = 0;
                    }else
                    {
                         feet = txtFeet.text.toString().toInt()
                    }

                    // Get inches from edit text
                    val txtInch =
                        findViewById(R.id.inches_et) as EditText
                    var inches : Int;
                    if (txtInch.text.toString()=="")
                    {
                        inches = 0;
                    }else
                    {
                        inches = txtInch.text.toString().toInt()
                    }

                    // Get weight from edit text
                    val txtLbs =
                        findViewById(R.id.lbs_et) as EditText
                    var lbs: Int;
                    if (txtLbs.text.toString()=="")
                    {
                        lbs = 0;
                    }else
                    {
                        lbs = txtLbs.text.toString().toInt()
                    }

                    // Get city from edit text
                    val txtCity =
                        findViewById(R.id.city_et) as EditText
                    val city = txtCity.text.toString()

                    // Get country from edit text
                    val txtCountry =
                        findViewById(R.id.country_et) as EditText
                    val country = txtCountry.text.toString()



                    // We created dummy values for the rest of the necessary arguments to construct a
                    // user model.
                    // All the following variables are jst dummmy values/
                    // These variables will represent file paths to the photos
                    val profilePicture = ""
                    val backgroundPicture = ""
                    // male = 0, female = 1
                    val sex= 0
                    // -1 = lose weight, 0 = maintain weight, 1 = gain weight
                    var goalType = 0
                    // the number of pounds the user wants to gain / loseper week
                    var lbsPerWeek = 0
                    // represents whether the user is sedentary or active
                    var isActive = false

                    // Create a user object
                    var uuid: String = UserModel.generateUUID()
                    val user:UserModel = UserModel(uuid,firstName,lastName,age,sex,feet,inches,lbs,city,country,profilePicture,backgroundPicture,goalType,lbsPerWeek,isActive)
                    // Create a DBManager object
                    var dbManager : DBManager = DBManager(this);
                    // Add the user to the database
                    var addUserDidSucceed : Boolean = dbManager.addUser(user);

                    // Show the home screen
                     showMainActivity()
                }
                else ->
                { // Note the block 
                    print("Invalid button press.")
                }
            }
        }

    }

    private fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showCreateUserProfile() {
        val intent = Intent(this, CreateUserProfile::class.java)
        startActivity(intent)
    }
}


