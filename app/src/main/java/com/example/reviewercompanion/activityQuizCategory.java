package com.example.reviewercompanion;

import static com.example.reviewercompanion.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class activityQuizCategory extends AppCompatActivity {
    public static int total_question_num;
    static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_quiz_category);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.question_num);

        // Array of numbers from 10 to 100 in increments of 10z
        String[] numbers = new String[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = String.valueOf((i + 1) * 10);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, numbers);

        autoCompleteTextView.setAdapter(adapter);

        // Set the threshold to 1 so suggestions appear from the first character
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedNumber = (String) parent.getItemAtPosition(position);
            int selectedInt = Integer.parseInt(selectedNumber);

            Intent intent = new Intent(activityQuizCategory.this, activityTakeQuiz.class);
            intent.putExtra("selectedNumber", selectedInt);
            startActivity(intent);
        });
    }
}