package com.example.lifestyle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentAgeHeightWeightBinding

class FragmentAgeHeightWeight : Fragment() {

    private var _binding: FragmentAgeHeightWeightBinding? = null
    lateinit var dataPasser: FragmentAgeHeightWeight.OnDataPass

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAgeHeightWeightBinding.inflate(inflater, container, false)
        binding.button3.setOnClickListener { view : View ->
            val age: String = binding.ageEt.text.toString()
            val feet: String = binding.feetEt.text.toString()
            val inches: String = binding.inchesEt.text.toString()
            val lbs: String = binding.lbsEt.text.toString()

            passData(age, feet, inches, lbs)
            view.findNavController().navigate(R.id.action_fragmentAgeHeightWeight_to_fragmentSex) }
        return binding.root
    }

    interface OnDataPass {
        fun onDataPass(age: String, feet: String, inches: String, lbs: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(age: String, feet: String, inches: String, lbs: String) {
        dataPasser.onDataPass(age, feet, inches, lbs)
    }
}