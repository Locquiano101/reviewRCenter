package com.example.reviewercompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class activityTakeQuiz extends AppCompatActivity {

    TextView question_text, remaining_question;
    RadioGroup group_choice;
    RadioButton choice_a, choice_b, choice_c, choice_d;
    Button next_button;
    String _question, _choice_1, _choice_2, _choice_3, _choice_4, _answer;
    String _selectedAnswer;
    String correctAnswer;
    static String formattedDateTime;
    int question_num, correctAns = 0;
    ArrayList<DatabaseVariable> getQuestion = new ArrayList<>();

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

        // setData();
    }
    /*
    public void setData() {
        MyQuestionDatabaseHelper _myQuestionDataHelper = new MyQuestionDatabaseHelper(this);
        getQuestion = _myQuestionDataHelper.ReadQuestions();
        Collections.shuffle(getQuestion);

        _question = getQuestion.get(question_num).question;
        _choice_1 = getQuestion.get(question_num).choice_1;
        _choice_2 = getQuestion.get(question_num).choice_2;
        _choice_3 = getQuestion.get(question_num).choice_3;
        _choice_4 = getQuestion.get(question_num).choice_4;
        _answer = getQuestion.get(question_num).answer;

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

        remaining_question.setText((question_num + 1) + "/" + getQuestion.size());

        question_text.setText(_question);
    }

    public void next_question(View view) {
        int selectedRadioButtonId = group_choice.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();

        } else {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            _selectedAnswer = selectedRadioButton.getText().toString();

            if (question_num < getQuestion.size()) {
                correctAnswer = getQuestion.get(question_num).answer;
                if (_selectedAnswer.equals(correctAnswer)) {
                    RingCorrect();
                    Toast.makeText(this, "You're Right!", Toast.LENGTH_SHORT).show();
                    correctAns++;
                } else {
                    RingWrong();
                    Toast.makeText(this, "You're Wrong! The right answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
                }
            }

            group_choice.clearCheck();
            new Handler().postDelayed(() -> {
                question_num++;
                if (question_num < getQuestion.size()) {
                    setData();
                } else {
                    Toast.makeText(this, "Test is completed\nCorrect Answers = " + correctAns, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    addScore();
                }
            }, 0);
        }
    }

    public void addScore() {
        long currentDateTimeMillis = System.currentTimeMillis();
        Date currentDateTime = new Date(currentDateTimeMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm a");
        formattedDateTime = dateFormat.format(currentDateTime);

        MyScoreDatabaseHelper _MyScoreDatabaseHelper = new MyScoreDatabaseHelper(this);

        String InsertScoreToSql = correctAns + "/" + getQuestion.size();
        _MyScoreDatabaseHelper.addScore(InsertScoreToSql, formattedDateTime);

    }

    public void AlertButton(View view) {
        MediaPlayer mp = MediaPlayer.create(QuizActivity.this, R.raw.alert);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release(); // Release the MediaPlayer after playback is complete.
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("You're Leaving this Quiz");

        alertDialogBuilder.setMessage("Quitting this Quiz will lead to not being able to record your progress. Continue?");

        alertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
            Intent intent = new Intent(QuizActivity.this, MainActivity.class);
            startActivity(intent);
        });
        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void RingCorrect() {
        MediaPlayer mp = MediaPlayer.create(QuizActivity.this, R.raw.correct);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release(); // Release the MediaPlayer after playback is complete.
            }
        });

    }

    public void RingWrong() {
        MediaPlayer mp = MediaPlayer.create(QuizActivity.this, R.raw.wrong);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release(); // Release the MediaPlayer after playback is complete.
            }
        });
    }
    */
}