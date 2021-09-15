package com.example.lifestyle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentAgeHeightWeightBinding
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding

class FirstAndLastName : Fragment() {

    private var _binding: FragmentFirstAndLastNameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstAndLastNameBinding.inflate(inflater, container, false)
        binding.button2.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_firstAndLastName_to_fragmentAgeHeightWeight) }
        return binding.root
    }


}