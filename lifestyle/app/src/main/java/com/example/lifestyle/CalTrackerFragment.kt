package com.example.lifestyle

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
 * Use the [CalTrackerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalTrackerFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var cals : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment


//
//        var dataBase:DBManager= DBManager(this)
//
//        var calTextView=findViewById(R.id.Cal_tv)as TextView
//        calTextView.text = dataBase.getCals(uuid)



        val view: View = inflater.inflate(R.layout.fragment_cal_tracker, container,
                false)
        val textView = view.findViewById(R.id.cals_tv_frag) as TextView
        textView.text= cals



        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                CalTrackerFragment().apply {
                    arguments = Bundle().apply{
                        cals=param1

                    }
                }
    }
}