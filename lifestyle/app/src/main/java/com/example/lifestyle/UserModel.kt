package com.example.lifestyle

import android.graphics.Color
import android.util.Log
import kotlin.math.pow

//  A class to represent a user
class UserModel {
    // A unique identifier to identify users by
    var uuid : String = "";
    var firstName : String = "";
    var lastName : String = "";
    var age : Int = 0;
    // male = 0, female = 1
    var sex : Int = 0

    // Height
    var feet: Int = 0;
    var inches: Int = 0;

    // Weight
    var lbs: Int = 0;

    var city: String = "";
    var country: String = "";
    // The path to the user's profile picture
    var profilePicture: String = "";
    // The path to the user's background picture;
    var backgroundPicture: String = "";
    // Represents whether the user wants to lose, maintain, or gain  weight.
    // Lose Weight = -1, Maintain Weight = 0, Gain Weight = 1
    var goalType: Int = 0;
    // Represents the number of pounds a user desires to gain or lose per week.
    // This value should be zero if the user wants to maintain their
    // weight.
    var lbsPerWeek: Int = 0;
    // Represents the recommended calories per day for the user
    var recommendedDailyCalories : Int = 0;
    // Represents whether the user is active or not
    var isActive : Boolean = false;
    // Represents the user's Basal Metabolic Rate
    var BMR : Int = 0;
    // Represents the user's Body Mass Index
    var BMI : Float = 0.0F;

    /* Constructors */

    constructor()

    // A constructor for when the user only enters their name
    constructor(uuid: String, firstName: String, lastName: String) {
        this.uuid = uuid
        this.firstName = firstName
        this.lastName = lastName
    }

    // A constructor for when the user enters data in addition to name
    constructor(
        uuid: String,
        firstName: String,
        lastName: String,
        age: Int,
        sex: Int,
        feet: Int,
        inches: Int,
        lbs: Int,
        city: String,
        country: String,
        profilePicture: String,
        backgroundPicture: String,
        goalType: Int,
        lbsPerWeek: Int,
        isActive: Boolean
    ) {
        this.uuid = uuid
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.sex = sex;
        this.feet = feet
        this.inches = inches
        this.lbs = lbs
        this.city = city
        this.country = country
        this.profilePicture = profilePicture
        this.backgroundPicture = backgroundPicture
        this.goalType = goalType
        this.lbsPerWeek = lbsPerWeek
        this.isActive = isActive;
    }

    // A constructor that initializes every member variable
    constructor(
        uuid: String,
        firstName: String,
        lastName: String,
        age: Int,
        sex: Int,
        feet: Int,
        inches: Int,
        lbs: Int,
        city: String,
        country: String,
        profilePicture: String,
        backgroundPicture: String,
        goalType: Int,
        lbsPerWeek: Int,
        isActive: Boolean,
        recommendedDailyCalories: Int,
        BMR: Int,
        BMI: Float)
    {
        this.uuid = uuid
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.sex = sex;
        this.feet = feet
        this.inches = inches
        this.lbs = lbs
        this.city = city
        this.country = country
        this.profilePicture = profilePicture
        this.backgroundPicture = backgroundPicture
        this.goalType = goalType
        this.lbsPerWeek = lbsPerWeek
        this.isActive = isActive
        this.recommendedDailyCalories = recommendedDailyCalories
        this.BMR = BMR
        this.BMI = BMI
    }

    /* Generates an 8-character unique id */
    companion object {fun generateUUID() : String {
        val allowedChars = ('A'..'Z') + ('0'..'9')
        return (1..8)
            .map { allowedChars.random() }
            .joinToString("")
    }
        /* Health Metric Methods */

        // Calculates BMR using a user's weight and height
        fun calculateBMR(lbs: Int, feet: Int, inches: Int,age:Int, sex: Int,isActive: Boolean) : Int
        {
            var intBMR=0.0

            intBMR = if( sex==0)
            {
                66+(6.23*lbs)+(12.7*(feet*12+inches)) - (6.8 * age)
            }else
            {
                655+(4.35*lbs)+(4.7*(feet*12+inches)) - (4.7 * age)
            }
            if (isActive)
            {
                intBMR += (90 * (0.035 * 0.453592 * lbs) + ((1.78816 * 1.78816) / (0.0254 * (feet * 12 + inches))) * (0.029) * (0.453592 * lbs))
            }
            Log.d("LOG",intBMR.toString()+" BMR" )
            return intBMR.toInt();
        }
//    Calories burned per minute = 90* (0.035  X 0.453592 * lbs) +
//    ((1.78816 ^ 2) / (0.0254*Height in inches))) X (0.029) X ( 0.453592 * lbs)



        //    Women: BMR = 655 + (4.35 x weight in pounds) + (4.7 x height in inches) – (4.7 x age in years)
//    Men: BMR = 66 + (6.23 x weight in pounds) + (12.7 x height in inches) – (6.8 x age in years)
        // Calculates BMI using a user's weight and height
        fun calculateBMI(lbs: Int, feet: Int, inches: Int) : Float {
            if (feet == 0) { // check if height has been entered
                return 0F
            }
            val intBMI= 703.00*(lbs.toFloat()/((feet.toFloat() * 12.0 + inches.toFloat())*(feet.toFloat() * 12.0 + inches.toFloat())))
            Log.d("LOG",intBMI.toString()+" BMI"  )
            return intBMI.toFloat()
        }

        // Calculates the user's recommended daily calories based on their
        // BMR, type of goal (lose, maintain or gain weight) and the amount // of weight they'd like to change per week
        fun calculateRecommendedDailyCalories(BMR: Int, goalType: Int, lbsPerWeek: Int) : Int {
            var cals:Int=BMR+((goalType)*((lbsPerWeek*3000)/7))
            Log.d("LOG",cals.toString()+" cals" )
            return cals ;
        }

        /* Calculates the nearest weight a person should be to achieve a healthy BMI. Returns
         the user's current weight if their BMI is healthy. */
        fun calculateIdealWeight(lbs: Int, feet: Int, inches  : Int) : Int {
            // calculate current BMI
            val bmi : Float = calculateBMI(lbs, feet, inches)

            if (bmi == 0F) { // no data entered
                return 0
            }

            // calculate the person's height in inches
            val heightInInches: Double = feet.toFloat() * 12.0 + inches.toFloat()

            var idealWeight : Int;
            if (bmi<19) { // underweight
                idealWeight = (19* heightInInches.pow(2.0) / 703).toInt()
            } else if(bmi<25) { // healthy
                // ideal weight is the person's current weight
                idealWeight = lbs
            }else { // overweight
                idealWeight = (24.9* heightInInches.pow(2.0) / 703).toInt()
            }
            return idealWeight

        }
    }




    override fun toString(): String {
        return "UserModel(uuid='$uuid', firstName='$firstName', lastName='$lastName', age=$age, sex=$sex, feet=$feet, inches=$inches, lbs=$lbs, city='$city', country='$country', profilePicture='$profilePicture', backgroundPicture='$backgroundPicture', goalType=$goalType, lbsPerWeek=$lbsPerWeek, recommendedDailyCalories=$recommendedDailyCalories, isActive=$isActive, BMR=$BMR, BMI=$BMI)"
    }


}