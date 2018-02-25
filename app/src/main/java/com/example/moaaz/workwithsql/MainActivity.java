package com.example.moaaz.workwithsql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    TextView tv;
    EditText et1, et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView1);
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);

        // Create DataBase if not Exists
        db = openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        // create table if not exists
        db.execSQL("create table if not exists mytable(name varchar, sur_name varchar)");



    }

    // method will be implement after click button insert
    public void insert(View view) {
        // get user input in EditText field and keep it in variable
        String name = et1.getText().toString();
        String sur_name = et2.getText().toString();

        // Empty fields after that
        et1.setText("");
        et2.setText("");

        //insert values to table
        db.execSQL("insert into mytable values ('"+name+"','"+sur_name+"')");

        // print Toast if Inserted succesfully
        Toast.makeText(this,"Insertion Success",Toast.LENGTH_LONG).show();

    }

    public void display(View v)
    {
        //use cursor to keep all data
        //cursor can keep data of any data type
        Cursor c=db.rawQuery("select * from mytable", null);
        tv.setText("");
        //move cursor to first position
        c.moveToFirst();
        //fetch all data one by one
        do
        {
            //we can use c.getString(0) here
            //or we can get data using column index
            String name=c.getString(c.getColumnIndex("name"));
            String surname=c.getString(c.getColumnIndex("sur_name"));
            //display on text view
            tv.append("Name:"+name+" and SurName:"+surname+"\n");
            //move next position until end of the data
        }while(c.moveToNext());
    }
}
