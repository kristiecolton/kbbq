package com.example.lifestyle

import android.util.Log
import kotlin.math.pow

class HealthMetricUtils {
    companion object {
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


}

