package com.example.lifestyle

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class LoginActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var mCreateAProfileBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mCreateAProfileBtn=findViewById(R.id.create_a_profile_btn) as Button;
        mCreateAProfileBtn.setOnClickListener(this);
        var dbManager : DBManager = DBManager(this);
        var usersUIDArray=dbManager.getAllUid()
        var linearLayoutUis=findViewById<LinearLayout>(R.id.users)

        usersUIDArray.forEach{
            val resBtn = ImageButton(this)
            val tv = TextView(this)
            tv.gravity = Gravity.CENTER or Gravity.BOTTOM
            tv.text = dbManager.getFirstName(it) +" " +dbManager.getLastName(it)
            var t=dbManager.getLastName(it)
            Log.d("LOG", "WHY IS THIS SO DUMB: $t")
            resBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.humidity));
            resBtn.setBackgroundColor(Color.TRANSPARENT);
//            linearLayoutUis.addView(resBtn);
            linearLayoutUis.addView(tv);

        }



        // Create a DBManager object



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
        val intent = Intent(this, CreateUserProfile::class.java)
        startActivity(intent)
    }


}