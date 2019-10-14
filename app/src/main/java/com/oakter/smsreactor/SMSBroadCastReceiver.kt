package com.oakter.smsreactor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.regex.Pattern
import java.util.stream.Collectors

class SMSBroadCastReceiver: BroadcastReceiver() {

    private val TAG = "SMSBroadCastReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "SMS Received")
        val listOfSMSFromInterestedNumber : ArrayList<SMSModelClass> = SMSListRetriver.returnSMSList(context!!)

//            .stream().filter { smsModelClass: SMSModelClass -> smsModelClass._address.equals("+917992267967", true) }.collect(Collectors.toList()) as ArrayList<SMSModelClass>

        Log.e("Count",listOfSMSFromInterestedNumber.size.toString())
    }
}