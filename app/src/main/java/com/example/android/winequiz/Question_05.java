package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Question_05 extends AppCompatActivity {

    // variables required to save values at screen rotation
    private static final String USER_SCORE = "user_score_status";

    // other variables
    private RadioGroup allQuestionAnswers;
    private RadioButton selectedAnswerA;
    private RadioButton selectedAnswerB;
    private RadioButton selectedAnswerC;
    private RadioButton selectedAnswerD;
    private TextView questionNumber;
    private TextView questionText;
    private ImageView questionPicture;
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
        setContentView(R.layout.activity_layout_type_04);
        allQuestionAnswers = (RadioGroup) findViewById(R.id.allAnswers_Layout_type_04);
        selectedAnswerA = (RadioButton) findViewById(R.id.answerOptionA_Layout_type_04);
        selectedAnswerB = (RadioButton) findViewById(R.id.answerOptionB_Layout_type_04);
        selectedAnswerC = (RadioButton) findViewById(R.id.answerOptionC_Layout_type_04);
        selectedAnswerD = (RadioButton) findViewById(R.id.answerOptionD_Layout_type_04);
        questionNumber = (TextView) findViewById(R.id.questionNumber_Layout_type_04);
        questionText = (TextView) findViewById(R.id.questionText_Layout_type_04);
        questionPicture = (ImageView) findViewById(R.id.picture_Layout_type_04);
        selectedAnswerA.setText(R.string.question_05_answer_A);
        selectedAnswerB.setText(R.string.question_05_answer_B);
        selectedAnswerC.setText(R.string.question_05_answer_C);
        selectedAnswerD.setText(R.string.question_05_answer_D);
        questionNumber.setText(R.string.question_05);
        questionText.setText(R.string.question_05_text);
        questionPicture.setImageResource(R.drawable.question_05_picture);
    }

    /**
     * Tracks which answers are selected by the user and assigns them with booleans.
     * Afterwards calls a method for answer evaluation.
     */
    public void checkAnswer(View view) {
        boolean answerA = false;
        boolean answerB = false;
        boolean answerC = false;
        boolean answerD = false;
        // gets selected radio button from radioGroup
        int selectedAnswerId = allQuestionAnswers.getCheckedRadioButtonId();
        // finds the radio button by returned id and assigns it with a boolean
        RadioButton selectedAnswer = (RadioButton) findViewById(selectedAnswerId);
        if (selectedAnswerA == selectedAnswer) {
            answerA = true;
        } else if (selectedAnswerB == selectedAnswer) {
            answerB = true;
        } else if (selectedAnswerC == selectedAnswer) {
            answerC = true;
        } else if (selectedAnswerD == selectedAnswer) {
            answerD = true;
        }
        // Checks if the user has selected an answer <--- if all conditions are false == no answer selected
        // If the selection is made - method to evaluate answer is called.
        if (!answerA && !answerB && !answerC && !answerD) {
            Toast.makeText(this, R.string.toastNoAnswerSelected, Toast.LENGTH_SHORT).show();
        } else {
            changeQuestion(answerD);
        }
    }

    /**
     * Evaluates the answers provided and starts a new activity
     * For Q5 correct answer is "D".
     */
    public void changeQuestion(boolean ans_D) {
        if (ans_D) {
            userScore += 1;
            Toast.makeText(this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrectAnswer, Toast.LENGTH_SHORT).show();
        }
        // Creates an intent to start a new activity / to go to next question
        Intent goToNextQuestion = new Intent(this, Question_06.class);
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
