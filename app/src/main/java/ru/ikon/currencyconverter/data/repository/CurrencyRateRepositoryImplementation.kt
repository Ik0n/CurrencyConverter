package ru.ikon.currencyconverter.data.repository

import retrofit2.Response
import ru.ikon.currencyconverter.data.api.CurrencyService
import ru.ikon.currencyconverter.models.Rates
import ru.ikon.currencyconverter.utils.Constants.Companion.API_KEY
import javax.inject.Inject

class CurrencyRateRepositoryImplementation @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRateRepository {

    var currencies = mapOf<String, Double>()

    override suspend fun getLatestRates() : Response<Rates> {
        return currencyService.getLatestRates(API_KEY)
    }

    override fun getFilterRates(text: String) : Map<String, Double> {
        return currencies.filter { it.component1().contains(text, ignoreCase = true) }
    }

}