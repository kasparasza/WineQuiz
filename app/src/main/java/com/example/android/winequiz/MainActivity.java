package com.example.android.winequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts the quiz after user click.
     */
    public void goToQuestionOne (View view) {
        Intent goToQuest_01 = new Intent(this, Question_01.class);
        // Clears the back stack, so that the user will not be able to navigate back to the activity with the back button
        goToQuest_01.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        goToQuest_01.putExtra("EXIT", false);
        startActivity(goToQuest_01);
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
}
