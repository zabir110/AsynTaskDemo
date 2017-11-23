package com.example.user.asyntaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText et1,et2;
    String surl="";
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.multiplication);
        et1 = (EditText) findViewById(R.id.firstno);
        et2 = (EditText) findViewById(R.id.secondno);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(et1.getText().toString());
                int j = Integer.parseInt(et2.getText().toString());
               // surl="http://www.telusko.com/addition.htm?t1=3&t2=6";
                surl="http://www.google.com";
                new MyTask().execute();

            }
        });

    }

    public class MyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
           // super.onPostExecute(s);
            Toast.makeText(MainActivity.this," "+result,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //URL set korlam
                URL url = new URL(surl);
                //Now do work for connection
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                //After connection we need to read data bufferReader
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String values=bufferedReader.readLine();
                //Save data in string
                result=values;

               }
            catch (Exception e){
                System.out.println(e);
            }
            return null;
        }
    }

}
