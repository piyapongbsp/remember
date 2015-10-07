package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Created by TIW on 9/16/2015.
 */
public class test extends ActionBarActivity {

    Button btnNext;
    Button btnSearch;
    Button btnToResult;
    EditText editSearchEng;
    TextView txtWord;
    TextView txtResult;
    TextView txtMatch;


    List<SetObject> listWord;
    ArrayList<Integer> listR = new ArrayList<Integer>();


    int index = 0;
    int cntR = 0;
    int score = 0;

    int ranMax;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randword_activity);

        ranMax = getIntent().getIntExtra("btnc", 0);

//        ArrayList<String> list = getIntent().getStringArrayListExtra("key");
        Toast.makeText(getApplicationContext(), ""+ranMax, Toast.LENGTH_LONG).show();
        // Get the message from the intent

        txtWord =  (TextView) findViewById(R.id.txtWord);
        btnNext =  (Button) findViewById(R.id.btnNext);
        editSearchEng = (EditText) findViewById(R.id.editSearchEng);
        btnSearch =  (Button) findViewById(R.id.btnSearch);
        txtResult =  (TextView) findViewById(R.id.txtResult);
        txtMatch =   (TextView) findViewById(R.id.txtMatch);
        btnToResult = (Button) findViewById(R.id.btnToResult);





        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSearch.setVisibility(View.INVISIBLE);
                txtMatch.setVisibility(View.GONE);

                txtResult.setVisibility(View.GONE);
                Handler handler = new Handler();
                for (int i = 0; i < listWord.size(); i++) {
                    SetObject data = listWord.get(listR.get(index));
                    if (data.getJapan().equalsIgnoreCase(editSearchEng.getText().toString())) {
                        txtResult.setText(data.getJapan());
                        txtMatch.setText("Correct!!");
                        score = score +1;
                        txtMatch.setVisibility(View.VISIBLE);
                        txtResult.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.INVISIBLE);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btnNextClick();
                            }
                        },3000);
                        break;
                    } else {
                        txtMatch.setText("Wrong!!");
                        txtMatch.setVisibility(View.VISIBLE);
                        txtResult.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btnNextClick();
                            }
                        }, 3000);
                    }

                }


            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                btnNextClick();
            }
        });


        btnToResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(test.this, showResult.class);
                intent.putExtra("pointS", score);

                startActivity(intent);

            }
        });



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Languages"); // Object      is classname
        query.setLimit(1000);
        //query.whereEqualTo("English", "Test");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                listWord = new ArrayList<SetObject>();

                for (cntR = 0; cntR < list.size() ; cntR++) {
                    listR.add(cntR);
                }
                Collections.shuffle(listR);
                for (int cntR = 0; cntR < 5; cntR++) {
                    System.out.println("Random " + listR.get(cntR));
                }


                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getString("Name"); //get data from column Name
                        String jap = list.get(i).getString("Japanese");
                        String eng = list.get(i).getString("English");
                        String th = list.get(i).getString("Thai");


//                        Log.e("name", name);
//                        Log.e("jap", jap);
//                        Log.e("eng", eng);
//                        Log.e("th", th);
                        SetObject dataObject = new SetObject(name, eng, jap, th);  // create box for 1 roll  --> create dataObject from type SetObject

                        listWord.add(dataObject);
                    }
                    txtWord.setText(listWord.get(listR.get(0)).getEnglish());
                    Log.e("size", list.size() + "");
                    btnSearch.setVisibility(View.VISIBLE);
                } else {
                    Log.d("list", "Error: " + e.getMessage());
                }


            }
        });




    }

    void btnNextClick() {
        txtResult.setVisibility(View.GONE);
        btnSearch.setVisibility(View.VISIBLE);
        editSearchEng.setText("");
        btnToResult.setVisibility(View.INVISIBLE);
        if (index < ranMax - 1) {
            txtWord.setText(listWord.get(listR.get(++index)).getEnglish());  //
            txtMatch.setVisibility(View.GONE);

        }
        if (index == ranMax - 1)
            btnNext.setVisibility(View.INVISIBLE);
        btnToResult.setVisibility(View.VISIBLE);
        txtMatch.setVisibility(View.GONE);
        txtResult.setVisibility(View.GONE);
    }
}