package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.sqlite.MESSAGE";

    EditText entername, enterheight, enterweight;

    SQLiteDatabase db;
    SQLite dbHelper;
    String DATABASE_TABLE = "table1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entername = (EditText) findViewById(R.id.editText1);
        enterheight = (EditText) findViewById(R.id.editText2);
        enterweight = (EditText) findViewById(R.id.editText3);
    }

    // 新增
    public void bt1(View v) {
        String a = entername.getText().toString();
        String b = enterheight.getText().toString();
        String c = enterweight.getText().toString();
        double height = Double.parseDouble(enterheight.getText().toString()) / 100;
        double width = Double.parseDouble(enterweight.getText().toString());
        double BMI = width / (height * height);

        // 開啟資料庫
        dbHelper = new SQLite(getBaseContext());
        db = dbHelper.getWritableDatabase();

        // 寫入資料庫
        ContentValues cv = new ContentValues();
        cv.put("Name", a);
        cv.put("Height", b);
        cv.put("Weight", c);
        cv.put("BMI", BMI);
        db.insert(DATABASE_TABLE, null, cv);
        //顯示紀錄

        // 關閉資料庫
        dbHelper.close();
        db.close();
        Intent intent = new Intent(this, showbmi.class);
        String sendbmi = ("姓名:" + a + "\n" + "身高:" + b + "\n" + "體重:" + c
                + "\n" + "BMI:" + BMI);
        intent.putExtra(EXTRA_MESSAGE, sendbmi);
        startActivity(intent);
    }


}
