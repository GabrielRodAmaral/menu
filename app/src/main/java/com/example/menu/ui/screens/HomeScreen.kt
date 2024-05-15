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
import com.example.menu.sampledata.sampleCandies
import com.example.menu.sampledata.sampleDrinks
import com.example.menu.sampledata.sampleProducts
import com.example.menu.sampledata.sampleSections
import com.example.menu.sampledata.sampleShopsSections
import com.example.menu.ui.components.CardProductItem
import com.example.menu.ui.components.PartnersSection
import com.example.menu.ui.components.ProductsSection
import com.example.menu.ui.components.SearchTextField
import com.example.menu.ui.theme.MenuTheme

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    private val products: List<Product> = emptyList(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(products: List<Product>) {
    var sections = emptyMap<String, List<Product>>()
    if (products.isNotEmpty()) {
        sections = mapOf(
            "Adicionados recentemente" to products,
            "Promoções" to sampleDrinks + sampleCandies,
            "Doces" to sampleCandies,
            "Bebidas" to sampleDrinks
        )
    } else {
        sections = mapOf(
            "Promoções" to sampleDrinks + sampleCandies,
            "Doces" to sampleCandies,
            "Bebidas" to sampleDrinks
        )
    }
    var text by remember {
        mutableStateOf("")
    }
    fun containsInNameOrDescrioption() = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true,
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false
    }
    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescrioption()) +
                    products.filter(containsInNameOrDescrioption())
        } else emptyList()
    }
    val state = remember(products, text) {
        HomeScreenUiState(
            sections,
            products = products,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val filteredProducts = state.searchedProducts
        SearchTextField(searchText = text, onSearchTextChange = state.onSearchChange)
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (state.isShowSections()) {
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
            HomeScreen(HomeScreenUiState(sampleSections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    MenuTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sampleSections, searchText = "Bat"))
        }
    }
}