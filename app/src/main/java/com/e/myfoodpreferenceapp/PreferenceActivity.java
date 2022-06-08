package com.e.myfoodpreferenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceActivity extends AppCompatActivity {
    Button btnReturn, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        setTitle("음식 선호도 투표");

        btnReturn.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnNext.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);

            }
        });

        final int voteCount[] = new int[9];
        for (int i = 0; i < 9; i++)
            voteCount[i] = 0;

        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6,
                R.id.iv7, R.id.iv8, R.id.iv9};

        final String imgName[] = {"치킨", "피자", "햄버거", "스테이크", "초밥", "파스타", "짜장면",
                "떡볶이", "라면"};

        for (int i = 0; i < imageId.length; i++)

        {
            final int index;
            index = i;
            image[index] = findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index] + ": 총 " +
                            voteCount[index] + " 표", Toast.LENGTH_SHORT).show();
                }
            });

            Button btnFinsh = findViewById(R.id.btnResult);
            btnFinsh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("VoteCount", voteCount);
                    intent.putExtra("ImageName", imgName);
                    startActivity(intent);
                }
            });
        }
    }
}