package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class CalTracker : AppCompatActivity() {
    private lateinit var mCalTextView : TextView
    var muuid:String =""
    private var mCalTrackerViewModel: CalTrackerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cal_tracker)
        muuid = intent.getExtras()?.getString("uuid")!!

        // Create the view model
        mCalTrackerViewModel = ViewModelProvider(this).get(CalTrackerViewModel::class.java)
        // Set the uuid for the view model
        mCalTrackerViewModel!!.setUUID(muuid!!)
        //Set the observer
        mCalTrackerViewModel!!.getRecommendedDailyCalories().observe(this, nameObserver)

        mCalTextView=findViewById(R.id.Cal_tv)as TextView

    }

    // Create an observer that watches the LiveData<List<UserTable>> object
    val nameObserver: Observer<Int?> =
        Observer<Int?> { calories ->
            if (calories != null) {
                mCalTextView.setText(calories.toString())
            }
        }


}