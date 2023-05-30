package ru.ikon.currencyconverter.data.repository

import retrofit2.Response
import ru.ikon.currencyconverter.models.Rates

interface CurrencyRateRepository {
    suspend fun getLatestRates(): Response<Rates>
    fun getFilterRates(text: String) : Map<String, Double>
}