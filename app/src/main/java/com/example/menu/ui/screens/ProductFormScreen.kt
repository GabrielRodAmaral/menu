package com.example.menu.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.menu.model.Product
import com.example.menu.ui.states.ProductFormUiState
import com.example.menu.ui.theme.MenuTheme
import com.example.menu.ui.viewmodels.ProductFormViewModel
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(
    viewModel: ProductFormViewModel,
    saveProduct: () -> Unit = {}
) {
//    ProductFormScreen(
//        state = ProductFormUiState(
//            url = url,
//            name = name,
//            price = price,
//            description = description,
//            isInvalidImage = isInvalidImage,
//            isPriceError = isPriceError,
//            errorInForm = errorInForm,
//            imageSuccess = {
//                isInvalidImage = false
//            },
//            imageError = {
//                isInvalidImage = true
//            },
//            onUrlChange = {
//                url = it
//            },
//            onNameChange = {
//                name = it
//            },
//            onPriceChange = {
//                isPriceError = try {
//                    BigDecimal(it)
//                    false
//                } catch (e: IllegalArgumentException) {
//                    it.isNotEmpty()
//                }
//                price = it
//            },
//            onDescriptionChange = {
//                description = it
//            },
//            buttonSaveClick = {
//                if (isPriceError ||
//                    (description.isEmpty() || description.isBlank()) ||
//                    (name.isEmpty() || name.isEmpty()) ||
//                    isInvalidImage || url.isBlank() || url.isEmpty()
//                ) {
//                    errorInForm = true
//                } else {
//                    errorInForm = false
//                    val product = Product(
//                        name = name,
//                        image = url,
//                        price = BigDecimal(price),
//                        description = description
//                    )
//                    saveProduct(product)
//                }
//            }
//        ),
//    )
    val state by viewModel.uiState.collectAsState()
    ProductFormScreen(
        state = state,
        saveProduct = {
            viewModel.save()
            saveProduct()
        }
    )
}

@Composable
fun ProductFormScreen(
    state: ProductFormUiState,
    saveProduct: () -> Unit = {}
) {
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
        if (state.url.isNotBlank()) {
            AsyncImage(
                model = state.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            value = state.url,
            onValueChange = state.onUrlChange,
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
        TextField(
            value = state.name,
            onValueChange = state.onNameChange,
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
        TextField(
            value = state.price,
            onValueChange = state.onPriceChange,
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
        TextField(
            value = state.description,
            onValueChange = state.onDescriptionChange,
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
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = saveProduct
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
            ProductFormScreen(state = ProductFormUiState())
        }
    }
}