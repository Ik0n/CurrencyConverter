package ru.ikon.currencyconverter.ui.currencyrate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ikon.currencyconverter.data.api.CurrencyService
import ru.ikon.currencyconverter.utils.Constants.Companion.API_KEY
import java.lang.Exception

class CurrencyRateViewModel: ViewModel() {

    private val currencyService = CurrencyService.create()

    private val _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>>
        get() = _rates

    var currencies = mapOf<String, Double>()

    fun getLatestRates() {
        viewModelScope.launch {
            try {
                val response = currencyService.getLatestRates(apiKey = API_KEY)
                if (response.isSuccessful) {
                    _rates.postValue(response.body()?.rates ?: emptyMap())
                    response.body()?.rates?.let {
                        currencies = it
                    }
                } else {
                    Log.e("CurrencyRateViewModel", "Error getting latest rates : ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("CurrencyRateViewModel", "Exception getting latest rates : ${e.message}")
            }
        }
    }

    fun getFilterRates(text: String) {
        _rates.postValue(currencies.filter { it.component1().contains(text, ignoreCase = true) })
    }
}