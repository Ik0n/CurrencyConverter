package ru.ikon.currencyconverter.models

data class Rates(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)