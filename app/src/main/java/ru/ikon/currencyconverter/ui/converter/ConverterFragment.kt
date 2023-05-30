package ru.ikon.currencyconverter.ui.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_converter.*
import ru.ikon.currencyconverter.databinding.FragmentConverterBinding
import ru.ikon.currencyconverter.utils.Constants.Companion.BASE_CODE
import ru.ikon.currencyconverter.utils.Constants.Companion.CURRENCY_CODE
import ru.ikon.currencyconverter.utils.Constants.Companion.CURRENCY_RATE

@AndroidEntryPoint
class ConverterFragment: Fragment() {

    private lateinit var binding: FragmentConverterBinding

    private var code: String = ""
    private var rate: Double = 0.0

    companion object {
        @JvmStatic
        fun newInstance(code: String, rate: Double): Fragment {
            return ConverterFragment().apply {
                arguments = Bundle().apply {
                    putString(CURRENCY_CODE, code)
                    putDouble(CURRENCY_RATE, rate)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            code = it.getString(CURRENCY_CODE, BASE_CODE)
            rate = it.getDouble(CURRENCY_RATE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_converter_rate.text = "1 $BASE_CODE = $rate $code"
        et_converter_from.hint = BASE_CODE
        et_converter_to.hint = code

        et_converter_from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    et_converter_to.setText("")
                } else {
                    s?.let {
                        et_converter_to.setText((it.toString().toDouble() * rate).toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


}