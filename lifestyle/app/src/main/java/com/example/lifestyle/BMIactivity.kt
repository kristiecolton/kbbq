package com.example.lifestyle

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class BMIactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_iactivity)
        setSupportActionBar(findViewById(R.id.toolbar))
        val fragment = BMIFragment.newInstance("pleasework")
        supportFragmentManager
            // 3
            .beginTransaction()
            // 4
            .add(R.id.root_container, BMIFragment.newInstance("das"))
            // 5
            .commit()

    }
}