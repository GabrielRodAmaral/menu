package com.example.menu.sampledata

import com.example.menu.R
import com.example.menu.model.Product
import java.math.BigDecimal

val samplePromotions = listOf(
    Product(
        "Hambúrguer",
        BigDecimal(35.90),
        R.drawable.burger
    ),
    Product(
        "Batata frita",
        BigDecimal(15.50),
        R.drawable.fries
    ),
    Product(
        "Pizza",
        BigDecimal(40.0),
        R.drawable.pizza
    )
)


val sampleCandies = listOf(
    Product(
        "Bolo de chocolate",
        BigDecimal(43.0),
        R.drawable.cake
    )
)

val sampleDrinks = listOf(
    Product(
        "Coca-Cola",
        BigDecimal(12.5),
        R.drawable.coke
    ),
    Product(
        "Suco de laranja",
        BigDecimal(9.2),
        R.drawable.juicy
    )
)