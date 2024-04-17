package com.example.menu.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menu.R
import com.example.menu.model.Product
import com.example.menu.sampledata.sampleCandies
import com.example.menu.sampledata.sampleDrinks
import com.example.menu.sampledata.samplePromotions
import java.math.BigDecimal

@Composable
fun ProductsSection(title: String, products: List<Product>) {
    Column {
        Text(
            title,
            Modifier
                .padding(bottom = 8.dp, start = 16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState()
        ) {
            items(products) {
                ProductItem(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsSectionPreview() {
    ProductsSection("Doces", samplePromotions)
    ProductsSection("Doces", sampleCandies)
    ProductsSection("Bebidas", sampleDrinks)
}
