package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MyTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestScreen()  // <- this is calling the screen you made
        }
    }
}
