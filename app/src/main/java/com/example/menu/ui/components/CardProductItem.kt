package com.example.menu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.menu.R
import com.example.menu.extensions.toBrazilianCurrency
import com.example.menu.model.Product
import com.example.menu.sampledata.sampleProducts
import com.example.menu.ui.theme.MenuTheme

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    expanded: Boolean = false
) {
    var expandedState by remember {
        mutableStateOf(expanded)
    }
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expandedState = !expandedState
            },
        elevation = CardDefaults.cardElevation(elevation),
        content =  {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF8E99F3))
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            val maxLines = if (expandedState) Int.MAX_VALUE else 2
            val textOverflow = if (expandedState) TextOverflow.Visible else TextOverflow.Ellipsis
            product.description?.let {
                Text(
                    text = product.description,
                    Modifier
                        .padding(16.dp),
                    maxLines = maxLines,
                    overflow = textOverflow
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardProductItemPreview() {
    MenuTheme {
        Surface {
            CardProductItem(
                product = sampleProducts.random(),
            )
        }
    }
}