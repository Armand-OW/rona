package com.example.rona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question_one.*

class QuestionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_one)

        title = "Question 1"

        //get intent extra info
        val userName = intent.getStringExtra(Constants.USER_NAME)

        //get questions
        val questionsList = Constants.getQuestions()
        Log.i("QuestionsList: ", "${questionsList.size}")

        //set question number & array
        val questionNumber: Int = 1
        val question: Question = questionsList[questionNumber-1]

        //set UI elements to Question 1
        tv_question.text = "Hi ${userName}! " + question.question
        iv_icon.setImageResource(question.icon)
        rb_answer_one.text = question.optionOne
        rb_answer_two.text = question.optionTwo

        pb_progressbar.progress = questionNumber
        tv_progress.text = questionNumber.toString() + "/" + questionsList.size.toString()


        //set a btn on click lsiter
        var answers: RadioButton
        var wrongAnswers: Int = 0

        btn_next.setOnClickListener{
            var id: Int = rg_options.checkedRadioButtonId
            if(id != -1){
//                Capture answer
                answers = findViewById(id)

//                Toast.makeText(this, "Checked answer: ${answers.text}", Toast.LENGTH_SHORT).show()


//                Check if answer's yes
                if(answers.text == question.optionOne){
                    wrongAnswers++
                }
                //Navigation
                val intent = Intent(this, QuestionTwoActivity::class.java)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.WRONG_ANSWERS, wrongAnswers)
                startActivity(intent)
                finish()

            } else {
//                Give validation
                Toast.makeText(this, "Please select your answer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}