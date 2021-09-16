package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding

class CreateUserProfile : AppCompatActivity(), FirstAndLastName.OnDataPass, FragmentAgeHeightWeight.OnDataPass, FragmentCityCountry.OnDataPass, FragmentProfileAndBackgroundPicture.OnDataPassProfileAndBackgroundPicture {

    lateinit var firstName: String
    lateinit var lastName: String
    var age: Int = 0
    var sex : Int = 0
    var feet: Int = 0;
    var inches: Int = 0;
    var lbs: Int = 0;
    lateinit var city: String
    lateinit var country: String
    lateinit var profilePicture: String
    lateinit var backgroundPicture: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_profile)
    }

    override fun onDataPassFirstAndLastName(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
        Log.d("LOG", "firstName: $firstName")
        Log.d("LOG", "lastName: $lastName")
    }

    fun isNumber(s: String?): Int {
        return if (!s.isNullOrEmpty() && s.matches(Regex("\\d+"))) {
            s.toInt()
        } else {
            0
        }
    }

    override fun onDataPass(age: String, feet: String, inches: String, lbs: String) {

        this.age = isNumber(age)
        this.feet = isNumber(feet)
        this.inches = isNumber(inches)
        this.lbs = isNumber(lbs)

        Log.d("LOG", "age: $age")
        Log.d("LOG", "feet: $feet")
        Log.d("LOG", "inches: $inches")
        Log.d("LOG", "lbs: $lbs")
    }

    override fun onDataPass(city: String, country: String) {
        this.city = city
        this.country = country

        Log.d("LOG", "city: $city")
        Log.d("LOG", "country: $country")
    }

    override fun onDataPassProfileAndBackgroundPicture(firstName: String, lastName: String) {
        // dummy values for the rest of the necessary arguments to construct a
        // user model.
        profilePicture = ""
        backgroundPicture = ""

        // -1 = lose weight, 0 = maintain weight, 1 = gain weight
        var goalType = 0
        // the number of pounds the user wants to gain / loseper week
        var lbsPerWeek = 0
        // represents whether the user is sedentary or active
        var isActive = false

        // Create a user object
        var uuid: String = UserModel.generateUUID()
        val user:UserModel = UserModel(uuid, this.firstName, this.lastName,age,sex,feet,inches,lbs,city,country,profilePicture,backgroundPicture,goalType,lbsPerWeek,isActive)
        // Create a DBManager object
        var dbManager : DBManager = DBManager(this);
        // Add the user to the database
        var addUserDidSucceed : Boolean = dbManager.addUser(user);

        Log.d("LOG", "Hey Hey")
        Log.d("LOG", "firstName: " + this.firstName)
        Log.d("LOG", "Sex: $sex")
        Log.d("LOG", "Database adding result: $addUserDidSucceed")
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.male_rbtn ->
                    if (checked) {
                        sex = 0
                        Log.d("LOG", "You are Male")
                    }
                R.id.female_rbtn ->
                    if (checked) {
                        sex = 1
                        Log.d("LOG", "You are Female")
                    }
                R.id.prefer_not_to_say_rbtn ->
                    if (checked) {
                        sex = 2
                        Log.d("LOG", "You prefer not to say")
                    }
            }
        }
    }
}