package com.junemon.multiplatform_masterclass.android.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
sealed interface NavigationScreen {

    @Keep
    @Serializable
    object ArticleScreen : NavigationScreen

    @Keep
    @Serializable
    object DeviceInformationScreen : NavigationScreen

    @Keep
    @Serializable
    object NewsSourceScreen : NavigationScreen

}