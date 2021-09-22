package com.example.lifestyle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [weatherFragment_Tab.newInstance] factory method to
 * create an instance of this fragment.
 */
class weatherFragment_Tab : Fragment() {
    // TODO: Rename and change types of parameters

    private val url: String? = "https://api.openweathermap.org/data/2.5/weather"
    private  val appid = "ae87a456ab68044c3e6265ca4ec0afa8"
    private val city = "Salt Lake City"
    private  val country = "US"
    var df = DecimalFormat("#.##")

    var addressText: TextView? = null
    var updatedAtText: TextView? = null
    var descriptionText: TextView? = null
    var tempText: TextView? = null
    var tempminText: TextView? = null
    var tempmaxText: TextView? = null
    var sunriseText: TextView? = null
    var sunsetText: TextView? = null
    var windText: TextView? = null
    var pressureText: TextView? = null
    var humidityText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var theWeatherView=inflater.inflate(R.layout.fragment_weather__tab, container, false)

        addressText = theWeatherView.findViewById<View>(R.id.weatherAddress) as TextView
        updatedAtText = theWeatherView.findViewById<TextView>(R.id.updatedAddressAt)
        descriptionText = theWeatherView.findViewById<TextView>(R.id.statusWeather)
        tempText = theWeatherView.findViewById<TextView>(R.id.tempWeather)
        tempminText = theWeatherView.findViewById<TextView>(R.id.temp_minWeather)
        tempmaxText = theWeatherView.findViewById<TextView>(R.id.temp_maxWeather)
        sunriseText = theWeatherView.findViewById<TextView>(R.id.sunriseWeather)
        sunsetText = theWeatherView.findViewById<TextView>(R.id.sunsetWeather)
        windText = theWeatherView.findViewById<TextView>(R.id.windWeather)
        pressureText = theWeatherView.findViewById<TextView>(R.id.pressureWeather)
        humidityText = theWeatherView.findViewById<TextView>(R.id.humidityWeather)
        getWeatherInfo()
        // Inflate the layout for this fragment
        return theWeatherView
    }
    fun getWeatherInfo() {
        val tempurl = "$url?q=$city,$country&appid=$appid"
        val stringRequest = StringRequest(
            Request.Method.POST, tempurl,
            { response ->
                Log.d("response", response!!)
                try {
                    //get data from response
                    val jsonRes = JSONObject(response)
                    val weather = jsonRes.getJSONArray("weather").getJSONObject(0)
                    val main = jsonRes.getJSONObject("main")
                    val sys = jsonRes.getJSONObject("sys")
                    val wind = jsonRes.getJSONObject("wind")
                    val description = weather.getString("description").toUpperCase()
                    var t = main.getDouble("temp") - 273.15
                    val temp = df.format(t) + "°C"
                    t = main.getDouble("temp_min") - 273.15
                    val tempMin = "Min Temp: " + df.format(t) + "°C"
                    t = main.getDouble("temp_max") - 273.15
                    val tempMax = "Max Temp: " + df.format(t) + "°C"
                    val pressure = main.getString("pressure") + " hPa"
                    val humidity = main.getString("humidity") + "%"
                    val sunrise = sys.getLong("sunrise")
                    val sunset = sys.getLong("sunset")
                    val w = wind.getDouble("speed")
                    val windspeed = df.format(w) + " m/s"
                    val location = jsonRes.getString("name") + ", US"
                    val updateAt = jsonRes.getLong("dt")


                    //set data into view
                    addressText!!.text = location
                    updatedAtText!!.text =
                        "Updated at: " + SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                            Date()
                        )
                    descriptionText!!.text = description
                    tempText!!.text = temp
                    tempminText!!.text = tempMin
                    tempmaxText!!.text = tempMax
                    sunriseText!!.text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                    sunsetText!!.text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                    windText!!.text = windspeed
                    pressureText!!.text = pressure
                    humidityText!!.text = humidity

                    //Views populated, Hiding the loader, Showing the main design
                    //findViewById<ProgressBar>(R.id.loaderWeather).visibility = View.GONE
                    //findViewById<RelativeLayout>(R.id.weatherContainer).visibility = View.VISIBLE
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(
                activity,
                error.toString().trim { it <= ' ' },
                Toast.LENGTH_SHORT
            ).show()
        }
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(stringRequest)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment weatherFragment_Tab.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            weatherFragment_Tab().apply {
                arguments = Bundle().apply {

                }
            }
    }
}