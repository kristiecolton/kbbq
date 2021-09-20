package com.example.lifestyle

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {
    val CITY: String = "l,us"
    val API: String = "ae87a456ab68044c3e6265ca4ec0afa8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        //weatherTask().execute()
    }

    inner class weatherTask():AsyncTask<String, Void, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            //Showing the ProgressBar, Making the main design GONE
            findViewById<ProgressBar>(R.id.loaderWeather).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.weatherContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorMessageWeather).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                //api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$API
                //api.openweathermap.org/data/2.5/weather?q=London,uk&appid=$API
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=$API").readText(
                    Charsets.UTF_8
                )
                //response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                //Charsets.UTF_8
                //)
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                //JSON returns from the API
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                //data into our views

                findViewById<TextView>(R.id.weatherAddress).text = address
                findViewById<TextView>(R.id.updatedAddressAt).text =  updatedAtText
                findViewById<TextView>(R.id.statusWeather).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.tempWeather).text = temp
                findViewById<TextView>(R.id.temp_minWeather).text = tempMin
                findViewById<TextView>(R.id.temp_maxWeather).text = tempMax
                findViewById<TextView>(R.id.sunriseWeather).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunsetWeather).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.windWeather).text = windSpeed
                findViewById<TextView>(R.id.pressureWeather).text = pressure
                findViewById<TextView>(R.id.humidityWeather).text = humidity

                //Views populated, Hiding the loader, Showing the main design
                findViewById<ProgressBar>(R.id.loaderWeather).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.weatherContainer).visibility = View.VISIBLE


            } catch (e: Exception) { //if any error
                findViewById<ProgressBar>(R.id.loaderWeather).visibility = View.GONE
                findViewById<TextView>(R.id.errorMessageWeather).visibility = View.VISIBLE
            }

        }
    }
}