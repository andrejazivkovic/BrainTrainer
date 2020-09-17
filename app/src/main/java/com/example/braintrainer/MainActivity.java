package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView text2;
    TextView text3;
    TextView text1;
    TextView text4;
    int correctAnswers=0;
    int totalAnswers=0;

    Button go;
    ArrayList<Integer>answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;


    public void startGame(View view){
        go.setVisibility(View.INVISIBLE);
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        text4.setVisibility(View.VISIBLE);

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
    }


    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view){


        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
                correctAnswers++;
                text2.setText("CORECT!");

        }
        else {
            text2.setText("WRONG!");
        }
        totalAnswers++;
        text3.setText(Integer.toString(correctAnswers)+"/"+Integer.toString(totalAnswers));
        generateQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(40);
        int b = rand.nextInt(40);
        answers.clear();

        text1.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a + b);
            }
            else {
                incorrectAnswer=rand.nextInt(80);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(80);
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));


    }
    public void timer(){
        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                text4.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                button5.setVisibility(View.VISIBLE);
                text4.setText("0s");
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                if (correctAnswers < 10) {
                    text2.setText("You can do better: "+ Integer.toString(correctAnswers)+"/"+Integer.toString(totalAnswers));
                }
                else {
                    text2.setText("Impressive: "+ Integer.toString(correctAnswers)+"/"+Integer.toString(totalAnswers));
                }
            }
        }.start();

    }
    @SuppressLint("SetTextI18n")
    public void PlayAgain(View view){
        generateQuestion();
        timer();
        text4.setText(30+"s");
        text3.setText(0+"/"+0);
        text2.setText("");
        button5.setVisibility(View.INVISIBLE);
        correctAnswers=0;
        totalAnswers=0;
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.StartButton);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.PlayAgain);
        text2 =(TextView) findViewById(R.id.textView5);
        text3=(TextView) findViewById(R.id.textView3);
        text1 = (TextView) findViewById(R.id.textView2);
        text4=(TextView) findViewById(R.id.textView);
        PlayAgain(findViewById(R.id.PlayAgain));
    }
}
