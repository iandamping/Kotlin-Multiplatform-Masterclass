package com.junemon.multiplatform_masterclass

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

class AndroidDevicePlatform : DevicePlatform {

    override val osName: String
        get() = "Android"
    override val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    override val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val deviceDensity: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    override fun logSystemInfo() {
        Log.d("Master class", "($osName $osVersion $deviceModel $deviceDensity")
    }
}

actual fun getDevicePlatform(): DevicePlatform {
    return AndroidDevicePlatform()
}