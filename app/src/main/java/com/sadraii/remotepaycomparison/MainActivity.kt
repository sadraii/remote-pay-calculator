package com.sadraii.remotepaycomparison

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.sadraii.remotepaycomparison.ui.theme.RemotePayComparisonTheme
import com.sadraii.remotepaycomparison.ui.theme.WindowInsetContent

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemotePayComparisonTheme {
                WindowInsetContent() {
                    MainScreen(viewModel = mainViewModel)
                }
            }
        }
    }
}

