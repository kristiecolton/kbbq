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
 * Use the [BmiStringFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BmiStringFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    private var BMIString: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//
        val view: View = inflater.inflate(R.layout.fragment_bmi_string, container, false)
        val textView = view.findViewById(R.id.BMIString_tv) as TextView
        var testString=""

        val bmiFloat= BMIString?.toFloat()
        if (bmiFloat != null) {
            if (bmiFloat<19)
            {
                testString="Underweight"
                textView.setTextColor(Color.parseColor("blue"))
            }else if(bmiFloat<25)
            {
                testString="Healthy"
                textView.setTextColor(Color.parseColor("green"))
            }else if (bmiFloat<30)
            {
                testString="Overweight"
                textView.setTextColor(Color.parseColor("yellow"))
            }else if(bmiFloat<40){
                testString="Obese"
                textView.setTextColor(Color.parseColor("red"))
            }else
            {
                testString="Very Obese"
                textView.setTextColor(Color.parseColor("red"))
            }
        }else
        {
            testString="Not Real Number"
        }
        textView.setText(testString).toString();


//
//
//
//        val txtViewNumber = view.findViewById(R.id.BMINumber_tv) as TextView
//        var testString=BMIString
//
//        val bmiFloatForString= BMIString?.toFloat()
//        if (bmiFloatForString != null) {
//            if (bmiFloatForString<19)
//            {
//
//                txtViewNumber.setTextColor(Color.parseColor("blue"))
//            }else if(bmiFloatForString<25)
//            {
//
//                txtViewNumber.setTextColor(Color.parseColor("green"))
//            }else if (bmiFloatForString<30)
//            {
//
//                txtViewNumber.setTextColor(Color.parseColor("yellow"))
//            }else if(bmiFloatForString<40){
//
//                txtViewNumber.setTextColor(Color.parseColor("red"))
//            }else
//            {
//
//                txtViewNumber.setTextColor(Color.parseColor("red"))
//            }
//        }else
//        {
//            var testString=""
//        }
//        txtViewNumber.setText(testString).toString();
//        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BmiStringFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            BmiStringFragment().apply {
                arguments = Bundle().apply {

                    BMIString=param1

                }
            }
    }
}