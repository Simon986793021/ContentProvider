package com.wind.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.wind.contentprovider.R.id.bt_insertdata;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button querybutton;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(bt_insertdata);
        querybutton= (Button) findViewById(R.id.bt_query);
        textView= (TextView) findViewById(R.id.tv_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        querybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryData();
            }
        });
    }

    private void queryData() {
        String array[]={"name","age","isMan"};

    Cursor cursor=getContentResolver().query(MyContentProvider.uri,array,null,null,null);
    int count=cursor.getCount();
        Log.i(">>>",">>>");
        Log.i("Simoncount",count+"");
        String text="";
        int nameindex=cursor.getColumnIndex("name");
        int ageindex=cursor.getColumnIndex("age");
        int isManindex=cursor.getColumnIndex("isMan");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(nameindex);
            int age = cursor.getInt(ageindex);
            int isMan=cursor.getInt(isManindex);
            Log.i(">>>>>>>>>",name+age);
            text+="name="+name+",    " +"age="+age+",    isMan="+isMan;//小技巧，在这里进行字符串拼接
            cursor.moveToNext();
        }
        textView.setText("总共有"+count+"条数据"+"   分别为："+text);
    }

    private void insertData() {
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","simon");
        contentValues.put("age",20);
        contentValues.put("isMan",true);
        Uri uri=getContentResolver().insert(MyContentProvider.uri,contentValues);
        Log.i("Simon",uri.toString());
        textView.setText("成功插入一条数据，Uri为"+uri.toString());
//        Cursor cursor=getContentResolver().query(MyContentProvider.uri,null,null,null,null);
//        try {
//            Toast.makeText(MainActivity.this,cursor.getString(1),Toast.LENGTH_SHORT).show();
//        }
//        finally {
//            cursor.close();
//        }
    }
}
