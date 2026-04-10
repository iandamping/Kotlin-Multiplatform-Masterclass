package com.junemon.multiplatform_masterclass.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.junemon.multiplatform_masterclass.android.screen.ArticleScreen
import com.junemon.multiplatform_masterclass.android.screen.DeviceInformationScreen
import com.junemon.multiplatform_masterclass.articles.ArticleViewModel

@Composable
fun MultiplatformMasterclassNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.ArticleScreen,
        modifier = modifier
    ) {
        composable<NavigationScreen.ArticleScreen> {
            ArticleScreen(viewModel = articleViewModel) {
                navController.navigate(NavigationScreen.DeviceInformationScreen)
            }
        }

        composable<NavigationScreen.DeviceInformationScreen> {
            DeviceInformationScreen {
                navController.navigate(NavigationScreen.ArticleScreen)
            }
        }
    }
}