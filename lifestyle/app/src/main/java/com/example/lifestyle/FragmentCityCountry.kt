package com.example.lifestyle

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
            view.findNavController().navigate(R.id.action_fragmentCityCountry_to_fragmentProfileAndBackgroundPicture) }
        return binding.root
    }
}