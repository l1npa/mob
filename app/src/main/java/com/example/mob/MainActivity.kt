package com.example.mob

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.math.pow

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
        val input : EditText = findViewById(R.id.editText);
        val but : Button = findViewById(R.id.button);
        val text : TextView = findViewById(R.id.textView);
        but.setOnClickListener {
            if (input.text.isNotEmpty()) {
                var sum: Double = 0.0;
                val edge: Double = input.text.toString().toDouble();
                var it: Double = 1.0;
                while (it.pow(-2) >= edge) {
                    sum += it.pow(-2);
                    it += 1;
                }
                text.text = "Sum = $sum \n a = ${(it - 1).pow(-2)} \n cicles = ${it - 1}";
            } else {
                Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show()
            }
        }
    }
}