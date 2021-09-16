package com.example.lifestyle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentCityCountryBinding
import com.example.lifestyle.databinding.FragmentSexBinding

class FragmentCityCountry : Fragment() {

    private var _binding: FragmentCityCountryBinding? = null
    lateinit var dataPasser: FragmentCityCountry.OnDataPass

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

        _binding = FragmentCityCountryBinding.inflate(inflater, container, false)
        binding.button5.setOnClickListener { view : View ->
            val city: String = binding.cityEt.text.toString()
            val country: String = binding.countryEt.text.toString()
            passData(city, country)
            view.findNavController().navigate(R.id.action_fragmentCityCountry_to_fragmentProfileAndBackgroundPicture) }
        return binding.root
    }

    interface OnDataPass {
        fun onDataPass(city: String, country: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(city: String, country: String) {
        dataPasser.onDataPass(city, country)
    }
}