package com.example.menu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(searchText: String, onSearchTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { new ->
            onSearchTextChange(new)
        },
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "ícone de lupa")
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que você procura?")
        }
    )
}