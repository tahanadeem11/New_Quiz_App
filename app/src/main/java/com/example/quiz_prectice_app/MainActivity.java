package com.example.quiz_prectice_app;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView questionImageView;
    ImageView yesButton, noButton;
    TextView textViewQuestion;

    private final String[] Q = {
            "Are you Muslim?",
            "Are you Pakistani?",
            "Was Java introduced in 1200?",
            "Is Android Studio easy to learn?",
            "Are you in the ADP class?"
    };

    private final Boolean[] A = {true, true, false, true, false};

    private int score = 0;
    private int index = 0;

    private final int[] questionImages = {
            R.drawable.question1,
            R.drawable.question2,
            R.drawable.question3,
            R.drawable.question4,
            R.drawable.question5
    };

    private final Handler handler = new Handler();
    private final Runnable nextQuestionRunnable = new Runnable() {
        @Override
        public void run() {
            moveToNextQuestion();
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.imageViewQuestion);
        yesButton = findViewById(R.id.imageViewYes);
        noButton = findViewById(R.id.imageViewNo);
        textViewQuestion = findViewById(R.id.textViewDisplayy);

        displayQuestion();

        yesButton.setOnClickListener(view -> onOptionSelected(true));
        noButton.setOnClickListener(view -> onOptionSelected(false));
    }

    private void displayQuestion() {
        if (index < questionImages.length) {
            questionImageView.setImageResource(questionImages[index]);
            textViewQuestion.setText(Q[index]);
        } else {
            // End of the quiz, display the final score
            Toast.makeText(MainActivity.this, "Total score is " + score, Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToNextQuestion() {
        if (index < questionImages.length) {
            index++;
            displayQuestion();
        } else {
            // Quiz is over, display the final score
            Toast.makeText(MainActivity.this, "Total score is " + score, Toast.LENGTH_SHORT).show();
        }
    }

    private void onOptionSelected(boolean userAnswer) {
        if (index < questionImages.length) {
            if (A[index] == userAnswer) {
                score++;
            }
            handler.removeCallbacks(nextQuestionRunnable);
            handler.postDelayed(nextQuestionRunnable, 1000); // Delay for 1 second (1000 milliseconds) before moving to the next question
        } else {
            // Quiz is over, display the final score
            Toast.makeText(MainActivity.this, "Total score is " + score, Toast.LENGTH_SHORT).show();
        }
    }
}
