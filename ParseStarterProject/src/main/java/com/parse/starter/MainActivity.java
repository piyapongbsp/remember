/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends ActionBarActivity {


    Button btn5;
    Button btn10;
//    TextView headingName;

    List<SetObject> listWord;



    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn5 = (Button) findViewById(R.id.btn5);
        btn10 = (Button) findViewById(R.id.btn10);

//        ParseObject testlang1 = new ParseObject("Languages");
//        testlang1.put("Name", "Piyapong");
//        testlang1.put("English", "One");
//        testlang1.put("Thai", "หนึ่ง");
//        testlang1.put("Japanese", "いち");
//        testlang1.saveInBackground();


//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Languages"); // Object      is classname
//        query.setLimit(1000);
//        //query.whereEqualTo("English", "Test");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> list, ParseException e) {
//                listWord = new ArrayList<SetObject>();
//                if (e == null) {
//                    for (int i = 0; i < list.size(); i++) {
//                        String name = list.get(i).getString("Name"); //get data from column Name
//                        String jap = list.get(i).getString("Japanese");
//                        String eng = list.get(i).getString("English");
//                        String th = list.get(i).getString("Thai");
//
////                        if (i == 0) txtWord.setText(eng);
//
//                        Log.e("name", name);
//                        Log.e("jap", jap);
//                        Log.e("eng", eng);
//                        Log.e("th", th);
//                        SetObject dataObject = new SetObject(name, eng, jap, th);  // create box for 1 roll  --> create dataObject from type SetObject
//
//                        listWord.add(dataObject);
//                    }
//                    Log.e("size", list.size() + "");
////                    btnSearch.setVisibility(View.VISIBLE);
//                } else {
//                    Log.d("list", "Error: " + e.getMessage());
//                }
//
//
//
//
//            }
//        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Intent intent = new Intent(MainActivity.this, test.class);
                intent.putExtra("btnc", 5);


                startActivity(intent);

            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, test.class);
                intent.putExtra("btnc", 10);
                startActivity(intent);
            }
        });



        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
