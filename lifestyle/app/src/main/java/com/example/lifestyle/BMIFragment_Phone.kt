package com.example.lifestyle

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [BMIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BMIFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var BMIString: String? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view: View = inflater.inflate(R.layout.fragment_b_m_i, container,
                false)
        val textView = view.findViewById(R.id.BMINumber_tv) as TextView
        var testString=BMIString

        val bmiFloat= BMIString?.toFloat()
        if (bmiFloat != null) {
            if (bmiFloat<19)
            {

                textView.setTextColor(Color.parseColor("blue"))
            }else if(bmiFloat<25)
            {

                textView.setTextColor(Color.parseColor("green"))
            }else if (bmiFloat<30)
            {

                textView.setTextColor(Color.parseColor("yellow"))
            }else if(bmiFloat<40){

                textView.setTextColor(Color.parseColor("red"))
            }else
            {

                textView.setTextColor(Color.parseColor("red"))
            }
        }else
        {
            var testString=""
        }
        textView.setText(testString).toString();
        // Inflate the layout for this fragment
        return view

    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BMIFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            BMIFragment().apply {
                arguments = Bundle().apply {

                    BMIString=param1

                }
            }
    }
}