package com.example.lifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BmiActivity : AppCompatActivity() ,View.OnClickListener
{
    lateinit var calculateButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        calculateButton=findViewById(R.id.calculate_btn) as Button;
        calculateButton.setOnClickListener(this);
    }
    override fun onClick(v: View?)
    {
        if (v != null)
        {
            when (v.id)
            {
                R.id.calculate_btn->
                {
                    supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_view_NumberBMI, BMIFragment.newInstance("24.4"))
                    .commit()

                    supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_view_stringBMI, BmiStringFragment.newInstance("24.5"))
                    .commit()

                }

            }

        }

    }
}