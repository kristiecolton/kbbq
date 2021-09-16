package com.example.lifestyle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentAgeHeightWeightBinding
import com.example.lifestyle.databinding.FragmentSexBinding

class FragmentSex : Fragment() {

    private var _binding: FragmentSexBinding? = null

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

        _binding = FragmentSexBinding.inflate(inflater, container, false)
        binding.button4.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_fragmentSex_to_fragmentCityCountry) }
        return binding.root
    }
}