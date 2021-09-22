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
import android.widget.ImageView
import androidx.core.net.toUri

class MainActivity : AppCompatActivity(),View.OnClickListener, LocationListener
{

    // User's uuid
    var uuid : String = ""
    lateinit var user : UserModel
    lateinit var mDBManager: DBManager
    var bmi : Float = 0F

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get user's uuid from previous activity
        this.uuid = intent.getExtras()?.getString("uuid")!!

        // Create a DBManager object
        mDBManager = DBManager(this);

        try {

            // Get the user's info from the database
            this.user = mDBManager.getUser(uuid!!)

        } catch (e: Exception) {
            this.user = UserModel()
        }
        this.bmi = UserModel.calculateBMI(user.lbs, user.feet, user.inches )

        val idealWeight : Int = UserModel.calculateIdealWeight(user.lbs, user.feet, user.inches)
        if (idealWeight == 0) { // not enough data to calculate weight properly

        } else {
            val pieChart = findViewById<PieChart>(R.id.pieChart)
            val Cal = ArrayList<PieEntry>()

            var dataSet : PieDataSet;

            if (user.lbs < idealWeight) { // underweight
                Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                Cal.add(PieEntry((idealWeight-user.lbs).toFloat(), "Pounds to gain"))
                dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
            } else if (user.lbs == idealWeight) { // healthy weight
                Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                Cal.add(PieEntry(0f, "Pounds to gain"))
                dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
            } else { // overweight
                Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                Cal.add(PieEntry((user.lbs - idealWeight).toFloat(), "Pounds to lose"))
                dataSet = PieDataSet(Cal, "Pounds To Lose For Healthy BMI")
            }

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
            pieChart.getDescription().setEnabled(false)
            pieChart.setCenterTextColor(Color.GREEN)
            pieChart.animateXY(5000, 5000)

        }

        mProfilePicture = findViewById(R.id.imageView1)
        if (this.user.profilePicture != "") { // Check if the user has a profile picture to show
            var profile_pic_uri : Uri = user.profilePicture.toUri()
            mProfilePicture.setImageURI(profile_pic_uri)

        }

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

    /* Note: This method gets called after we navigate back to the Main Activity from a different
     activity. In the case of Edit Profile, the Main Activity does not get re-created after
     navigating back from the Edit Profile activity.  Thus, we need to check for changes that
     may have been saved to the database, but are not reflected in MainActivity.user
     (like a different BMI or profile picture) since these affect the main activity UI */
    override fun onStart() {
        super.onStart()

        // Check if the user has changed their profile picture
        if (this.user.profilePicture != mDBManager.getProfilePictureURI(this.uuid)) {
            mProfilePicture.setImageURI( mDBManager.getProfilePictureURI(this.uuid).toUri())
        }

        val curr_bmi : Float = mDBManager.getBMI(this.uuid).toFloat()
        if (user.BMI != curr_bmi) {
            // Update the user's info
            this.user = mDBManager.getUser(this.uuid)
            val idealWeight : Int = UserModel.calculateIdealWeight(user.lbs, user.feet, user.inches)

            if (idealWeight == 0) { // not enough data to calculate weight properly

            } else {
                val pieChart = findViewById<PieChart>(R.id.pieChart)
                val Cal = ArrayList<PieEntry>()

                var dataSet : PieDataSet;

                if (user.lbs < idealWeight) { // underweight
                    Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                    Cal.add(PieEntry((idealWeight-user.lbs).toFloat(), "Pounds to gain"))
                    dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
                } else if (user.lbs == idealWeight) { // healthy weight
                    Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                    Cal.add(PieEntry(0f, "Pounds to gain"))
                    dataSet = PieDataSet(Cal, "Pounds To Gain For Healthy BMI")
                } else { // overweight
                    Cal.add(PieEntry(user.lbs.toFloat(), "Current weight"))
                    Cal.add(PieEntry((user.lbs - idealWeight).toFloat(), "Pounds to lose"))
                    dataSet = PieDataSet(Cal, "Pounds To Lose For Healthy BMI")
                }

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
                pieChart.getDescription().setEnabled(false)
                pieChart.setCenterTextColor(Color.GREEN)
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


