package com.example.wallgodds

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyTestScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello Vijay 👋, this is your Jetpack Compose screen!", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun MyTestScreenPreview() {
    MyTestScreen()
}
