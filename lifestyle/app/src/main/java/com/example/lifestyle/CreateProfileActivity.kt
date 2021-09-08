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
                    val age= 0
                    if (txtAge.text.toString()=="")
                    {

                    }else
                    {
                        val age = txtAge.text.toString().toInt()
                    }


                    val txtFeet =
                        findViewById(R.id.feet_et) as EditText
                    val feet = txtFeet.text.toString().toInt()

                    val txtInch =
                        findViewById(R.id.inches_et) as EditText
                    val inches = txtInch.text.toString().toInt()

                    val txtLbs =
                        findViewById(R.id.weight_et) as EditText
                    val lbs = txtLbs.text.toString().toInt()

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





                    //end of Section TO DOOOOOOOOOOOOOOOOOOOOO





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



//
//                    var user:UserModel()
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


