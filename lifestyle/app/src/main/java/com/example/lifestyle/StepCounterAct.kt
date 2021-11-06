//package com.example.lifestyle
//
//import android.content.Context
//import android.hardware.Sensor
//import android.hardware.SensorEvent
//import android.hardware.SensorEventListener
//import android.hardware.SensorManager
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//
//class StepCounterAct: AppCompatActivity(), SensorEventListener {
//
//    var running = false
//    var sensorManager: SensorManager? = null
//    private lateinit var stepCounterView : TextView
//    private lateinit var StepCounterViewModel: StepCounterViewModel
//    var muuid:String =""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("LOG", "DuringActive")
//        setContentView(R.layout.step_counter)
//        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//
//        muuid = intent.getExtras()?.getString("uuid")!!
//
//        stepCounterView=findViewById(R.id.stepsValue) as TextView;
//        // Create the view model
//        StepCounterViewModel = ViewModelProvider(this).get(StepCounterViewModel::class.java)
//        // Set the uuid for the view model
//        StepCounterViewModel!!.setUUID(muuid!!)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        running = true
//        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
//
//        if (stepsSensor == null) {
//            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
//        } else {
//            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        running = false
//        sensorManager?.unregisterListener(this)
//    }
//
//    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//    }
//
//    override fun onSensorChanged(event: SensorEvent) {
//        if (running) {
//            stepCounterView.setText("" + event.values[0])
//        }
//    }
//}