package com.example.reviewercompanion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activityLoading extends AppCompatActivity {
    private int progressStatus = 0;
    private final Handler handler = new Handler();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar);

        updateProgressBar(progressBar, handler);

    }

    /* TODO :baguhin to into the length of the question being added and then if done na mag kakaroon ng
        if statement na mag loloading na lang
    */
    public void updateProgressBar(final ProgressBar progressBar, final Handler handler) {
        new Thread(() -> {

            while (progressStatus < 100) {
                progressStatus++;
                SystemClock.sleep(50);

                handler.post(() -> progressBar.setProgress(progressStatus));
            }
            handler.post(() -> Toast.makeText(activityLoading.this, "Process completed", Toast.LENGTH_SHORT).show());
            Intent intent = new Intent(this, activityHomeScreen.class);
            startActivity(intent);

        }).start();
    }

    /* TODO : create an instance where the progress bar is affected by the question size */
    public void InsertInitialQuestions() {
        runOnUiThread(() -> {
            Toast.makeText(activityLoading.this, "Process On Going...", Toast.LENGTH_SHORT).show();
        });
    }

    /*
        public void InsertInitialQuestions() {
        MyQuestionDatabaseHelper myDB = new MyQuestionDatabaseHelper(MainActivity.this);

        if (myDB.isTableEmpty()) { //---------- table exist but has no data ----------
            Toast.makeText(this, "adding initial questions, Please wait...", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < questionTexts.length; i++) {
                myDB.CreateQuestion(questionTexts[i], answerAs[i], answerBs[i], answerCs[i], answerDs[i], correctAnswers[i]);
            }
        } else { // ---------- table exist and has data ----------
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        }
    }
    */

}