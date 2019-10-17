package com.oakter.smsreactor

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Common {


    companion object {
        private val MY_PREFS_NAME: String = "SMSReactor"
        fun getSharedPreferenceEditor(context: Context) : SharedPreferences.Editor {
            return context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()
        }

        fun getSharedPreference(context: Context) : SharedPreferences {
            return context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        }
    }
}