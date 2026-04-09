package com.junemon.multiplatform_masterclass

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform