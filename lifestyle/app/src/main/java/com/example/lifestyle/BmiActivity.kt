package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BmiActivity : AppCompatActivity()
{
    var uuid:String =""
    var dataBase:DBManager= DBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


        this.uuid = intent.getExtras()?.getString("uuid")!!



        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view_NumberBMI, BMIFragment.newInstance(dataBase.getBMI(uuid)))
                .commit()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view_stringBMI, BmiStringFragment.newInstance(dataBase.getBMI(uuid)))
                .commit()

    }

}