package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding

class CreateUserProfile : AppCompatActivity(), FirstAndLastName.OnDataPass, FragmentAgeHeightWeight.OnDataPass, FragmentCityCountry.OnDataPass {

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

    override fun onDataPass(age: String, feet: String, inches: String, lbs: String) {
        this.age = age.toInt()
        this.feet = feet.toInt()
        this.inches = inches.toInt()
        this.lbs = lbs.toInt()

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


}