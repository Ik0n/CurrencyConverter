package ru.ikon.currencyconverter.ui.currencyrate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_currency_rate.*
import ru.ikon.currencyconverter.databinding.FragmentCurrencyRateBinding
import ru.ikon.currencyconverter.ui.adapters.CurrencyRateAdapter

class CurrencyRateFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyRateBinding
    private val viewModel: CurrencyRateViewModel by viewModels()
    private lateinit var adapter: CurrencyRateAdapter

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return CurrencyRateFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyRateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CurrencyRateAdapter(emptyMap())
        recycler_currency.adapter = adapter
        recycler_currency.layoutManager = LinearLayoutManager(requireContext())

        viewModel.rates.observe(viewLifecycleOwner) { rates ->
            adapter.rates = rates
            recycler_currency.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewModel.getLatestRates()

        et_currency.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    viewModel.getFilterRates(it.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        et_currency.setText("")
    }

}