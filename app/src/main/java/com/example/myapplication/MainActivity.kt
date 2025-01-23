package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radioGroup)
        editText = findViewById(R.id.editTextNumber)
        button.setOnClickListener{
            val selectedOptionId = radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val sum = when (selectedOptionId){
                    R.id.radioButton -> 10
                    R.id.radioButton2 -> 30
                    R.id.radioButton3 -> 100
                    else -> 0
                }
                val count = if (editText.text.isNotEmpty()) editText.text.toString().toInt() else 0
                findViewById<TextView>(R.id.textView).setText("Сумма равна ${sum*count} руб")
            } else {
                Toast.makeText(this, "Пожалуйста, выберите опцию", Toast.LENGTH_SHORT).show()
            }
        }
    }
}