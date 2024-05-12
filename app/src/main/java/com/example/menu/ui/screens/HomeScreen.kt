package com.example.menu.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.menu.model.Product
import com.example.menu.sampledata.sampleProducts
import com.example.menu.sampledata.sampleSections
import com.example.menu.sampledata.sampleShopsSections
import com.example.menu.ui.components.CardProductItem
import com.example.menu.ui.components.PartnersSection
import com.example.menu.ui.components.ProductsSection
import com.example.menu.ui.components.SearchTextField
import com.example.menu.ui.theme.MenuTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = ""
) {
    Column {
        var text by remember {mutableStateOf(searchText)}
        SearchTextField(searchText = text) {
            text = it
        }
        val filteredProducts = remember(text) {
            if (text.isNotBlank()) {
                sampleProducts.filter {p ->
                    p.name.contains(text, true) ||
                            p.description?.contains(text, true) ?: false
                }
            } else emptyList()
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (text.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }

                for (shopSections in sampleShopsSections){
                    val title = shopSections.key
                    val shop = shopSections.value
                    item {
                        PartnersSection(title = title, shop = shop)
                    }
                }
            } else {
                items(filteredProducts) {p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MenuTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    MenuTheme {
        Surface {
            HomeScreen(sampleSections, "Promo")
        }
    }
}