package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Question_04 extends AppCompatActivity {

    // variables required to save values at screen rotation
    private static final String USER_SCORE = "user_score_status";

    // other variables
    private CheckBox optionACheckBox;
    private CheckBox optionBCheckBox;
    private CheckBox optionCCheckBox;
    private CheckBox optionDCheckBox;
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
        setContentView(R.layout.activity_layout_type_03);
        optionACheckBox = (CheckBox) findViewById(R.id.answerOptionA_Layout_type_03);
        optionBCheckBox = (CheckBox) findViewById(R.id.answerOptionB_Layout_type_03);
        optionCCheckBox = (CheckBox) findViewById(R.id.answerOptionC_Layout_type_03);
        optionDCheckBox = (CheckBox) findViewById(R.id.answerOptionD_Layout_type_03);
    }

    /**
     * Tracks which answers are selected by the user and assigns them with booleans.
     * Afterwards calls a method for answer evaluation.
     */
    public void checkAnswer(View view) {
        boolean answerA = optionACheckBox.isChecked();
        boolean answerB = optionBCheckBox.isChecked();
        boolean answerC = optionCCheckBox.isChecked();
        boolean answerD = optionDCheckBox.isChecked();
        // Checks if the user has selected an answer <--- if all conditions are false == no answer selected
        // If the selection is made - method to evaluate answer is called.
        if (!answerA && !answerB && !answerC && !answerD) {
            Toast.makeText(this, R.string.toastNoAnswerSelected_02, Toast.LENGTH_SHORT).show();
        } else {
            changeQuestion(answerA, answerB, answerC, answerD);
        }
    }

    /**
     * Evaluates the answers provided and starts a new activity
     * For Q4 correct answers are "C" and "D".
     */
    public void changeQuestion(boolean ans_A, boolean ans_B, boolean ans_C, boolean ans_D) {
        if (ans_C && ans_D && !ans_A && !ans_B) {
            userScore += 1;
            Toast.makeText(this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrectAnswer, Toast.LENGTH_SHORT).show();
        }
        // Creates an intent to start a new activity / to go to next question
        Intent goToNextQuestion = new Intent(this, Question_05.class);
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
