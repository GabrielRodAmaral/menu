package com.example.menu.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.menu.dao.ProductDao
import com.example.menu.model.Product
import com.example.menu.sampledata.sampleCandies
import com.example.menu.sampledata.sampleDrinks
import com.example.menu.sampledata.sampleProducts
import com.example.menu.sampledata.sampleSections
import com.example.menu.ui.screens.HomeScreen
import com.example.menu.ui.screens.HomeScreenUiState
import com.example.menu.ui.theme.MenuTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onFABClick = {
                    startActivity(Intent(this, ProductFormaActivity::class.java))
                }
            ) {
                val products = dao.products()
                HomeScreen(products)
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


