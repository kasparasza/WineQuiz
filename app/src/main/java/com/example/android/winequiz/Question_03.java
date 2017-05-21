package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Question_03 extends AppCompatActivity {

    // variables required to save values at screen rotation
    private static final String USER_SCORE = "user_score_status";

    // other variables
    private EditText hintText;
    private int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensures that the activity is not started when user clicks back button
        if (getIntent().getBooleanExtra("EXIT", true)) {
            finish();
        }
        // remaining part of the usual code
        userScore = getIntent().getIntExtra("CURRENT_SCORE", 0);
        setContentView(R.layout.activity_layout_type_02);
        hintText = (EditText) findViewById(R.id.answerEntered_Layout_type_02);
    }

    /**
     * Checks if the user has entered an answer that corresponds to the set conditions: is [0 ; 20]
     */
    public void checkAnswer(View view) {
        String answerEnteredString = hintText.getText().toString();
        // Check #1 - checks if the user has entered any answer at all
        if (!answerEnteredString.matches("")) {
            int usersAnswer = Integer.parseInt(answerEnteredString);
            // Check #2 - checks if the user has entered a valid integer
            if (usersAnswer >= 0 && usersAnswer <= 20) {
                changeQuestion(usersAnswer);
            } else {
                Toast.makeText(this, R.string.toastNoAnswerEntered_01, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toastNoAnswerEntered_01, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Starts new activity - goes to next question
     */
    public void changeQuestion(int answer) {
        // Checks the answer for correctness
        // For Q3 correct answer is "between 6 & 10".
        if (answer >= 6 && answer <= 10) {
            userScore += 1;
            Toast.makeText(this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrectAnswer, Toast.LENGTH_SHORT).show();
        }
        // Creates an intent to start a new activity / to go to next question
        Intent goToNextQuestion = new Intent(this, Question_04.class);
        goToNextQuestion.putExtra("CURRENT_SCORE", userScore);
        //// Clears the back stack, so that the user will not be able to navigate back to the activity with the back button
        goToNextQuestion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        goToNextQuestion.putExtra("EXIT", false);
        startActivity(goToNextQuestion);
    }

    /**
     * Brings the user back to the start screen and effectively deletes the current quiz result
     */
    public void goToStartOfApp(View view) {
        Intent goToStart = new Intent(this, MainActivity.class);
        startActivity(goToStart);
    }

    /**
     * Saves variables in order to recreate them after screen rotation.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(USER_SCORE, userScore);
    }

    /**
     * Recreates variables after screen rotation.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userScore = savedInstanceState.getInt(USER_SCORE);
    }
}
