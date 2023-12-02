package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class activityTakeQuiz extends AppCompatActivity {
    TextView question_text, remaining_question;
    RadioGroup group_choice;
    RadioButton choice_a, choice_b, choice_c, choice_d;
    Button next_button;
    DatabaseScores DatabaseScores = new DatabaseScores(this);
    DatabaseQuestions DatabaseQuestions = new DatabaseQuestions(this);
    String _quiz_category, _correct_answer, _selected_answer,
            _question, _choice_1, _choice_2,
            _choice_3, _choice_4, _answer;
    int _quiz_limit, _current_question_num, _correct_selected_answer;
    ArrayList<DatabaseVariable> QuestionArray = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        question_text = findViewById(R.id.question_text);
        remaining_question = findViewById(R.id.remaining_question);

        group_choice = findViewById(R.id.group_choice);

        choice_a = findViewById(R.id.choice_a);
        choice_b = findViewById(R.id.choice_b);
        choice_c = findViewById(R.id.choice_c);
        choice_d = findViewById(R.id.choice_d);

        next_button = findViewById(R.id.next_button);

        _quiz_limit = getIntent().getIntExtra("total_question_num", 0);
        _quiz_category = getIntent().getStringExtra("quiz_category");
        DatabaseQuestions.FetchQuestionsByCategory(_quiz_category,_quiz_limit);

    }
}
