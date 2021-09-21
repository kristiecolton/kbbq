package com.example.lifestyle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class WeatherActivity extends AppCompatActivity {
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "ae87a456ab68044c3e6265ca4ec0afa8";
    private final String city = "Salt Lake City";
    private final String country = "US";
    DecimalFormat df = new DecimalFormat("#.##");

    TextView addressText;
    TextView updatedAtText;
    TextView descriptionText;
    TextView tempText;
    TextView tempminText;
    TextView tempmaxText;
    TextView sunriseText;
    TextView sunsetText;
    TextView windText;
    TextView pressureText;
    TextView humidityText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        addressText =  (TextView) findViewById(R.id.weatherAddress);
        updatedAtText = findViewById(R.id.updatedAddressAt);
        descriptionText = findViewById(R.id.statusWeather);
        tempText = findViewById(R.id.tempWeather);
        tempminText = findViewById(R.id.temp_minWeather);
        tempmaxText = findViewById(R.id.temp_maxWeather);
        sunriseText = findViewById(R.id.sunriseWeather);
        sunsetText = findViewById(R.id.sunsetWeather);
        windText = findViewById(R.id.windWeather);
        pressureText = findViewById(R.id.pressureWeather);
        humidityText = findViewById(R.id.humidityWeather);
        getWeatherInfo();
    }

    public void getWeatherInfo(){
        String tempurl = url + "?q=" + city + "," + country + "&appid=" + appid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                try{
                    //get data from response

                    JSONObject jsonRes = new JSONObject(response);
                    JSONObject weather = jsonRes.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = jsonRes.getJSONObject("main");
                    JSONObject sys = jsonRes.getJSONObject("sys");
                    JSONObject wind = jsonRes.getJSONObject("wind");

                    String description = weather.getString("description").toUpperCase();

                    double t = main.getDouble("temp") - 273.15;
                    String temp = df.format(t) + "°C";
                    t = main.getDouble("temp_min") - 273.15;
                    String tempMin = "Min Temp: " + df.format(t)+ "°C";
                    t = main.getDouble("temp_max") - 273.15;
                    String tempMax = "Max Temp: " + df.format(t)+ "°C";
                    String pressure = main.getString("pressure") + " hPa";
                    String humidity = main.getString("humidity") + "%";

                    Long sunrise = sys.getLong("sunrise");
                    Long sunset = sys.getLong("sunset");

                    double w = wind.getDouble("speed");
                    String windspeed = df.format(w) + " m/s";

                    String location = jsonRes.getString("name") + ", US" ;
                    Long updateAt = jsonRes.getLong("dt");


                    //set data into view

                    addressText.setText(location);
                    updatedAtText.setText("Updated at: " + new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                    descriptionText.setText(description);
                    tempText.setText(temp);
                    tempminText.setText(tempMin);
                    tempmaxText.setText(tempMax);
                    sunriseText.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunrise*1000)));
                    sunsetText.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunset*1000)));
                    windText.setText(windspeed);
                    pressureText.setText(pressure);
                    humidityText.setText(humidity);

                    //Views populated, Hiding the loader, Showing the main design
                    //findViewById<ProgressBar>(R.id.loaderWeather).visibility = View.GONE
                    //findViewById<RelativeLayout>(R.id.weatherContainer).visibility = View.VISIBLE

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
