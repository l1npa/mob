package com.example.mob

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.math.pow

private const val TAG = "MainActivity";



class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button;
    private lateinit var falseButton : Button;
    private lateinit var nextButton: Button;
    private lateinit var questionTextView: TextView;
    private val questionBank = listOf(
        Question(R.string.question_australia, true,false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false))
    private var currentIndex = 0;
    private var correctAnswers = 0;
    private var incorrectAnswers = 0;
    private fun updateQuestion() {
        if (questionBank[currentIndex].answered){
            trueButton.visibility = View.GONE
            falseButton.visibility = View.GONE
        } else {
            trueButton.visibility = View.VISIBLE
            falseButton.visibility = View.VISIBLE
        }
        val questionTextResId = questionBank[currentIndex].textResId;
        questionTextView.setText(questionTextResId);
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show()
        countAnswers(userAnswer)
    }
    private fun countAnswers(userAnswer: Boolean){
        if (userAnswer == questionBank[currentIndex].answer) correctAnswers += 1
        else incorrectAnswers += 1
        if (correctAnswers + incorrectAnswers == questionBank.size) {
            Toast.makeText(this, "Correct answers: $correctAnswers", Toast.LENGTH_LONG).show();
            nextButton.visibility = View.GONE;
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG,"onCreate(Bundle?) called");
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt("index");
            correctAnswers = savedInstanceState.getInt("answers");
        };
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener { view : View ->
            checkAnswer(true);
            questionBank[currentIndex].answered = true
            updateQuestion();
        }
        falseButton.setOnClickListener{ view : View ->
            checkAnswer(false);
            questionBank[currentIndex].answered = true
            updateQuestion();
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion();
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",currentIndex);
        outState.putInt("answers",correctAnswers);
        Log.d(TAG,"onSaveInstanceState called");
    }
}