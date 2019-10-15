package com.oakter.smsreactor

import android.os.Parcel
import android.os.Parcelable

class SMSModelClass() : Parcelable {
    lateinit var _id: String
    lateinit var _address: String
    lateinit var _msg: String
    lateinit var _readState: String //"0" for have not read sms and "1" for have read sms
    lateinit var _time: String
    lateinit var _folderName: String

    constructor(parcel: Parcel) : this() {
        _id = parcel.readString()!!
        _address = parcel.readString()!!
        _msg = parcel.readString()!!
        _readState = parcel.readString()!!
        _time = parcel.readString()!!
        _folderName = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(_address)
        parcel.writeString(_msg)
        parcel.writeString(_readState)
        parcel.writeString(_time)
        parcel.writeString(_folderName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SMSModelClass> {
        override fun createFromParcel(parcel: Parcel): SMSModelClass {
            return SMSModelClass(parcel)
        }

        override fun newArray(size: Int): Array<SMSModelClass?> {
            return arrayOfNulls(size)
        }
    }
}