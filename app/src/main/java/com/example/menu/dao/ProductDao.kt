package com.example.menu.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.menu.model.Product
import com.example.menu.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()
    fun save(product: Product) {
        products.add(product)
    }
}