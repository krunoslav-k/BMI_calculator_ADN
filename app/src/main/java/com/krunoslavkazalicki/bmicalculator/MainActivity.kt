package com.krunoslavkazalicki.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightTextView = findViewById<EditText>(R.id.etWeight)
        val heightTextView = findViewById<EditText>(R.id.etHeight)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)

        calculateButton.setOnClickListener{
            val weight = weightTextView.text.toString()
            val height = heightTextView.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }

    }

    private fun validateInput(weight:String?, height:String?): Boolean {

         return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float){

        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val infoTextView = findViewById<TextView>(R.id.tvInfo)
        var resultText = ""
        var color = 0

        resultIndex.text = bmi.toString()
        infoTextView.text = "Normal BMI index ranges between 18.5 and 24.9"

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }

            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText

    }
}
