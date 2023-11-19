package com.example.reviewercompanion;

import static com.example.reviewercompanion.R.id;
import static com.example.reviewercompanion.R.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activityQuizCategory extends AppCompatActivity {
    public static int total_question_num;
    static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_quiz_category);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
}