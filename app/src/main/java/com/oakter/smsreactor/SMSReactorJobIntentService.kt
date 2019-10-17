package com.oakter.smsreactor

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.ResultReceiver
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class SMSReactorJobIntentService: JobIntentService() {

    private val TAG : String = "SMSReactorJobIntentService"
    lateinit var latestSMSModel: SMSModelClass
    val SHOW_RESULT: Int = 123
    /**
     * Result receiver object to send results
     */
    private var mResultReceiver: ResultReceiver? = null
    /**
     * Unique job ID for this service.
     */
    val JOB_ID = 1000
    /**
     * Convenience method for enqueuing work in to this service.
     */
    /**
     * Convenience method for enqueuing work in to this service.
     */
    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, SMSReactorJobIntentService::class.java, JOB_ID, work)
    }


    @SuppressLint("LongLogTag")
    override fun onHandleWork(intent: Intent) {
        Log.i(TAG, ""+intent)
        latestSMSModel = intent.getParcelableExtra("latestSMSModel")
        Log.e(latestSMSModel._address,latestSMSModel._msg)
        workOnWhatTheMsgContains(latestSMSModel._msg)
    }

    private fun workOnWhatTheMsgContains(msgContent: String) {
        val msgArrayList: ArrayList<String> = ArrayList<String>(msgContent.split(","))
        commandInArrayList(msgArrayList)
    }

    private fun commandInArrayList(stringCommandArray : ArrayList<String>) {
        for (i in 0..stringCommandArray.size - 1 step 2){
            try {
                when (stringCommandArray[i].toLowerCase().trim()) {
                    "vol" -> {
                        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
                        Log.e(
                            "Ringer Max Volume",
                            audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toString()
                        )
                        Log.e(
                            "Setting volume : ",
                            ((audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * ((stringCommandArray[i+1].trim().toInt() * 10f) / 100f)).toInt()).toString()
                        )
                        audioManager.setStreamVolume(
                            AudioManager.STREAM_MUSIC,
                            (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * ((stringCommandArray[i+1].trim().toInt() * 10f) / 100f)).toInt(),
                            0
                        )
                    }
                    "wifi" -> {
                        val wifiManager: WifiManager =
                            getSystemService(Context.WIFI_SERVICE) as WifiManager
                        if (stringCommandArray[i+1].trim().toInt() == 0 && wifiManager.isWifiEnabled)
                            wifiManager.setWifiEnabled(false)
                        else if (stringCommandArray[i+1].toInt() == 1)
                            wifiManager.setWifiEnabled(true)
                    }
                    "data" -> {
                        /*val telephonyService = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        val setMobileDataEnabledMethod = telephonyService::class.java.getDeclaredMethod(
                            "setDataEnabled",
                            Boolean::class.javaPrimitiveType
                        )
                        if (null != setMobileDataEnabledMethod)
                            setMobileDataEnabledMethod.invoke(telephonyService, false)*/
                    }
                }
            } catch (exception: Exception){
                exception.printStackTrace()
            }
        }
    }
}