package com.oakter.smsreactor

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import java.lang.Exception
import android.provider.Telephony.Sms




class SMSListRetriver {
    companion object {
        fun returnSMSList(context: Context): ArrayList<SMSModelClass> {
            val lstSms = ArrayList<SMSModelClass>()
            var contentResolver: ContentResolver
            try {
                var objSms = SMSModelClass()
                val message = Uri.parse("content://sms/")
                contentResolver = context.contentResolver
                val c = contentResolver.query(message, null, null, null, null)
                val totalSMS = c!!.getCount()

                if (c.moveToFirst()) {
                    for (i in 0 until totalSMS) {

                        objSms = SMSModelClass()
                        objSms._id = (c.getString(c.getColumnIndexOrThrow("_id")))
                        objSms._address = (
                            c.getString(c.getColumnIndexOrThrow("address"))
                        )
                        objSms._msg = (c.getString(c.getColumnIndexOrThrow("body")))
                        objSms._readState = (c.getString(c.getColumnIndex("read")))
                        objSms._time = (c.getString(c.getColumnIndexOrThrow("date")))
                        if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                            objSms._folderName = ("inbox")
                        } else {
                            objSms._folderName = ("sent")
                        }

                        lstSms.add(objSms)
                        c.moveToNext()
                    }
                }
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
            return lstSms
        }
    }
}