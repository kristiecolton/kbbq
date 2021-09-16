package com.example.lifestyle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding

class FirstAndLastName : Fragment() {

    private var _binding: FragmentFirstAndLastNameBinding? = null
    lateinit var dataPasser: OnDataPass

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstAndLastNameBinding.inflate(inflater, container, false)
        binding.firstAndLastNameNextButton.setOnClickListener { view : View ->
            val firstName: String = binding.firstNameEt.text.toString()
            val lastName: String = binding.lastNameEt.text.toString()
            passData(firstName, lastName)
            view.findNavController().navigate(R.id.action_firstAndLastName_to_fragmentAgeHeightWeight) }

        return binding.root
    }

    interface OnDataPass {
        fun onDataPassFirstAndLastName(firstName: String, lastName: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(firstName: String, lastName: String) {
        dataPasser.onDataPassFirstAndLastName(firstName, lastName)
    }
}