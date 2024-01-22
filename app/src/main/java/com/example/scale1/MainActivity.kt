package com.example.scale1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scale1.ui.scale.WeekScreen
import com.example.scale1.ui.scale.WeekViewModel
import com.example.scale1.ui.theme.ScaleWeekTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val weekViewModel: WeekViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaleWeekTheme {
                WeekScreen(weekViewModel)
            }
        }
    }
}