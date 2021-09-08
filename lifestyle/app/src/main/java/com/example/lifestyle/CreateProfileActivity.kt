package com.example.lifestyle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity

class CreateProfileActivity : AppCompatActivity(),View.OnClickListener
{

    lateinit var submitButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        submitButton=findViewById(R.id.submit_btn) as Button;
        submitButton.setOnClickListener(this);



    }
//    firstName: String,
//    lastName: String,
//    age: Int,
//    feet: Int,
//    inches: Int,
//    lbs: Int,
//    city: String,
//    country: String,
//    profilePicture: String,
//    backgroundPicture: String,
//    goalType: Int,
//    lbsPerWeek: Int

    override fun onClick(v: View?)
    {
        if (v != null)
        {
            when (v.id)
            {

                R.id.submit_btn->
                {
                    val txtFirstName =
                        findViewById(R.id.first_name_et) as EditText
                    val firstName = txtFirstName.text.toString()

                    val txtLastName =
                        findViewById(R.id.last_name_et) as EditText
                    val lastName = txtLastName.text.toString()

                    val txtAge =
                        findViewById(R.id.age_et) as EditText
                    var age= 0
                    if (txtAge.text.toString()=="")
                    {

                    }else
                    {
                         age = txtAge.text.toString().toInt()
                    }

// THIS IS WRONG BECAUSE OF REASONS
//                    val txtSex =
//                        findViewById(R.id.se) as EditText
                    val sex= 0
//                    if (txtAge.text.toString()=="")
//                    {
//
//                    }else
//                    {
//                        age = txtAge.text.toString().toInt()
//                    }


                    val txtFeet =
                        findViewById(R.id.feet_et) as EditText
                    var feet = 0
                    if (txtFeet.text.toString()=="")
                    {

                    }else
                    {
                         feet = txtFeet.text.toString().toInt()
                    }

                    val txtInch =
                        findViewById(R.id.inches_et) as EditText
                    var inches = 0
                    if (txtInch.text.toString()=="")
                    {

                    }else
                    {
                        inches = txtInch.text.toString().toInt()
                    }


                    val txtLbs =
                        findViewById(R.id.weight_et) as EditText
                    var lbs = 0
                    if (txtLbs.text.toString()=="")
                    {

                    }else
                    {
                        lbs = txtLbs.text.toString().toInt()
                    }

                    val txtCity =
                        findViewById(R.id.city_et) as EditText
                    val city = txtCity.text.toString()


                    val txtCountry =
                        findViewById(R.id.country_et) as EditText
                    val country = txtCountry.text.toString()

                    //to DO Add a way to find the file path going to auto write something for now
//                    val txtProfilePicture =
//                        findViewById(R.id.pr) as EditText
                    val profilePicture = "WOW WHAT GREAT DOCUMENTATION"


//                    val txtbackgroundPicture =
//                        findViewById(R.id.pr) as EditText
                    val backgroundPicture = ""

                    //added an automatic your fat and need to work out since our testing UI is so small
                    //please add the correct txt finder

//                    val txtGoalType =
//                        findViewById(R.id.city_et) as EditText
                    var goalType = 0
//                    if (txtGoalType.text.toString()=="")
//                    {
//
//                    }else
//                    {
//                        goalType = txtGoalType.text.toString().toInt()
//                    }



//                    val txtLbsPerWeek =
//                        findViewById(R.id.city_et) as EditText
                        var lbsPerWeek = 0
//                    if (txtLbsPerWeek.text.toString()=="")
//                    {
//
//                    }else
//                    {
//                        lbsPerWeek = txtLbsPerWeek.text.toString().toInt()
//                    }


                    //                    val txtLbsPerWeek =
//                        findViewById(R.id.city_et) as EditText
                    var isActive = false
//                    if (txtLbsPerWeek.text.toString()=="")
//                    {
//
//                    }else
//                    {
//                        lbsPerWeek = txtLbsPerWeek.text.toString().toInt()
//                    }
                    //end of Section TO DOOOOOOOOOOOOOOOOOOOOO





//                    uuid: String,
//                    firstName: String,
//                    lastName: String,
//                    age: Int,
//                    sex: Int,
//                    feet: Int,
//                    inches: Int,
//                    lbs: Int,
//                    city: String,
//                    country: String,
//                    profilePicture: String,
//                    backgroundPicture: String,
//                    goalType: Int,
//                    lbsPerWeek: Int,
//                    isActive: Boolean

//
                    val user:UserModel = UserModel("BRADEN68",firstName,lastName,age,sex,feet,inches,lbs,city,country,profilePicture,backgroundPicture,goalType,lbsPerWeek,isActive)

                    var dbManager : DBManager = DBManager(this);
                    var addUserDidSucceed : Boolean = dbManager.addUser(user);
                    var userResult : UserModel = dbManager.getUser("BRADEN68");
                    print("Here We Go:");
                    print(userResult.toString());
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
}


