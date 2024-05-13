package com.example.menu.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.menu.R
import com.example.menu.dao.ProductDao
import com.example.menu.model.Product
import com.example.menu.ui.theme.MenuTheme
import java.math.BigDecimal

class ProductFormaActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuTheme {
                Surface {
                    ProductFormScreen(saveProduct = {
                        dao.save(it)
                        finish()
                    })
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(saveProduct: (Product) -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            text = "Adicione um produto",
            Modifier
                .fillMaxWidth(),
            fontSize = 28.sp
        )
        var url by remember { mutableStateOf("") }
        var isInvalidImage by remember { mutableStateOf(false) }
        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                onSuccess = {
                    isInvalidImage = false
                },
                onError = {
                    isInvalidImage = true
                }
            )
        }
        TextField(
            value = url,
            onValueChange = {
                url = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text("Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )
        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text("Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )
        var price by remember { mutableStateOf("") }
        var isPriceError by remember { mutableStateOf(false) }
        TextField(
            value = price,
            onValueChange = {
                isPriceError = try {
                    BigDecimal(it)
                    false
                } catch (e: IllegalArgumentException) {
                    it.isNotEmpty()
                }
                price = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text("Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        if (isPriceError) {
            Text(
                text = "Preço deve ser um número decimal",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        var description by remember { mutableStateOf("") }
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            label = {
                Text("Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        var errorInForm by remember {
            mutableStateOf(false)
        }
        if (errorInForm) {
            Text(
                text = "Todos os campos devem ser preenchidos corretamente",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (isPriceError ||
                    (description.isEmpty() || description.isBlank()) ||
                    (name.isEmpty() || name.isEmpty()) ||
                    isInvalidImage || url.isBlank() || url.isEmpty()) {
                    errorInForm = true
                } else {
                    errorInForm = false
                    val product = Product(
                        name = name,
                        image = url,
                        price = BigDecimal(price),
                        description = description
                    )
                    saveProduct(product)
                }
            }
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier)
    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    MenuTheme {
        Surface {
            ProductFormScreen()
        }
    }
}