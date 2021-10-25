package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class BmiActivity : AppCompatActivity()
{
    var muuid:String =""
    private var mBMIViewModel: BMIViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        muuid = intent.getExtras()?.getString("uuid")!!

        // Create the view model
        mBMIViewModel = ViewModelProvider(this).get(BMIViewModel::class.java)
        // Set the uuid for the view model
        mBMIViewModel!!.setUUID(muuid!!)
        //Set the observer
        mBMIViewModel!!.getBMI().observe(this, nameObserver)

    }

    // Create an observer that watches the LiveData<List<UserTable>> object
    val nameObserver: Observer<Float?> =
        Observer<Float?> { bmi ->
            if (bmi != null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_view_NumberBMI, BMIFragment.newInstance(bmi.toString()))
                    .commit()

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_view_stringBMI, BmiStringFragment.newInstance(bmi.toString()))
                    .commit()
            }
        }

}