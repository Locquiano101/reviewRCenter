package com.example.reviewercompanion;

import static com.example.reviewercompanion.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activityQuizCategory extends AppCompatActivity {
    public static int total_question_num;
    public static String category;
    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_quiz_category);

        autoCompleteTextView = findViewById(R.id.question_num);

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
            category = "auditing"; // Set String 2 for cardView2
        } else if (v.getId() == R.id.auditing) {
            category = "law"; // Set String 1 for cardView1
        }else if (v.getId() == R.id.tax) {
            category = "law"; // Set String 1 for cardView1
        }else if (v.getId() == R.id.far) {
            category = "law"; // Set String 1 for cardView1
        }else if (v.getId() == R.id.afar) {
            category = "law"; // Set String 1 for cardView1
        } else if (v.getId() == R.id.ms) {
            category = "law"; // Set String 1 for cardView1
        }else {
            category = "Default String"; // Set a default string if needed
        }
        Toast.makeText(activityQuizCategory.this, "Card String: " + category, Toast.LENGTH_SHORT).show();
    }
    public void take_quiz(View view) {
        String selectedNumber = autoCompleteTextView.getText().toString();
        total_question_num = Integer.parseInt(selectedNumber);

        Intent intent = new Intent(this, activityTakeQuiz.class);
        intent.putExtra("total_num", total_question_num);
        intent.putExtra("quiz_category", category);
        startActivity(intent);
    }
}