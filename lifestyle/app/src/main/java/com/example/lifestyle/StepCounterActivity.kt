package com.example.lifestyle

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.Sensor.*
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.ViewModelProvider
import java.security.AccessController.getContext

class StepCounterActivity : AppCompatActivity(),SensorEventListener {
    var running = false
    var sensorManager: SensorManager? = null
    private lateinit var stepCounterView: TextView
    private lateinit var StepCounterViewModel: StepCounterViewModel
    var muuid: String = ""
    var stepsSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 1001
            )
            Log.d("log", "fick")

            // Permission is not granted

        } else {


        }
        setContentView(R.layout.content_step_counter)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

//        muuid = intent.getExtras()?.getString("uuid")!!
//
        stepCounterView = findViewById(R.id.steps_tv) as TextView;
//        // Create the view model
//        StepCounterViewModel = ViewModelProvider(this).get(StepCounterViewModel::class.java)
//        // Set the uuid for the view model
//        StepCounterViewModel!!.setUUID(muuid!!)
    }

    override fun onResume() {
        super.onResume()
        running = true
        Log.d("LOG", "On Resume")


        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {
            Log.d("LOG", "on change")
            stepCounterView.setText("" + event.values[0])
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val action: Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("DEBUG_TAG", "Action was DOWN")
                running=false
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d("DEBUG_TAG", "Action was UP")
                running=true
                true
            }

            else -> super.onTouchEvent(event)
        }
    }
}