package com.example.rona

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //start button click
        btn_start.setOnClickListener {

            //check if name is empty
            if(et_name.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuestionOneActivity::class.java)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }

        //get shared preferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        //fab onclick
        fab_last_user.setOnClickListener {
            //get values
            val userName = sharedPref.getString(Constants.USER_NAME, "No User")
            val wrongAnswers = sharedPref.getInt(Constants.WRONG_ANSWERS, 0)

            //setting a message
            var message: String = if(wrongAnswers > 0){
                "$userName was flagged during the previous screening"
            } else {
                "$userName passed the previous screening"
            }

            //creating AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Previous Screening")
            builder.setMessage(message)
            builder.setNegativeButton("Thanks", DialogInterface.OnClickListener{ dialog, _ ->
                dialog.dismiss()
            })

            builder.show()
        }

    }
}