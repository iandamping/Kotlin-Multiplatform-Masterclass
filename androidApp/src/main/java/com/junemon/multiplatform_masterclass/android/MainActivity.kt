package com.junemon.multiplatform_masterclass.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.junemon.multiplatform_masterclass.android.navigation.MultiplatformMasterclassNavHost
import com.junemon.multiplatform_masterclass.articles.ArticleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleViewModel: ArticleViewModel by viewModels()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold { paddingValue ->
                        MultiplatformMasterclassNavHost(
                            modifier = Modifier.padding(paddingValue),
                            navController = navController,
                            articleViewModel = articleViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DevicePlatformInformation(
    modifier: Modifier = Modifier,
    osName: String, osVersion: String, deviceModel: String, screenDensity: Int
) {
    Column(modifier) {
        Text("Os Name : $osName")
        Text("Os Version : $osVersion")
        Text("Device Model : $deviceModel")
        Text("Screen Density : $screenDensity")
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
