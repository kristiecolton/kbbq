package com.example.lifestyle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifestyle.databinding.FragmentProfileAndBackgroundPictureBinding
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts


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

            passData("addUser")

        }

        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            binding.profilePicture?.setImageURI(uri)
            passData(uri.toString())
        }

        binding.uploadProfilePictureBtn.setOnClickListener {
            getImage.launch("image/*")
        }

        return binding.root
    }

    interface OnDataPassProfileAndBackgroundPicture {
        fun onDataPassProfileAndBackgroundPicture(firstName: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPassProfileAndBackgroundPicture
    }

    fun passData(data: String) {
        dataPasser.onDataPassProfileAndBackgroundPicture(data)
    }
}

//        val loadImage = registerForActivityResult(ActivityResultContracts.TakePicture(),
//            ActivityResultCallback {
//                binding.profilePicture.setImageURI(it)
//            })
//        binding.uploadProfilePictureBtn.setOnClickListener(View.OnClickListener {
//            loadImage.launch("image/*")
//        })

//        var latestTmpUri: Uri? = null
//        val previewImage by lazy { view?.findViewById<ImageView>(R.id.profile_picture) }
//
//        val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
//            if (isSuccess) {
//                latestTmpUri?.let { uri ->
//                    previewImage?.setImageURI(uri)
//                }
//            }
//        }
//        binding.uploadProfilePictureBtn.setOnClickListener { view : View ->
//            takeImageResult.launch(latestTmpUri)
//        }