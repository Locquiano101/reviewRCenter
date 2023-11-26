package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class activityLoading extends AppCompatActivity {
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar);

        InsertInitialQuestions();
    }


    @SuppressLint("StaticFieldLeak")
    public void InsertInitialQuestions() {
        ProgressBar progressBar = findViewById(R.id.progressBar);

        DatabaseQuestions myDB = new DatabaseQuestions(this);

        DatabaseVariableCategory variableCategory = new DatabaseVariableCategory();

        String[] Category = variableCategory.Category;
        String[][] Questions = variableCategory.questions;
        String[][] Choice_A = variableCategory.ChoiceA;
        String[][] Choice_B = variableCategory.ChoiceB;
        String[][] Choice_C = variableCategory.ChoiceC;
        String[][] Choice_D = variableCategory.ChoiceD;
        String[][] Answers = variableCategory.CorrectAns;

        final int[] totalQuestions = {0};
        final int[] currentQuestion = {0};
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                for (int x = 0; x < Questions.length; x++) {
                    totalQuestions[0] += Questions[x].length;
                }

                for (int x = 0; x < Questions.length; x++) {
                    for (int y = 0; y < Questions[x].length; y++) {
                        myDB.insertQuestion(
                                Category[x], Questions[x][y],
                                Choice_A[x][y], Choice_B[x][y],
                                Choice_C[x][y], Choice_D[x][y],
                                Answers[x][y]
                        );

                        currentQuestion[0]++;

                        try {
                            Thread.sleep(10); // Adjust delay time as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Update progress based on the total number of questions
                        publishProgress((currentQuestion[0] * 100) / totalQuestions[0]);
                    }
                }
                return null;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
//                 Update the progress bar here with values[0]
                progressBar.setProgress(values[0]);
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Task completed, hide or handle the progress bar as needed
                progressBar.setVisibility(View.GONE); // Hide the progress bar
                // Perform any UI updates or operations after the insertion completes
            }
        }.execute();
    }
}