package com.example.lifestyle

import androidx.room.ColumnInfo

class CustomListDataItem {
    @ColumnInfo(name = "UUID")
    var uuid: String? = null

    @ColumnInfo(name = "FIRST_NAME")
    var firstName: String? = null

    @ColumnInfo(name = "LAST_NAME")
    var lastName: String? = null

//    constructor(uuid: String, first_name : String, last_name: String) {
//        this.uuid = uuid
//        this.firstName = first_name
//        this.lastName = last_name
//    }
}