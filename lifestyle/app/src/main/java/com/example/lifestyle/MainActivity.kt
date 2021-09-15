package com.example.lifestyle

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class MainActivity : AppCompatActivity(),View.OnClickListener
{
    lateinit var bmiButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        bmiButton=findViewById(R.id.button) as Button;
        bmiButton.setOnClickListener(this);


    }
    override fun onClick(v: View?)
    {
        if (v != null)
        {
            when (v.id)
            {
                R.id.button->
                {
                    showBMIActivity()

                }

                }

        }

    }
    private fun showBMIActivity() {
        val intent = Intent(this, BmiActivity::class.java)
        startActivity(intent)
    }

}
