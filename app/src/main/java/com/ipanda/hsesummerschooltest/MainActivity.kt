package com.ipanda.hsesummerschooltest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var currencies: List<String>
    private lateinit var values: List<Double>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        currencies = listOf(
            getString(R.string.ruble),
            getString(R.string.dollar),
            getString(R.string.euro),
            getString(R.string.pound),
            getString(R.string.hryvnia)
        )

        values = listOf(1.0, 70.62, 78.53, 87.09, 2.64)

        adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, currencies)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.onItemSelectedListener = this@MainActivity
        spinner2.onItemSelectedListener = this@MainActivity

        textChanged()
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (editText.text.isNotEmpty()) {
            textView.text = (editText.text.toString()
                .toDouble() * values[spinner1.selectedItemPosition] / values[spinner2.selectedItemPosition]).toString()
        }
    }

    private fun textChanged() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.text.isEmpty()) {
                    textView.text = "Сумма не введена"
                    textView.setTextColor(resources.getColor(R.color.grey))
                } else {
                    textView.setTextColor(resources.getColor(R.color.black))
                    textView.text = (editText.text.toString()
                        .toDouble() * values[spinner1.selectedItemPosition] / values[spinner2.selectedItemPosition]).toString()
                }
            }

        })
    }
}
