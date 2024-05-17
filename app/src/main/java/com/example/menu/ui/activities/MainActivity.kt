package com.example.menu.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.menu.ui.screens.HomeScreen
import com.example.menu.ui.theme.MenuTheme
import com.example.menu.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onFABClick = {
                    startActivity(Intent(this, ProductFormaActivity::class.java))
                }
            ) {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun App(onFABClick: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    MenuTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(
                    onClick = { onFABClick() }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) {
                Box(
                    Modifier
                        .padding(it)
                ) {
                    content()
                }
            }
        }
    }
}


