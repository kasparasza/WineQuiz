package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Question_01 extends AppCompatActivity {

    // variables required to save values at screen rotation
    private static final String OPTION_A = "option_A_status";
    private static final String OPTION_B = "option_B_status";
    private static final String OPTION_C = "option_C_status";
    private static final String OPTION_D = "option_D_status";
    private static final String USER_SCORE = "user_score_status";

    // other variables
    private ImageButton optionAButton;
    private ImageButton optionBButton;
    private ImageButton optionCButton;
    private ImageButton optionDButton;
    private boolean answerA;
    private boolean answerB;
    private boolean answerC;
    private boolean answerD;
    private int userScore = 0; // <--- we initialize userScore to zero only in the 1st question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensures that the activity is not started when user clicks back button
        if (getIntent().getBooleanExtra("EXIT", true)) {
            finish();
        }
        // remaining part of the usual code
        setContentView(R.layout.activity_layout_type_01);
        optionAButton = (ImageButton) findViewById(R.id.answerOptionA_Layout_type_01);
        optionBButton = (ImageButton) findViewById(R.id.answerOptionB_Layout_type_01);
        optionCButton = (ImageButton) findViewById(R.id.answerOptionC_Layout_type_01);
        optionDButton = (ImageButton) findViewById(R.id.answerOptionD_Layout_type_01);
    }

    /**
     * Starts new activity - goes to next question
     */
    public void changeQuestion(View view) {
        // Checks if the user has selected an answer <--- all conditions are false == no answer selected
        if (!answerA && !answerB && !answerC && !answerD) {
            Toast.makeText(this, R.string.toastNoAnswerSelected, Toast.LENGTH_SHORT).show();
        } else {
            // Checks the answer for correctness
            // For Q1 correct answer is "C".
            if (answerC) {
                userScore += 1;
                Toast.makeText(this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.incorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            // Creates an intent to start a new activity / to go to next question
            Intent goToNextQuestion = new Intent(this, Question_02.class);
            goToNextQuestion.putExtra("CURRENT_SCORE", userScore);
            //// Clears the back stack, so that the user will not be able to navigate back to the activity with the back button
            goToNextQuestion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            goToNextQuestion.putExtra("EXIT", false);
            startActivity(goToNextQuestion);
        }
    }

    /**
     * Brings the user back to the start screen and effectively deletes the current quiz result
     */
    public void goToStartOfApp(View view) {
        Intent goToStart = new Intent(this, MainActivity.class);
        startActivity(goToStart);
    }

    /**
     * Tracks which answer is selected by the user and assigns it with a boolean.
     * The answers are mutually exclusive. Each time a different answer is selected, the previous answer option is deselected.
     */
    public void selectAnswerOption(View view) {
        //Makes the button to change appearance when pressed
        if (optionAButton.isPressed()) {
            optionAButton.setImageResource(R.drawable.question_01_answer_pict_01_pressed);
            optionBButton.setImageResource(R.drawable.question_01_answer_pict_02);
            optionCButton.setImageResource(R.drawable.question_01_answer_pict_03);
            optionDButton.setImageResource(R.drawable.question_01_answer_pict_04);
            //assigns a boolean for an answer option
            answerA = true;
            answerB = answerC = answerD = false;
        } else if (optionBButton.isPressed()) {
            optionAButton.setImageResource(R.drawable.question_01_answer_pict_01);
            optionBButton.setImageResource(R.drawable.question_01_answer_pict_02_pressed);
            optionCButton.setImageResource(R.drawable.question_01_answer_pict_03);
            optionDButton.setImageResource(R.drawable.question_01_answer_pict_04);
            answerB = true;
            answerA = answerC = answerD = false;
        } else if (optionCButton.isPressed()) {
            optionAButton.setImageResource(R.drawable.question_01_answer_pict_01);
            optionBButton.setImageResource(R.drawable.question_01_answer_pict_02);
            optionCButton.setImageResource(R.drawable.question_01_answer_pict_03_pressed);
            optionDButton.setImageResource(R.drawable.question_01_answer_pict_04);
            answerC = true;
            answerA = answerB = answerD = false;
        } else if (optionDButton.isPressed()) {
            optionAButton.setImageResource(R.drawable.question_01_answer_pict_01);
            optionBButton.setImageResource(R.drawable.question_01_answer_pict_02);
            optionCButton.setImageResource(R.drawable.question_01_answer_pict_03);
            optionDButton.setImageResource(R.drawable.question_01_answer_pict_04_pressed);
            answerD = true;
            answerA = answerB = answerC = false;
        }
    }

    /**
     * Saves variables in order to recreate them after screen rotation.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(OPTION_A, answerA);
        savedInstanceState.putBoolean(OPTION_B, answerB);
        savedInstanceState.putBoolean(OPTION_C, answerC);
        savedInstanceState.putBoolean(OPTION_D, answerD);
        savedInstanceState.putInt(USER_SCORE, userScore);
    }

    /**
     * Recreates variables after screen rotation.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        answerA = savedInstanceState.getBoolean(OPTION_A);
        answerB = savedInstanceState.getBoolean(OPTION_B);
        answerC = savedInstanceState.getBoolean(OPTION_C);
        answerD = savedInstanceState.getBoolean(OPTION_D);
        userScore = savedInstanceState.getInt(USER_SCORE);
    }
}
