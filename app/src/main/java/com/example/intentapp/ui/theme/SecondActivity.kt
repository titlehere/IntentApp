package com.example.intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val returnButton = findViewById<Button>(R.id.button_return_result)
        returnButton.setOnClickListener {
            calculateBMIAndReturnResult()
        }
    }

    private fun calculateBMIAndReturnResult() {
        val weightEditText = findViewById<EditText>(R.id.numberWeight)
        val heightEditText = findViewById<EditText>(R.id.numberHeight)

        val weightString = weightEditText.text.toString()
        val heightString = heightEditText.text.toString()

        if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
            val weight = weightString.toFloat()
            val height = heightString.toFloat() / 100 // convert cm to m
            val bmi = weight / (height * height)
            val bmiResult = "BMI: $bmi"

            val returnIntent = Intent()
            returnIntent.putExtra("bmi_result", bmiResult)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
