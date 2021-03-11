package com.example.rona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_two.*
import kotlinx.android.synthetic.main.activity_question_two.btn_next
import kotlinx.android.synthetic.main.activity_question_two.iv_icon
import kotlinx.android.synthetic.main.activity_question_two.pb_progressbar
import kotlinx.android.synthetic.main.activity_question_two.rb_answer_one
import kotlinx.android.synthetic.main.activity_question_two.rb_answer_two
import kotlinx.android.synthetic.main.activity_question_two.rg_options
import kotlinx.android.synthetic.main.activity_question_two.tv_progress
import kotlinx.android.synthetic.main.activity_question_two.tv_question

class QuestionTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_two)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        var wrongAnswers = intent.getIntExtra(Constants.WRONG_ANSWERS, 0)

        title = "Question 2"



        //get questions
        val questionsList = Constants.getQuestions()
        Log.i("QuestionsList: ", "${questionsList.size}")

        //set question number & array
        val questionNumber: Int = 2
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
                val intent = Intent(this, QuestionThreeActivity::class.java)
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