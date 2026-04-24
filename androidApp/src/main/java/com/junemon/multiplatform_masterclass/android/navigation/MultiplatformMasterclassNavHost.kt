package com.junemon.multiplatform_masterclass.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.junemon.multiplatform_masterclass.android.screen.article.ArticleScreen
import com.junemon.multiplatform_masterclass.android.screen.deviceInformation.DeviceInformationScreen
import com.junemon.multiplatform_masterclass.android.screen.newsSource.NewsSourceScreen

@Composable
fun MultiplatformMasterclassNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.ArticleScreen,
        modifier = modifier
    ) {
        composable<NavigationScreen.ArticleScreen> {
            ArticleScreen(
                deviceInfoClick = {
                    navController.navigate(NavigationScreen.DeviceInformationScreen)
                }, newsSourceClick = {
                    navController.navigate(NavigationScreen.NewsSourceScreen)
                })
        }

        composable<NavigationScreen.NewsSourceScreen> {
            NewsSourceScreen(backClick = {
                navController.popBackStack()
            })
        }

        composable<NavigationScreen.DeviceInformationScreen> {
            DeviceInformationScreen {
                navController.navigate(NavigationScreen.ArticleScreen)
            }
        }
    }
}