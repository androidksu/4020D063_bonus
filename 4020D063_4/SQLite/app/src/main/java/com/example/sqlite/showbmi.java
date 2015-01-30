package com.example.sqlite;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class showbmi extends Activity {

    TextView show1, show2;

    SQLiteDatabase db;
    SQLite dbHelper;
    String DATABASE_TABLE = "table1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showbmi);
        show1 = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        show1.setText(message);

        show2 = (TextView) findViewById(R.id.textView5);
        String[] item = {"Name", "Height", "Weight", "BMI"};
        StringBuffer sb = new StringBuffer();
        sb.append("姓名");
        sb.append("\t");
        sb.append("身高");
        sb.append("\t\t\t");
        sb.append("體重");
        sb.append("\t\t\t");
        sb.append("BMI");
        sb.append("\n");
        // 開啟資料庫
        dbHelper = new SQLite(getBaseContext());
        db = dbHelper.getWritableDatabase();
        // 查詢
        Cursor c = db.query(DATABASE_TABLE, item, null, null, null, null, null);
        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {
            sb.append(c.getString(0));
            sb.append("\t");
            sb.append(c.getString(1));
            sb.append("\t\t\t");
            sb.append(c.getString(2));
            sb.append("\t\t\t");
            sb.append(c.getString(3));
            sb.append("\n\n");
            c.moveToNext();
        }
        // 關閉資料庫
        dbHelper.close();
        db.close();
        show2.setText(sb);
    }
}
