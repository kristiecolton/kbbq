package com.example.lifestyle

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BMIFragment_Tablet.newInstance] factory method to
 * create an instance of this fragment.
 */
class BMIFragment_Tablet : Fragment() {
    // TODO: Rename and change types of parameters
    private var BMIString: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_b_m_i__tablet, container, false)
        val textViewBmiString = view.findViewById(R.id.StringBmi_tab) as TextView
        val textViewBmiNumber = view.findViewById(R.id.number_bmi_tab) as TextView
        var bmiString=""
        var bmiNumber=BMIString
        val bmiFloat= BMIString?.toFloat()
        if (bmiFloat != null) {
            if (bmiFloat<19)
            {
                bmiString="Underweight"
                textViewBmiString.setTextColor(Color.parseColor("blue"))
                textViewBmiNumber.setTextColor(Color.parseColor("blue"))
            }else if(bmiFloat<25)
            {
                bmiString="Healthy"
                textViewBmiString.setTextColor(Color.parseColor("green"))
                textViewBmiNumber.setTextColor(Color.parseColor("green"))
            }else if (bmiFloat<30)
            {
                bmiString="Overweight"
                textViewBmiString.setTextColor(Color.parseColor("yellow"))
                textViewBmiNumber.setTextColor(Color.parseColor("yellow"))
            }else if(bmiFloat<40){
                bmiString="Obese"
                textViewBmiString.setTextColor(Color.parseColor("red"))
                textViewBmiNumber.setTextColor(Color.parseColor("red"))
            }else
            {
                bmiString="Very Obese"
                textViewBmiString.setTextColor(Color.parseColor("red"))
                textViewBmiNumber.setTextColor(Color.parseColor("red"))
            }
        }else
        {
            bmiString="Not Real Number"
        }
        textViewBmiString.setText(bmiString).toString();
        textViewBmiNumber.setText(bmiNumber).toString();
        // Inflate the layout for this fragment
        return view
        // Inflate the layout for this fragment

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BMIFragment_Tablet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(BMI: String) =
            BMIFragment_Tablet().apply {
                arguments = Bundle().apply{
                    BMIString=BMI
                }
            }
    }
}