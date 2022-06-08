package com.e.myfoodpreferenceapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("음식 선호도 투표 결과");

        Intent intent = getIntent();
        int[] vote = intent.getIntArrayExtra("vote");
        String[] str = intent.getStringArrayExtra("pictureName");

        TextView tv[] = new TextView[str.length];
        RatingBar rbar[] = new RatingBar[str.length];

        int tvid[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6,
                R.id.tv7, R.id.tv8, R.id.tv9};
        int rbarid[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6,
                R.id.rbar7, R.id.rbar8, R.id.rbar9};

        for (int i = 0; i < vote.length; i++) {
            tv[i] = findViewById(tvid[i]);
            rbar[i] = findViewById(rbarid[i]);
        }

        for (int i = 0; i < vote.length; i++) {
            tv[i].setText(str[i]);
            rbar[i].setRating((float) vote[i]);
        }

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}