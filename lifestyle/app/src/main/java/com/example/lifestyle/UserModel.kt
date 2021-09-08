package com.example.lifestyle
//  A class to represent a user
class UserModel {
    // A unique identifier to identify users by
    private var uuid : String = "";
    private var firstName : String = "";
    private var lastName : String = "";
    private var age : Int = -1;

    // Height
    private var feet: Int = -1;
    private var inches: Int = -1;

    // Weight
    private var lbs: Int = -1;

    private var city: String = "";
    private var country: String = "";
    // The path to the user's profile picture
    private var profilePicture: String = "";
    // The path to the user's background picture;
    private var backgroundPicture: String = "";
    // Represents whether the user wants to lose, maintain, or gain  weight.
    // Lose Weight = -1, Maintain Weight = 0, Gain Weight = 1
    private var goalType: Int = 0;
    // Represents the number of pounds a user desires to gain or lose per week.
    // This value should be zero if the user wants to maintain their
    // weight.
    private var lbsPerWeek: Int = 0;
    // Represents the recommended calories per day for the user
    private var recommendedDailyCalories : Int = 0;
    // Represents the user’s Basal Metabolic Rate
    private var BMR : Int = 0;
    // Represents the user’s Body Mass Index
    private var BMI : Int = 0;

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
        feet: Int,
        inches: Int,
        lbs: Int,
        city: String,
        country: String,
        profilePicture: String,
        backgroundPicture: String,
        goalType: Int,
        lbsPerWeek: Int
    ) {
        this.uuid = uuid
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.feet = feet
        this.inches = inches
        this.lbs = lbs
        this.city = city
        this.country = country
        this.profilePicture = profilePicture
        this.backgroundPicture = backgroundPicture
        this.goalType = goalType
        this.lbsPerWeek = lbsPerWeek
    }

    // A constructor that initializes every member variable
    constructor(
        uuid: String,
        firstName: String,
        lastName: String,
        age: Int,
        feet: Int,
        inches: Int,
        lbs: Int,
        city: String,
        country: String,
        profilePicture: String,
        backgroundPicture: String,
        goalType: Int,
        lbsPerWeek: Int,
        recommendedDailyCalories: Int,
        BMR: Int,
        BMI: Int)
    {
        this.uuid = uuid
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.feet = feet
        this.inches = inches
        this.lbs = lbs
        this.city = city
        this.country = country
        this.profilePicture = profilePicture
        this.backgroundPicture = backgroundPicture
        this.goalType = goalType
        this.lbsPerWeek = lbsPerWeek
        this.recommendedDailyCalories = recommendedDailyCalories
        this.BMR = BMR
        this.BMI = BMI
    }

    /* Health Metric Methods */

    // Calculates BMR using a user’s weight and height
    private fun calculateBMR(lbs: Int, feet: Int, inches: Int) : Int {
        // TODO
        return 0;
    }

    // Calculates BMI using a user’s weight and height
    private fun calculateBMI(lbs: Int, feet: Int, inches: Int) : Float {
        // TODO
        return 0F;
    }

    // Calculates the user’s recommended daily calories based on their
    // BMR, type of goal (lose, maintain or gain weight) and the amount // of weight they’d like to change per week
    private fun calculateRecommendedDailyCalories(BMR: Int, goalType: Int, lbsPerWeek: Int) : Int {
        // TODO
        return 0;
    }




}