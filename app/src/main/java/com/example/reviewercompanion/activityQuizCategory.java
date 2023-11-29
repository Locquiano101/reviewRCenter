package com.example.reviewercompanion;

import static com.example.reviewercompanion.R.layout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activityQuizCategory extends AppCompatActivity {
    public static String category;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_quiz_subject);

        autoCompleteTextView = findViewById(R.id.total_question_num);

        String[] numbers = new String[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = String.valueOf((i + 1) * 10);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, numbers);

        autoCompleteTextView.setAdapter(adapter);

        // Set the threshold to 1 so suggestions appear from the first character
        autoCompleteTextView.setThreshold(1);

    }

    public void startActivityBasedOnButton(View v) {
        // Differentiate between CardViews by their IDs
        if (v.getId() == R.id.law) {
            category = "Law";
        } else if (v.getId() == R.id.auditing) {
            category = "Auditing";
        } else if (v.getId() == R.id.tax) {
            category = "Tax";
        } else if (v.getId() == R.id.far) {
            category = "FAR";
        } else if (v.getId() == R.id.afar) {
            category = "AFAR";
        } else if (v.getId() == R.id.ms) {
            category = "MS";
        } else {
            category = null;
        }

        /*MAKE THIS INTO COLOR CHANGING SHIT*/
        Toast.makeText(activityQuizCategory.this, "Card String: " + category, Toast.LENGTH_SHORT).show();
    }



//        String selectedNumber = autoCompleteTextView.getText().toString();
//  public void take_quiz(View view) {
//
//        // Inside your Activity or Fragment
//        DatabaseQuestions dbHelper = new DatabaseQuestions(this);
//
//        // Call FetchQuestionsByCategory with the desired category and limit
//        ArrayList<DatabaseVariable> questions = dbHelper.FetchQuestionsByCategory("AFAR", Integer.parseInt("10"));
//
//        // Log the retrieved questions in Logcat
//        for (DatabaseVariable question : questions) {
//            Log.d("Question", "Subject: " + question.subject);
//            Log.d("Question", "Question: " + question.question);
//        }
//        if (!selectedNumber.isEmpty()) {
//            try {
//                int total_question_num = Integer.parseInt(selectedNumber);
//                if (category != null) {
//                    Intent intent = new Intent(this, activityTakeQuiz.class);
//
//                    intent.putExtra("total_question_num", total_question_num);
//                    intent.putExtra("quiz_category", category);
//
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "Please Select quiz category", Toast.LENGTH_SHORT).show();
//                }
//            } catch (NumberFormatException e) {
//                Toast.makeText(this, "Invalid number format for total number of questions", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Please Select total number of questions", Toast.LENGTH_SHORT).show();
//        }
    }
}