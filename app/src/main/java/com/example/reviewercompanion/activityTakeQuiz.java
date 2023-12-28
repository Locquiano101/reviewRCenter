package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class activityTakeQuiz extends AppCompatActivity {
    TextView question_text, remaining_question;
    RadioGroup group_choice;
    RadioButton choice_a, choice_b, choice_c, choice_d;
    Button next_button;
    DatabaseScores databaseScores;
    DatabaseQuestions databaseQuestions;
    String _quiz_category, _correct_answer, _selected_answer,
            _question, _choice_1, _choice_2,
            _choice_3, _choice_4, _answer;
    int _quiz_limit, _current_question_num, _score;

    ArrayList<DatabaseVariable> questionArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        // Initialize views
        initializeViews();

        // Initialize database instances
        databaseScores = new DatabaseScores(this);
        databaseQuestions = new DatabaseQuestions(this);

        // Get data from Intent
        _quiz_limit = getIntent().getIntExtra("total_question_num", 0);
        _quiz_category = getIntent().getStringExtra("quiz_category");
        // Fetch questions and set dataLog.d
        Log.d("ScoreView", "_quiz_limit: " + _quiz_limit);
        fetchQuestionsAndSetData();
    }

    private void initializeViews() {
        question_text = findViewById(R.id.question_text);
        remaining_question = findViewById(R.id.remaining_question);
        group_choice = findViewById(R.id.group_choice);
        choice_a = findViewById(R.id.choice_a);
        choice_b = findViewById(R.id.choice_b);
        choice_c = findViewById(R.id.choice_c);
        choice_d = findViewById(R.id.choice_d);
        next_button = findViewById(R.id.next_button);
    }

    private void fetchQuestionsAndSetData() {
        questionArray = databaseQuestions.FetchQuestionsByCategory(_quiz_category, _quiz_limit);

        if (questionArray.size() > 0) {
            setData();
        } else {
            // Handle the case when no questions are fetched for the given category and limit
            Toast.makeText(this, "No questions available for this category", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void setData() {
        Collections.shuffle(questionArray);

        _question = questionArray.get(_current_question_num).question;
        _choice_1 = questionArray.get(_current_question_num).choice_1;
        _choice_2 = questionArray.get(_current_question_num).choice_2;
        _choice_3 = questionArray.get(_current_question_num).choice_3;
        _choice_4 = questionArray.get(_current_question_num).choice_4;
        _answer = questionArray.get(_current_question_num).answer;

        ArrayList<String> choices = new ArrayList<>();
        choices.add(_choice_1);
        choices.add(_choice_2);
        choices.add(_choice_3);
        choices.add(_choice_4);

        Collections.shuffle(choices);

        choice_a.setText(choices.get(0));
        choice_b.setText(choices.get(1));
        choice_c.setText(choices.get(2));
        choice_d.setText(choices.get(3));

        remaining_question.setText((_current_question_num + 1) + "/" + questionArray.size());

        question_text.setText(_question);
    }

    // Declare a boolean flag
    private boolean canClickButton = true;

    public void next_question(final View view) {

        if (!canClickButton) {
            return; // Prevent button click if the flag is false
        }

        next_button.setEnabled(false); // Disable the button
        canClickButton = false; // Update flag to prevent further clicks

        int selectedRadioButtonId = group_choice.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();
            next_button.setEnabled(true); // Enable the button back
            canClickButton = true; // Update flag to allow clicks
        } else {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            _selected_answer = selectedRadioButton.getText().toString();

            // Fetch the correct answer based on the question number
            _correct_answer = questionArray.get(_current_question_num).answer;

            if (_selected_answer.equals(_correct_answer)) {
                Toast.makeText(this, "You're Right!", Toast.LENGTH_SHORT).show();
                _score++;
            } else {
                Toast.makeText(this, "You're Wrong! The right answer is: " + _correct_answer, Toast.LENGTH_SHORT).show();
            }

            // Clear the radio button selection after processing the answer

            // Set a delay to stay on the same question for 2 seconds before proceeding
            new Handler().postDelayed(() -> {
                _current_question_num++; // Increment the question number

                if (_current_question_num < questionArray.size()) {
                    group_choice.clearCheck();
                    setData(); // Load the next question
                } else {
                    // All questions have been asked, go to activityViewScore
                    Toast.makeText(getApplicationContext(), "Test is completed\nCorrect Answers = " + _score, Toast.LENGTH_SHORT).show();

                    addScore();
                    finishQuiz();
                }

                next_button.setEnabled(true);
                canClickButton = true; // Update flag to allow clicks after delay
            }, 2000); // Delay for 2 seconds (2000 milliseconds)
        }
    }


    public void finishQuiz() {
        Intent intent = new Intent(this, activityViewScore.class);
        Log.d("ScoreView", "_quiz_limit: " + _quiz_limit);

        intent.putExtra("score", _score);
        intent.putExtra("quiz_limit", _quiz_limit);
        intent.putExtra("quiz_category", _quiz_category);

        startActivity(intent);
    }

    @SuppressLint("SimpleDateFormat")
    public void addScore() {
        long currentDateTimeMillis = System.currentTimeMillis();
        Date currentDateTime = new Date(currentDateTimeMillis);

        String _total_score = _score + "/" + _quiz_limit;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDateTime = dateFormat.format(currentDateTime);

        DatabaseScores _MyScoreDatabaseHelper = new DatabaseScores(this);

        _MyScoreDatabaseHelper.addScore(_total_score, formattedDateTime);
    }
}
