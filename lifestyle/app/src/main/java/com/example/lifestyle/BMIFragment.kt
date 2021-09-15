package com.example.lifestyle

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
            BMIString = it.getString(ARG_PARAM1)

        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view: View = inflater.inflate(R.layout.fragment_b_m_i, container,
                false)
        val textView = view.findViewById(R.id.BMI_tv) as TextView
        val testString=BMIString
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
                    putString(BMIString, param1)

                }
            }
    }
}