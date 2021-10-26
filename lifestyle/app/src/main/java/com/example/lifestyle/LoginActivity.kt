package com.example.lifestyle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.concurrent.thread


class LoginActivity : AppCompatActivity(), View.OnClickListener,  MyRVAdapter.DataPasser {
    /* UI Elements */
    lateinit var mCreateAProfileBtn: Button;
    private var mMasterListFragment: MasterListFragment? = null
    private val locationPermissionCode = 2

    private lateinit var mLoginViewModel : LoginViewModel

    // Contains all the user's data for the recycle view
    lateinit var mCustomListData : CustomListData
    private val PERMISSION_CODE = 1001;

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            }
        }


        //Create the view model
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        //Set the observer
        mLoginViewModel.getCustomListDataItems().observe(this, nameObserver)

        // Set click listener for Create a Profile Button
        mCreateAProfileBtn=findViewById(R.id.create_a_profile_btn) as Button;
        mCreateAProfileBtn.setOnClickListener(this);

    }

    // Create an observer that watches the LiveData<List<CustomListDataItem>> object
    val nameObserver: Observer<List<CustomListDataItem>> =
        Observer<List<CustomListDataItem>> { customListDataItems ->
            Log.d("LOG", "waffles")
            if (customListDataItems != null) {
                Log.d("LOG", "pancakes")
                // Create the Custom List Data for the "Select a Profile" Recycle view
                var names : ArrayList<String> = ArrayList()
                var uuids : ArrayList<String> = ArrayList()

                customListDataItems.forEach{
                    uuids.add(it.uuid!!)

                    val firstName : String = it.firstName!!
                    val lastName : String = it.lastName!!
                    val fullName = firstName + " " + lastName
                    names.add(fullName)
                }

                mCustomListData = CustomListData(names, uuids)

                //Put this into a bundle
                val fragmentBundle = Bundle()
                fragmentBundle.putParcelable("item_list", mCustomListData)

                //Create the fragment
                mMasterListFragment = MasterListFragment()

                //Pass data to the fragment
                mMasterListFragment!!.arguments = fragmentBundle

                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(
                        R.id.fl_frag_masterlist_container_phone,
                        mMasterListFragment!!,
                        "frag_masterlist")
                fTrans.commit()
            }
        }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.create_a_profile_btn->{

                    showCreateAProfileActivity()
                }

                else -> { // Note the block
                    print("Invalid button press.")
                }
            }
        }

    }

    fun showCreateAProfileActivity() {
        val intent = Intent(this, CreateUserProfile::class.java)
        startActivity(intent)
    }

    fun showMainActivity(uID:String) {
        val intent = Intent(this, CreateUserProfile::class.java)
        intent.putExtra("uid",uID)
        startActivity(intent)
    }


    override fun passData(position: Int) {
        //Get the uuid of the profile tapped
        val uuid : String = mCustomListData.getItemDetail(position)!!

        //Put the uuid into a bundle
        val detailBundle = Bundle()
        detailBundle.putString("uuid", uuid)

        //Start MainActivity, pass the uuid along
        val sendIntent = Intent(this, MainActivity::class.java)
        sendIntent.putExtras(detailBundle)
        startActivity(sendIntent)
    }

}