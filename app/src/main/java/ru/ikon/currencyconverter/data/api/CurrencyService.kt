package ru.ikon.currencyconverter.data.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ikon.currencyconverter.models.Rates
import ru.ikon.currencyconverter.utils.Constants.Companion.BASE_CODE
import ru.ikon.currencyconverter.utils.Constants.Companion.BASE_URL
import ru.ikon.currencyconverter.utils.Constants.Companion.LATEST_ENDPOINT

interface CurrencyService {

    @GET(LATEST_ENDPOINT)
    suspend fun getLatestRates(@Query("access_key") apiKey: String, @Query("base") base: String = BASE_CODE): Response<Rates>

}