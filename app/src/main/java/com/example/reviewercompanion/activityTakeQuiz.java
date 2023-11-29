package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class activityTakeQuiz extends AppCompatActivity {

    TextView question_text, remaining_question;
    RadioGroup group_choice;
    RadioButton choice_a, choice_b, choice_c, choice_d;
    Button next_button;
    DatabaseScores _ScoreHelper = new DatabaseScores(this);
    DatabaseQuestions DatabaseQuestions = new DatabaseQuestions(this);
    String quiz_category, correctAnswer, _selectedAnswer, _question, _choice_1, _choice_2, _choice_3, _choice_4, _answer;
    int total_question_num = 10;
    int correctAns;
    ArrayList<DatabaseVariable> getQuestion = new ArrayList<>();

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

//        total_question_num = getIntent().getIntExtra("total_question_num", 0);
        quiz_category = getIntent().getStringExtra("quiz_category");

//        setData();
    }

    // TODO: METHOD     DATA PARA SA PAG SET NG TEXT SA QUESTIONS


    public void next_question(View view) {
        int selectedRadioButtonId = group_choice.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            _selectedAnswer = selectedRadioButton.getText().toString();

            if (total_question_num < getQuestion.size()) {
                correctAnswer = getQuestion.get(total_question_num).answer; // Retrieve the correct answer
                if (_selectedAnswer.equals(_answer)) {
                    Toast.makeText(this, "You're Right!", Toast.LENGTH_SHORT).show();
                    correctAns++;
                } else {
                    Toast.makeText(this, "You're Wrong! The right answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
                }
            }

            group_choice.clearCheck();
            new Handler().postDelayed(() -> {
                total_question_num++;
                if (total_question_num < getQuestion.size()) {
                } else {
                    Toast.makeText(this, "Test is completed\nCorrect Answers = " + correctAns, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, activityScoreHistory.class));
                    addScore();
                }
            }, 0);
        }
    }

    public void addScore() {
        long currentDateTimeMillis = System.currentTimeMillis();
        Date currentDateTime = new Date(currentDateTimeMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm a");
        String formattedDateTime = dateFormat.format(currentDateTime);


        String InsertScoreToSql = correctAns + "/" + getQuestion.size();
        _ScoreHelper.addScore(InsertScoreToSql, formattedDateTime);

    }
}
