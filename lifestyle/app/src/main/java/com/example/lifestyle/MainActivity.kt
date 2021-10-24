package com.example.lifestyle

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import android.Manifest;
import android.graphics.Color
import android.graphics.Color.*
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class MainActivity : AppCompatActivity(),View.OnClickListener, LocationListener
{

    // User's uuid
    var muuid : String = ""

    var cals:String=""
    var bmiString:String=""

    var user : UserData= UserData()
    lateinit var mDBManager: DBManager
    var bmi : Float = 0F



    // For Fragement
    var frag: Fragment? =null
    // Hiking variables
    var longitude: String="40.7608"
    var lattitude: String="-111.8910"
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2

    // UI Elements
    lateinit var mProfilePicture : ImageView
    lateinit var bmiButton: Button
    lateinit var HikingButton:Button
    lateinit var weatherButton: Button
    lateinit var editProfileButton : Button
    lateinit var calButton : Button

    // View Model
    private var mMainViewModel: MainViewModel? = null

    // var dataBase:DBManager= DBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isTablet())
        {
            setContentView(R.layout.activity_main_tablet)
        }else{
            setContentView(R.layout.activity_main)
        }

        bmiButton=findViewById(R.id.BmiButton) as Button;
        bmiButton.setOnClickListener(this);

        HikingButton=findViewById(R.id.Hiking) as Button;
        HikingButton.setOnClickListener(this);

        weatherButton = findViewById(R.id.weather_btn)
        weatherButton.setOnClickListener(this)

        calButton = findViewById(R.id.Cal_btn)
        calButton.setOnClickListener(this)

        editProfileButton = findViewById(R.id.edit_profile_btn)
        editProfileButton.setOnClickListener(this)


        // Get user's uuid from previous activity
        muuid = intent.getExtras()?.getString("uuid")!!

        // Create the view model
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Set the uuid for the view model
        mMainViewModel!!.setUUID(muuid!!)
        //Set the observer
        mMainViewModel!!.getUser().observe(this, nameObserver)


    }


    // Create an observer that watches the LiveData<List<UserTable>> object
    val nameObserver: Observer<UserTable> =
        Observer<UserTable> { user ->
            if (user != null) {
                // this.bmi = UserData.calculateBMI(user.lbs, user.feet, user.inches )

                val idealWeight : Int = HealthMetricUtils.calculateIdealWeight(user.COLUMN_LBS!!, user.COLUMN_FEET!!, user.COLUMN_INCHES!!)
                if (idealWeight == 0) { // not enough data to calculate weight properly, do nothing!

                } else {
                    val pieChart = findViewById<PieChart>(R.id.pieChart)
                    val Cal = ArrayList<PieEntry>()

                    var dataSet : PieDataSet;

                    if (user.COLUMN_LBS < idealWeight) { // underweight
                        Cal.add(PieEntry(user.COLUMN_LBS!!.toFloat(), "Current weight"))
                        Cal.add(PieEntry((idealWeight-user.COLUMN_LBS.toFloat()), "Pounds to gain"))
                        dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
                    } else if (user.COLUMN_LBS == idealWeight) { // healthy weight
                        Cal.add(PieEntry(user.COLUMN_LBS.toFloat(), "Current weight"))
                        Cal.add(PieEntry(0f, "Pounds to gain"))
                        dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
                    } else { // overweight
                        Cal.add(PieEntry(user.COLUMN_LBS.toFloat(), "Current weight"))
                        Cal.add(PieEntry((user.COLUMN_LBS - idealWeight).toFloat(), "Pounds to lose"))
                        dataSet = PieDataSet(Cal, "Pounds To Lose For Healthy BMI")
                    }

                    dataSet.setDrawIcons(false)
                    dataSet.sliceSpace = 3f
                    dataSet.iconsOffset = MPPointF(0F, 40F)
                    dataSet.selectionShift = 5f
                    dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

                    val data = PieData(dataSet)
                    pieChart.data = data
                    pieChart.highlightValues(null)
                    pieChart.invalidate()
                    pieChart.getDescription().setEnabled(false)
                    pieChart.setEntryLabelColor(WHITE)
                    pieChart.setHoleColor(TRANSPARENT)
                    pieChart.legend.setTextColor(WHITE)
                    pieChart.legend.setTextSize(11f)
                    pieChart.animateXY(5000, 5000)

                }
            }
        }

    override fun onClick(v: View?)
    {
        if (v != null)
            when (v.id)
            {
                R.id.BmiButton->
                {
                    if (isTablet())
                    {




                        for (fragment in supportFragmentManager.fragments) {
                            supportFragmentManager.beginTransaction().remove(fragment!!)
                                .commit()
                        }
                        supportFragmentManager
                            .beginTransaction()
                            .add(R.id.fragment_container_main, BMIFragment_Tablet.newInstance(bmiString),"lol")
                            .commit()


                    }else{
                        showBMIActivity()

                    }


                }
                R.id.Hiking->
                {

                    getLocation()
                    // Search for Hikes nearby
                    val gmmIntentUri = Uri.parse("geo:"+lattitude+","+longitude+"?q=hikes")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }
                R.id.weather_btn-> {
                    if(isTablet()) {
                        for (fragment in supportFragmentManager.fragments) {
                            supportFragmentManager.beginTransaction().remove(fragment!!)
                                .commit()
                        }
                        supportFragmentManager
                            .beginTransaction()
                            .add(R.id.fragment_container_main, weatherFragment_Tab.newInstance(),"lol")
                            .commit()


                    }else
                    {
                        val intent = Intent(this, WeatherActivity::class.java)
                        startActivity(intent)

                    }

                }
                R.id.edit_profile_btn -> {
                    val intent = Intent(this, EditProfileActivity::class.java)
                    intent.putExtra("uuid", this.muuid);
                    startActivity(intent)
                }
                R.id.Cal_btn->
                {
                    if(isTablet())
                    {
//


                            for (fragment in supportFragmentManager.fragments) {
                                supportFragmentManager.beginTransaction().remove(fragment!!)
                                    .commit()
                            }
//
                        supportFragmentManager
                            .beginTransaction()
                            .add(R.id.fragment_container_main, CalTrackerFragment.newInstance(cals),"lol")
                            .commit()


                    }else
                    {
                        showCalActivity()
                    }

                }





                }

    }
    private fun showBMIActivity() {
        val intent = Intent(this, BmiActivity::class.java)
        intent.putExtra("uuid",muuid)
        startActivity(intent)
    }
    private fun showCalActivity() {
        val intent = Intent(this, CalTracker::class.java)
        intent.putExtra("uuid",muuid)
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

    fun isTablet(): Boolean {
        return resources.getBoolean(R.bool.isTablet)
    }

}


