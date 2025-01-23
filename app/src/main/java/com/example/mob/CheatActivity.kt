package com.example.mob

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

const val EXTRA_ANSWER_SHOWN = "com.example.mob.answer_shown"
class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var sdk : TextView
    private val quizViewModel : QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val answer = quizViewModel.currentQuestionAnswer
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        sdk = findViewById(R.id.sdk)
        quizViewModel.setIndex(intent.getIntExtra("index", 0))
        if (intent.getBooleanExtra("cheated", false)){
            quizViewModel.isCheater()
            showAnswerButton.visibility = View.GONE
        }
        if (quizViewModel.isCheater) {
            val answerText = when {
                answer -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
        }
        sdk.setText("API level ${Build.VERSION.SDK_INT}")
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answer -> R.string.true_button
                else -> R.string.false_button
            }
            quizViewModel.isCheater()
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
            showAnswerButton.visibility = View.GONE
        }
    }
    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object {
        fun newIntent(context: Context, index : Int, cheated : Boolean) : Intent {
            return Intent(context, CheatActivity::class.java).apply {
                putExtra("index",index)
                putExtra("cheated",cheated)
            }
        }
    }
}