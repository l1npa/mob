package com.example.mob

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val input: EditText = findViewById(R.id.editText);
        val but: Button = findViewById(R.id.button);
        but.setOnClickListener() {
            if (input.text.toString().trim().length > 0) {
                val message = when (input.text.toString().trim().toInt()) {
                    1 -> "Понедельник"
                    2 -> "Вторник"
                    4 -> "Четверг"
                    5 -> "Пятница"
                    6 -> "Суббота"
                    7 -> "Воскресенье"
                    else -> "Введите номер дня недели"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show()
            }
        }
    }
}