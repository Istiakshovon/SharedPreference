package com.istiak.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView txtHighScore,txtScore;
    Button btnPlay,btnReset;

    //Declare shared Preference
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    //declare shared prefernce file name
    public final String PREFS_GAME="com.istiak.sharedpreference.Game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHighScore=findViewById(R.id.txt_high_score);
        txtScore=findViewById(R.id.txt_score);

        btnPlay=findViewById(R.id.btn_play);
        btnReset=findViewById(R.id.btn_reset);


        //Creating SharedPreferences name game and mode is private
        pref=getSharedPreferences(PREFS_GAME,MODE_PRIVATE);
        editor=pref.edit();

        //set initial score
        final int highScore=pref.getInt("highScore",0);
        txtHighScore.setText("High Score :"+highScore);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int score=random.nextInt(1000);
                txtScore.setText(String.valueOf(score));

                int getSaveScore=pref.getInt("highScore",0);

                //Checking score is greater than high score
                if (score>getSaveScore)
                {
                    txtHighScore.setText("High Score: "+score);
                    editor.putInt("highScore",score);
                    editor.apply();
                }



            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("highScore",0);
                editor.apply();

                txtHighScore.setText("High Score: 0");
                txtScore.setText(String.valueOf(0));


                //In setText method,we can not write int value directly.
                //setText method accept only String values
            }
        });

    }
}
