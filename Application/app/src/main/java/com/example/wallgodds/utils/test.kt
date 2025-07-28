package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wallgodds.screens.PopUpPage
import com.example.wallgodds.ui.theme.WallGoddsTheme

class test : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallGoddsTheme {
                PopUpPage(
                    onProfileClick = {},
                    onClose = {}
                )
            }
        }
    }
}
