package com.junemon.multiplatform_masterclass

class DeviceInformation {
    private val devicePlatform: DevicePlatform = getDevicePlatform()

    val osName: String = devicePlatform.osName
    val osVersion: String = devicePlatform.osVersion
    val deviceModel: String = devicePlatform.deviceModel
    val deviceDensity: Int = devicePlatform.deviceDensity

    private fun logSystemInfo() = devicePlatform.logSystemInfo()

    init {
        logSystemInfo()
    }

}