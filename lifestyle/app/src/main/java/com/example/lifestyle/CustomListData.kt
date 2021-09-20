package com.example.lifestyle

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class CustomListData : Parcelable {
    constructor(names: ArrayList<String>, uuids: ArrayList<String>) {
        //Populate the item list with data
        //and populate the details list with details at the same time
        mItemList = names
        mItemDetails = uuids

    }

    //Implement the creator method
    @JvmField
    val CREATOR: Parcelable.Creator<CustomListData?> = object : Parcelable.Creator<CustomListData?> {
        //Call the private constructor
        override fun createFromParcel(`in`: Parcel): CustomListData? {
            return CustomListData(`in`) as CustomListData
        }

        override fun newArray(size: Int): Array<CustomListData?> {
            return arrayOfNulls(size)
        }
    }

    private var mItemList: List<String>? = null
    private var mItemDetails: List<String>? = null

    fun CustomListData(names: ArrayList<String>, uuids: ArrayList<String>) {
        //Populate the item list with data
        //and populate the details list with details at the same time
        mItemList = names
        mItemDetails = uuids
//        var dbManager : DBManager = DBManager(this);
//        // Get a list of all uuids saved in the database
//        var uuids =dbManager.getAllUuids()
//
//        uuids.forEach{
//            val firstName : String = dbManager.getFirstName(it)
//            val lastName : String = dbManager.getLastName(it)
//            val fullName = firstName + " " + lastName
//
//            (mItemList as ArrayList<String>).add(fullName)
//            (mItemDetails as ArrayList<String>).add(it)
//
//        }

    }

    //Say how to read in from parcel
    private fun CustomListData(`in`: Parcel) {
        `in`.readStringList(mItemList!!)
        `in`.readStringList(mItemDetails!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    //Say how and what to write to parcel
    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeStringList(mItemList)
        out.writeStringList(mItemDetails)
    }

    //Implement a getter and setter for getting whole list
    fun getItemList(): List<String?>? {
        return mItemList
    }

    fun setItemList(itemList: List<String>) {
        mItemList = itemList
    }

    //Implement getter for item details at a position
    fun getItemDetail(position: Int): String? {
        return mItemDetails!![position]
    }
}