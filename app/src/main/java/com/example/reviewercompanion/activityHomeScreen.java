package com.example.reviewercompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activityHomeScreen extends AppCompatActivity {

    DatabaseQuestions DatabaseQuestions = new DatabaseQuestions(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
    public void startActivityBasedOnButton(View view) {
        Intent intent = null; // Initialize intent to null
        if (view.getId() == R.id.take_quiz) {
            intent = new Intent(this, activityQuizCategory.class);
        } else if (view.getId() == R.id.score_history) {
            intent = new Intent(this, activityScoreHistory.class);
        } else if (view.getId() == R.id.setting) {
            intent = new Intent(this, activitySettings.class);
        } else if (view.getId() == R.id.about_me) {
            intent = new Intent(this, activityAboutMe.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}