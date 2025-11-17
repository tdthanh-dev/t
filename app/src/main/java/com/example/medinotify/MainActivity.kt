package com.example.medinotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.medinotify.ui.theme.MedinotifyTheme
import com.example.medinotify.ui.navigation.MedinotifyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedinotifyTheme {
                MedinotifyApp()
            }
        }
    }
}