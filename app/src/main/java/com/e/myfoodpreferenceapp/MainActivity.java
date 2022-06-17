package com.e.myfoodpreferenceapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button btnStart;

      /**
     * 
     * jvad의 코드는 투표 앱의 메인 홈 입니다.
     */
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("음식 선호도 투표 앱");

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
                startActivity(intent);

            }
        });
    }
}
