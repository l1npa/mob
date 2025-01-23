package com.example.mob

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class QuizViewModel (application: Application) : AndroidViewModel(application){
    private val questionBank = listOf(
        Question(R.string.question_australia, true,false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var currentIndex = 0
    fun setIndex(index : Int){
        currentIndex = index
    }
    fun getIndex() : Int {return currentIndex;}
    val isCheater : Boolean get() = questionBank[currentIndex].cheated
    fun isCheater(){
        questionBank[currentIndex].cheated = true
    }
    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer
    val currentQuestionText: Int get() = questionBank[currentIndex].textResId
    fun moveToNext() { currentIndex = (currentIndex + 1) % questionBank.size }
}