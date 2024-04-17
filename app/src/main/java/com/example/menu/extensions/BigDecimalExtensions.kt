package com.example.menu.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toBrazilianCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}

fun BigDecimal.toAmericanCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("en", "us")).format(this)
}