package com.oakter.smsreactor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SMSBroadCastReceiver: BroadcastReceiver() {
    private val TAG = "SMSBroadCastReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "SMS Received")

    }
}