package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CalTracker : AppCompatActivity() {
    // User's uuid
    var uuid : String = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cal_tracker)
        this.uuid = intent.getExtras()?.getString("uuid")!!
        var dataBase:DBManager= DBManager(this)

        var calTextView=findViewById(R.id.Cal_tv)as TextView
        calTextView.text = dataBase.getCals(uuid)

    }

}