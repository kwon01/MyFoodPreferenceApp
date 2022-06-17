package com.e.myfoodpreferenceapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    myDBHelper myHelper = new myDBHelper(this, "groupDB", null, 1);
    TextView tvName, tvReview;
    EditText edtName, edtReview, edtNameResult, edtReviewResult;
    Button btnReturn, btnNext, btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

      /**
     * 
     * 음식 선호도 평점에 대한 java 코드 입니다.
     */
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        setTitle("음식선호도 투표에 대한 평점");

        tvName = findViewById(R.id.tvName);
        tvReview = findViewById(R.id.tvReview);
        edtName = findViewById(R.id.edtName);
        edtReview = findViewById(R.id.edtReview);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtReviewResult = findViewById(R.id.edtReviewResult);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
                startActivity(intent);
            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                startActivity(intent);
            }
        });

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                        + edtName.getText().toString() + "' , "
                        + edtReview.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "음식이름" + "\r\n" + "--------" + "\r\n";
                String strReviews = "평점" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strReviews += cursor.getString(1) + "" + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtReviewResult.setText(strReviews);

                cursor.close();
                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context,"groupDB",null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL;");
            onCreate(sqLiteDatabase);
        }
    }
}
