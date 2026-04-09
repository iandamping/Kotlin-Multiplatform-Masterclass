package com.junemon.multiplatform_masterclass

/**expect class or interface are not allowed to have any kind of function implementation
 * and initialize variable value*/
interface DevicePlatform{
    val osName: String
    val osVersion: String
    val deviceModel: String
    val deviceDensity: Int

    fun logSystemInfo()
}

expect fun getDevicePlatform(): DevicePlatform
