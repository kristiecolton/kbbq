package com.example.lifestyle

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class CalTracker : AppCompatActivity(),View.OnClickListener {
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
        var BackButton=findViewById(R.id.backButton)as Button
        BackButton.setOnClickListener(this)

    }
    fun showMainActivity(uID:String) {
        val intent = Intent(this, CreateUserProfile::class.java)
        intent.putExtra("uid",uID)
        startActivity(intent)
    }
    override fun onClick(v: View?)
    {
        if (v != null)
            when (v.id)
            {
                R.id.BmiButton->
                {
                    showMainActivity(uuid)

                }



            }

    }
}