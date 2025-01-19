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
        but.setOnClickListener {
            val inp = input.text.toString().trim();
            if (inp == ""){
                Toast.makeText(this,"Введите текст",Toast.LENGTH_SHORT).show();
            } else {
                var counter = 0;
                for (it in inp.toLowerCase()){
                    if (it >= 'a' && it <= 'z') counter++;
                }
                Toast.makeText(this, "Латинских букв: $counter", Toast.LENGTH_SHORT).show()
            }
        }
    }
}