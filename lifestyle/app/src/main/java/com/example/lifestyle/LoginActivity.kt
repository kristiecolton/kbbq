package com.example.lifestyle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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


        // Get all users' data for "Select a Profile" Recycle View
 //       var dbManager : DBManager = DBManager(this);
//        var viewModel:ViewModelUserRoom=ViewModelUserRoom(application)
//        var neverGoingToWork=viewModel.getAllUUIDs()
//        thread {
//            viewModel.insertMultipleUsers(
//                UserTable(
//                    "10294031",
//                    "braden",
//                    "mcclean",
//                    25,
//                    1,
//                    6,
//                    1,
//                    220,
//                    "lehi",
//                    "usa",
//                    null,
//                    null,
//                    1,
//                    200,
//                    300,
//                    false,
//                    2103,
//                    23.0f
//                )
//            )
//        }

        // Get a list of all uuids saved in the database
 //       var uuids =dbManager.getAllUuids().toCollection(ArrayList())

        //Populate the item list with data
        //and populate the details list with details at the same time
 //       var names : ArrayList<String> = ArrayList()


//        uuids.forEach{
//            val firstName : String = dbManager.getFirstName(it)
//            val lastName : String = dbManager.getLastName(it)
//            val fullName = firstName + " " + lastName
//
//            names.add(fullName)
//
//        }
//        mCustomListData = CustomListData(names, uuids)

        //Create the view model
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        //Set the observer
        // mLoginViewModel.getCustomListData().observe(this, nameObserver)
        mLoginViewModel.getCustomListDataItems().observe(this, nameObserver)

//        //Put this into a bundle
//        val fragmentBundle = Bundle()
//        fragmentBundle.putParcelable("item_list", mCustomListData)
//
//        //Create the fragment
//        mMasterListFragment = MasterListFragment()
//
//        //Pass data to the fragment
//        mMasterListFragment!!.arguments = fragmentBundle
//
//        val fTrans = supportFragmentManager.beginTransaction()
//        fTrans.replace(
//            R.id.fl_frag_masterlist_container_phone,
//            mMasterListFragment!!,
//            "frag_masterlist"
//        )
//
////        if (isTablet()) {
////            //Pane 1: Master list
////            fTrans.replace(
////                R.id.fl_frag_masterlist_container_tablet,
////                mMasterListFragment!!,
////                "frag_masterlist"
////            )
////        } else {
////            fTrans.replace(
////                R.id.fl_frag_masterlist_container_phone,
////                mMasterListFragment,
////                "frag_masterlist"
////            )
////        }
//        fTrans.commit()

//        var dbManager : DBManager = DBManager(this);
//        var usersUIDArray=dbManager.getAllUid()
//        var linearLayoutUis=findViewById<LinearLayout>(R.id.users)

//        usersUIDArray.forEach{
//            val resBtn = ImageButton(this)
//            val tv = TextView(this)
//            tv.gravity = Gravity.CENTER or Gravity.BOTTOM
//            tv.text = dbManager.getFirstName(it) +" " +dbManager.getLastName(it)
//            var t=dbManager.getLastName(it)
//            Log.d("LOG", "WHY IS THIS SO DUMB: $t")
//            resBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.humidity));
//            resBtn.setBackgroundColor(Color.TRANSPARENT);
////            linearLayoutUis.addView(resBtn);
//            linearLayoutUis.addView(tv);
//            var idToStore=View.generateViewId()
//            tv.id=idToStore
//            arrayUIIds.add(idToStore)
//
//        }

        // Set click listener for Create a Profile Button
        mCreateAProfileBtn=findViewById(R.id.create_a_profile_btn) as Button;
        mCreateAProfileBtn.setOnClickListener(this);

    }

    // Create an observer that watches the LiveData<CustomListData> object
    val nameObserver: Observer<List<CustomListDataItem>> =
        Observer<List<CustomListDataItem>> { customListDataItems ->
            // Update the recycle view if the custom list data changes
            if (customListDataItems != null) {
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
//        //Get the string data corresponding to the detail view
//        val itemDetailString: String = HikeActivity.mCustomListData.getItemDetail(position)
//
//        //Put this into a bundle
//
//        //Put this into a bundle
//        val detailBundle = Bundle()
//        detailBundle.putString("item_detail", itemDetailString)
//
//        //If we're on a tablet, the fragment occupies the second pane (right). If we're on a phone,
//        //the fragment is on a new Activity
//
//        //If we're on a tablet, the fragment occupies the second pane (right). If we're on a phone,
//        //the fragment is on a new Activity
//        if (isTablet()) {
//            //Create a new detail fragment
//            val itemDetailFragment = ItemDetailFragment()
//
//            //Pass data to the fragment
//            itemDetailFragment.setArguments(detailBundle)
//
//            //Replace the detail fragment container
//            val fTrans = supportFragmentManager.beginTransaction()
//            fTrans.replace(
//                R.id.fl_frag_itemdetail_container_tablet,
//                itemDetailFragment,
//                "frag_itemdetail"
//            )
//            fTrans.commit()
//        } else { //On a phone
//            //Start ItemDetailActivity, pass the string along
//            val sendIntent = Intent(this, ItemDetailActivity::class.java)
//            sendIntent.putExtras(detailBundle)
//            startActivity(sendIntent)
//        }
    }


}