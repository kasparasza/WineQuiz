package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Evaluation extends AppCompatActivity {

    // variables required to save values at screen rotation
    private static final String USER_SCORE = "user_score_status";

    // other variables
    private TextView evaluationScoreMessage;
    private TextView evaluationMessage;
    private ImageView evaluationPicture;
    int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensures that the activity is not started when user clicks back button
        if (getIntent().getBooleanExtra("EXIT", true)) {
            finish();
        }
        // remaining part of the usual code
        userScore = getIntent().getIntExtra("CURRENT_SCORE", 0);
        setContentView(R.layout.activity_evaluation);
        evaluationScoreMessage = (TextView) findViewById(R.id.evalScreenScoreMessage);
        evaluationMessage = (TextView) findViewById(R.id.evalScreenScoreMessage_02);
        evaluationPicture = (ImageView) findViewById(R.id.evalScreenPicture);
        evaluateQuizScore(userScore);
    }

    /**
     * Displays the total score on screen as a toast,
     * and one of the 3 possible evaluation messages based on it.
     */
    public void evaluateQuizScore(int score) {
        String scoreMessage = getResources().getString(R.string.evalScreen_02) + score + getResources().getString(R.string.evalScreen_03);
        Toast.makeText(this, scoreMessage, Toast.LENGTH_LONG).show();
        evaluationScoreMessage.setText(scoreMessage);
        if (score >= 8) {
            evaluationMessage.setText(R.string.evalScreen_04_best);
            evaluationPicture.setImageResource(R.drawable.evaluation_01);
        } else if (score >= 4) {
            evaluationMessage.setText(R.string.evalScreen_04_medium);
            evaluationPicture.setImageResource(R.drawable.evaluation_02);
        } else {
            evaluationMessage.setText(R.string.evalScreen_04_poorest);
            evaluationPicture.setImageResource(R.drawable.evaluation_03);
        }
    }

    /**
     * Restarts the quiz from Q1 after "Retry quiz" is pressed
     */
    public void goToQuestionOne(View view) {
        Intent goToQuestionOne = new Intent(this, Question_01.class);
        //// Clears the back stack, so that the user will not be able to navigate back to the activity with the back button
        goToQuestionOne.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        goToQuestionOne.putExtra("EXIT", false);
        startActivity(goToQuestionOne);
    }

    /**
     * Closes the app after "Close app" is pressed
     */
    public void closeApp(View view) {
        finish();
    }

    /**
     * Opens an activity with additional reading material
     */
    public void goToReadingMaterial(View view) {
        Intent goToRead = new Intent(this, ReadMore.class);
        // Clears the back stack, so that the user will not be able to navigate back to the activity with the back button
        goToRead.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        goToRead.putExtra("EXIT", false);
        startActivity(goToRead);
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
