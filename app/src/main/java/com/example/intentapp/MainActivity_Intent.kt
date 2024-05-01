package com.example.intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity_Intent : Activity() {

    private lateinit var spinnerAgeGroup: Spinner
    private lateinit var textResult: TextView

    companion object {
        const val REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        spinnerAgeGroup = findViewById(R.id.spinnerAgeGroup)
        textResult = findViewById(R.id.text_result)

        val ageGroups = arrayOf("Dewasa", "Anak-anak")
//        val ageGroupSelected = spinnerAgeGroup.selectedItem.toString()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageGroups)
        spinnerAgeGroup.adapter = adapter

        val openSecondActivityButton = findViewById<Button>(R.id.button_open_second_activity)
        openSecondActivityButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val bmiResult = data?.getStringExtra("bmi_result")

            // Ambil nilai umur dari Spinner
            val ageGroup = spinnerAgeGroup.selectedItem.toString()

            // Hitung status berdasarkan umur dan BMI
            val bmiResultString = bmiResult?.toFloatOrNull() ?: 0.0f
            // Konversi string ke float, atau 0.0 jika null atau tidak bisa diubah menjadi float
            val status = if (ageGroup == "Dewasa") {
                when {
                    bmiResultString < 18.5 -> "Status: Berat Badan Kurang"
                    bmiResultString <= 22.9 -> "Status: Berat Badan Normal"
                    bmiResultString <= 29.9 -> "Status: Berat Badan Berlebih (kecenderungan obesitas)"
                    else -> "Status: Obesitas"
                }
            } else if (ageGroup == "Anak-anak") {
                when {
                    bmiResultString < 9.5 -> "Status: Berat Badan Kurang"
                    bmiResultString <= 11.9 -> "Status: Berat Badan Normal"
                    bmiResultString <= 15.9 -> "Status: Berat Badan Berlebih (kecenderungan obesitas)"
                    else -> "Status: Obesitas"
                }
            } else {
                "Status: Umur tidak valid"
            }

            textResult.visibility = TextView.VISIBLE
            textResult.text = "Result: $bmiResult, $status, Age Group: $ageGroup"
        }
    }
}
