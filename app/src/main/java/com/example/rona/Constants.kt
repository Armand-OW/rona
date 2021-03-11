package com.example.rona

object Constants {

    const val USER_NAME : String = "username"
    const val WRONG_ANSWERS : String = "wrongAnswers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Have you had close contact with anyone who has tested positive for Covid-19?",
            R.drawable.ic_close_contact,
            "Yes, I have been in contact.",
            "No, all about that social distance life."
        )

        val que2 = Question(
            2,
            "Have you experienced any cold- or flu-like symptoms?",
            R.drawable.ic_temperature,
            "Yes, it's getting hot in here.",
            "No, I feel great."
        )

        val que3 = Question(
            3,
            "Have you been tested for Covid-19 and still waiting for the results?",
            R.drawable.ic_tested,
            "Yes, my nose hurts from the swab.",
            "No, haven't been tested or waiting."
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)

        return questionsList
    }
}