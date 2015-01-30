package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText entername, enterheight, enterweight;
	TextView tv;

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
		tv = (TextView) findViewById(R.id.textView4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		tv.setText("新增一筆資料");
	}

	// 查詢
	public void bt2(View v) {
		String[] item = { "Name", "Height", "Weight","BMI" };
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
			sb.append("\n");
			c.moveToNext();
		}
		// 關閉資料庫
		dbHelper.close();
		db.close();
		tv.setText(sb);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
