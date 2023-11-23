package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

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

        //if (isTable.empty()){
        InsertInitialQuestions();
        //  } else {
        //     Intent intent = new Intent(activityLoading.this, activityHomeScreen.class);
        //     startActivity(intent);
        //  }
    }

    /* TODO :baguhin to into the length of the question being added and then if done na mag kakaroon ng
        if statement na mag loloading na lang
        tuloy tuloy padin pag add ng question. change that shit.
    */
    @SuppressLint("StaticFieldLeak")
    public void InsertInitialQuestions() {
        ProgressBar progressBar = findViewById(R.id.progressBar);

        DatabaseQuestions myDB = new DatabaseQuestions(this);

        DatabaseVariableCategory variableCategory = new DatabaseVariableCategory();

        String[] questionTexts = variableCategory.afarQuestions();
        progressBar.setVisibility(View.VISIBLE); // Show the progress bar

        // Use AsyncTask to perform database operations in the background
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int totalQuestions = questionTexts.length;
                for (int i = 1; i < totalQuestions; i++) {
                    myDB.insertQuestion(null, null, questionTexts[i],null,null,null,null,null);
                    // For illustration purposes, I'm using a sleep to simulate the insertion
                    try {
                        Thread.sleep(500); // Adjust delay time as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update progress based on the inserted question count
                    publishProgress((i + 1) * 100 / totalQuestions);
                }
                return null;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                progressBar.setProgress(values[0]); // Update progress bar
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Database insertion completed
                progressBar.setVisibility(View.GONE);
                try {
                    Thread.sleep(3000); // Adjust delay time as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }// Hide progress bar
                Intent intent = new Intent(activityLoading.this, activityHomeScreen.class);
                startActivity(intent);
            }
        }.execute();
    }
}