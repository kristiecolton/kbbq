package com.example.lifestyle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.navigation.findNavController
import com.example.lifestyle.databinding.ActivityCreateProfileBinding.inflate
import com.example.lifestyle.databinding.ActivityCreateUserProfileBinding.inflate
import com.example.lifestyle.databinding.FragmentFirstAndLastNameBinding
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding.inflate
import com.example.lifestyle.databinding.FragmentSexBinding.inflate
import android.content.Intent




class FragmentProfileAndBackgroundPicture : Fragment() {

    private var _binding: FragmentProfileAndBackgroundPictureBinding? = null
    lateinit var dataPasser: FragmentProfileAndBackgroundPicture.OnDataPassProfileAndBackgroundPicture

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

        _binding = FragmentProfileAndBackgroundPictureBinding.inflate(inflater, container, false)
        binding.submitButton.setOnClickListener { view : View ->

            passData("Hey", "Hey")

            // view.findNavController().navigate(R.id.action_fragmentProfileAndBackgroundPicture_to_mainActivity)
        }

        return binding.root
    }

    interface OnDataPassProfileAndBackgroundPicture {
        fun onDataPassProfileAndBackgroundPicture(firstName: String, lastName: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPassProfileAndBackgroundPicture
    }

    fun passData(firstName: String, lastName: String) {
        dataPasser.onDataPassProfileAndBackgroundPicture(firstName, lastName)
    }
}