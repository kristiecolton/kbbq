package com.example.lifestyle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.Button

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var mCreateAProfileBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mCreateAProfileBtn=findViewById(R.id.create_a_profile_btn) as Button;
        mCreateAProfileBtn.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.create_a_profile_btn->{
                    showCreateAProfileActivity()
                }
                else -> { // Note the block
                    print("Invalid button press.")
                }
            }
        }

    }

    fun showCreateAProfileActivity() {
        val intent = Intent(this, CreateProfileActivity::class.java)
        startActivity(intent)
    }


}