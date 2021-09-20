package com.example.lifestyle

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import android.Manifest;
import android.util.Log

class MainActivity : AppCompatActivity(),View.OnClickListener, LocationListener
{

    // User's uuid
    var uuid : String = ""

    // Hiking variables
    var longitude: String="40.7608"
    var lattitude: String="-111.8910"
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2

    // UI Elements
    lateinit var bmiButton: Button
    lateinit var HikingButton:Button
    lateinit var weatherButton: Button
    lateinit var editProfileButton : Button
    lateinit var calButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get user'ss uuid from previous activity
        this.uuid = intent.getExtras()?.getString("uuid")!!

        val pieChart = findViewById<PieChart>(R.id.pieChart)
        val Cal = ArrayList<PieEntry>()

        Cal.add(PieEntry(10f, "Cal Precentage"))
        Cal.add(PieEntry(90f, "Cal Failure"))
        val dataSet = PieDataSet(Cal, "Your Cals")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        pieChart.data = data
        pieChart.highlightValues(null)
        pieChart.invalidate()
        pieChart.animateXY(5000, 5000)

        bmiButton=findViewById(R.id.BmiButton) as Button;
        bmiButton.setOnClickListener(this);

        HikingButton=findViewById(R.id.HikingButton) as Button;
        HikingButton.setOnClickListener(this);

        weatherButton = findViewById(R.id.weather_btn)
        weatherButton.setOnClickListener(this)

        editProfileButton = findViewById(R.id.edit_profile_btn)
        editProfileButton.setOnClickListener(this)


        calButton = findViewById(R.id.Cal_btn)
        calButton.setOnClickListener(this)

    }

    override fun onClick(v: View?)
    {
        if (v != null)
            when (v.id)
            {
                R.id.BmiButton->
                {
                    showBMIActivity()

                }
                R.id.HikingButton->
                {
                    getLocation()
                    // Search for Hikes nearby
                    val gmmIntentUri = Uri.parse("geo:"+lattitude+","+longitude+"?q=hikes")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }
                R.id.weather_btn-> {
                    val intent = Intent(this, WeatherActivity::class.java)
                    startActivity(intent)

                }
                R.id.edit_profile_btn -> {
                    val intent = Intent(this, EditProfileActivity::class.java)
                    intent.putExtra("uuid", this.uuid);
                    startActivity(intent)
                }
                R.id.Cal_btn->
                {
                    showCalActivity()
                }


                }

    }
    private fun showBMIActivity() {
        val intent = Intent(this, BmiActivity::class.java)
        intent.putExtra("uuid",uuid)
        startActivity(intent)
    }
    private fun showCalActivity() {
        val intent = Intent(this, CalTracker::class.java)
        intent.putExtra("uuid",uuid)
        startActivity(intent)
    }

    override fun onLocationChanged(location: Location)
    {
        longitude=location.longitude.toString()
        lattitude=location.latitude.toString()
        Toast.makeText(this, "Latitude: " + location.latitude + " , Longitude: " + location.longitude, Toast.LENGTH_SHORT).show()

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
//                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

    }

}


