package ru.ikon.currencyconverter.ui.currencyrate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ikon.currencyconverter.data.repository.CurrencyRateRepository
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CurrencyRateViewModel @Inject constructor(private val repository: CurrencyRateRepository) : ViewModel() {

    private val _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>>
        get() = _rates

    init {
        getLatestRates()
    }

    fun getLatestRates() {
        viewModelScope.launch {
            try {
                val response = repository.getLatestRates()
                if (response.isSuccessful) {
                    _rates.postValue(response.body()?.rates ?: emptyMap())
                    response.body()?.rates?.let {
                        repository.currencies = it
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
        _rates.postValue(repository.getFilterRates(text))
    }
}