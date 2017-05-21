package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ReadMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_more);
    }

    /**
     * Starts the quiz from Q1 after "Start quiz" is pressed
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
}

