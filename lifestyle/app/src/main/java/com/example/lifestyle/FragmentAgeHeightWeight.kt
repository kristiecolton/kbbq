package com.example.lifestyle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentAgeHeightWeightBinding
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding

class FragmentAgeHeightWeight : Fragment() {

    private var _binding: FragmentAgeHeightWeightBinding? = null

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
            view.findNavController().navigate(R.id.action_fragmentAgeHeightWeight_to_fragmentSex) }
        return binding.root
    }
}