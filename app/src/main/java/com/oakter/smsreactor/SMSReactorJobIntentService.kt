package com.oakter.smsreactor

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.ResultReceiver
import android.util.Log
import androidx.core.app.JobIntentService


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
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0)
    }
}