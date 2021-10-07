package com.example.lifestyle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding

class CreateUserProfile : AppCompatActivity(), FirstAndLastName.OnDataPass, FragmentAgeHeightWeight.OnDataPass, FragmentCityCountry.OnDataPass, FragmentProfileAndBackgroundPicture.OnDataPassProfileAndBackgroundPicture {
    lateinit var uuid : String
    lateinit var firstName: String
    lateinit var lastName: String
    var age: Int = 0
    var sex : Int = 0
    var feet: Int = 0;
    var inches: Int = 0;
    var lbs: Int = 0;
    lateinit var city: String
    lateinit var country: String
    var profilePicture: String = ""
    var backgroundPicture: String = ""
    var isActive:Boolean=false
    var goalType = 0
    var lbsPerWeek = 0


    private var _binding: FragmentProfileAndBackgroundPictureBinding? = null
    private val binding get() = _binding!!

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

    override fun onDataPassProfileAndBackgroundPicture(data: String) {

        if (data == "addUser") {
            addUserToDatabase()
        }
        else {
            profilePicture = data
            backgroundPicture = ""
        }

    }

    fun addUserToDatabase() {
        // Create a DBManager object
        var dbManager = DBManager(this);

        // Create a user object
        this.uuid = UserModel.generateUUID()
        val user: UserModel = UserModel(uuid, firstName, lastName,age,sex,feet,inches,lbs,city,country,profilePicture,backgroundPicture,goalType,lbsPerWeek*goalType,isActive,UserModel.calculateRecommendedDailyCalories(UserModel.calculateBMR(lbs,feet,inches,age,sex,isActive),goalType,lbsPerWeek),UserModel.calculateBMR(lbs,feet,inches,age,sex,isActive), UserModel.calculateBMI(lbs,feet,inches))


        // Add the user to the database
        var addUserDidSucceed : Boolean = Repository.addUser(user, dbManager);

        // Navigate to home page
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("uuid", this.uuid);
        startActivity(intent)

        Log.d("LOG", "Sex: $sex")
        Log.d("LOG", "Database adding result: $addUserDidSucceed")
        Log.d("LOG", this.profilePicture)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton)
        {
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
                R.id.yes ->
                    if (checked) {
                        isActive = true
                        Log.d("LOG", "yes to active")
                    }
                R.id.no ->
                    if (checked) {
                        isActive = false
                        Log.d("LOG", "No to active")
                    }
                R.id.gain ->
                    if (checked) {
                        var pounds_per_week_lin_layout : LinearLayout = findViewById(R.id.pounds_per_week_linear_layout)
                        val params: ViewGroup.LayoutParams = pounds_per_week_lin_layout.getLayoutParams()
                        // Changes the height and width to the specified *pixels*
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        pounds_per_week_lin_layout.setLayoutParams(params)
                        goalType = 1
                        Log.d("LOG", "you want to gain")
                    }
                R.id.Lose ->
                    if (checked) {
                        var pounds_per_week_lin_layout : LinearLayout = findViewById(R.id.pounds_per_week_linear_layout)
                        val params: ViewGroup.LayoutParams = pounds_per_week_lin_layout.getLayoutParams()
                        // Changes the height and width to the specified *pixels*
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        pounds_per_week_lin_layout.setLayoutParams(params)
                        goalType = -1
                        Log.d("LOG", "you want to lose")
                    }
                R.id.Maintain ->
                    if (checked) {
                        var pounds_per_week_lin_layout : LinearLayout = findViewById(R.id.pounds_per_week_linear_layout)
                        val params: ViewGroup.LayoutParams = pounds_per_week_lin_layout.getLayoutParams()
                        // Changes the height and width to the specified *pixels*
                        params.height = 0
                        pounds_per_week_lin_layout.setLayoutParams(params)
                        goalType = 0
                        Log.d("LOG", "you want to maintain")
                    }
                R.id.one ->
                    if (checked) {
                        lbsPerWeek = 1
                        Log.d("LOG", "you selected one")
                    }
                R.id.two ->
                    if (checked) {
                        lbsPerWeek = 2
                        Log.d("LOG", "you selected two")
                    }
            }
        }
    }
}