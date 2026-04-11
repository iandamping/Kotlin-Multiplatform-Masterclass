package com.junemon.multiplatform_masterclass.android.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.junemon.multiplatform_masterclass.devicesInformation.DeviceInformation

@Composable
fun DeviceInformationScreen(
    modifier: Modifier = Modifier,
    backClick: () -> Unit,
) {
    Column(modifier) {
        DeviceInformationAppBar(backClick = backClick)

        ContentView()
    }
}

@Composable
private fun ContentView() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(makeItems()) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = DeviceInformation()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.deviceDensity.toString())
    )
}

@Composable
private fun RowView(
    title: String,
    subtitle: String,
) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceInformationAppBar(
    modifier: Modifier = Modifier,
    backClick: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Text("Device Information")
    }, navigationIcon = {
        IconButton(onClick = backClick) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Device Information Button",
            )
        }
    })
}