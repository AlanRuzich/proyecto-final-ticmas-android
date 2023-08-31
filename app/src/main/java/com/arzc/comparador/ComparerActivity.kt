package com.arzc.comparador

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.arzc.comparador.databinding.ActivityMainBinding

class ComparerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val comparerViewModel: ComparerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        comparerViewModel.comparer.observe(this) {
            binding.resultText.text = if (it.equal) getString(R.string.textEqual) else getString(R.string.textNotEqual)
        }

        binding.compareButton.setOnClickListener {
            comparerViewModel.compareStrings(
                findViewById<EditText>(R.id.editText1).text.toString(),
                findViewById<EditText>(R.id.editText2).text.toString()
            )
        }
    }
}