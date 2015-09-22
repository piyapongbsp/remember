package com.parse.starter;

import android.os.Bundle;
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

/**
 * Created by TIW on 9/16/2015.
 */
public class test extends ActionBarActivity {

    Button btnNext;
    Button btnPrev;
    TextView txtWord;
    EditText editSearchEng;
    Button btnSearch;
    TextView txtResult;
    TextView txtMatch;
    List<SetObject> listWord;



    int index = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randword_activity);

        int ranMax = getIntent().getIntExtra("btnc", 0);

//        ArrayList<String> list = getIntent().getStringArrayListExtra("key");
        Toast.makeText(getApplicationContext(), ""+ranMax, Toast.LENGTH_LONG).show();
        // Get the message from the intent

        txtWord =  (TextView) findViewById(R.id.txtWord);
        btnNext =  (Button) findViewById(R.id.btnNext);
        btnPrev =  (Button) findViewById(R.id.btnPrev);
        editSearchEng = (EditText) findViewById(R.id.editSearchEng);
        btnSearch =  (Button) findViewById(R.id.btnSearch);
        txtResult =  (TextView) findViewById(R.id.txtResult);
        txtMatch =   (TextView) findViewById(R.id.txtMatch);





        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMatch.setVisibility(View.GONE);
                txtResult.setVisibility(View.GONE);
                for (int i = 0; i < listWord.size(); i++) {
                    SetObject data = listWord.get(index);
                    if (data.getJapan().equalsIgnoreCase(editSearchEng.getText().toString())) {
                        txtResult.setText(data.getJapan());
                        txtMatch.setText("Correct!!");
                        txtMatch.setVisibility(View.VISIBLE);
                        txtResult.setVisibility(View.VISIBLE);
                        break;
                    } else {
                        txtMatch.setText("Wrong!!");
                        txtMatch.setVisibility(View.VISIBLE);
                    }

                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < listWord.size() - 1) {
                    btnPrev.setVisibility(View.VISIBLE);
                    txtWord.setText(listWord.get(++index).getEnglish());
                    txtMatch.setVisibility(View.GONE);
                    txtResult.setVisibility(View.GONE);
                }
                if (index == listWord.size() - 1)
                    btnNext.setVisibility(View.INVISIBLE);
                txtMatch.setVisibility(View.GONE);
                txtResult.setVisibility(View.GONE);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    btnNext.setVisibility(View.VISIBLE);
                    txtWord.setText(listWord.get(--index).getEnglish());
                    txtMatch.setVisibility(View.GONE);
                    txtResult.setVisibility(View.GONE);
                }
                if (index == 0)
                    btnPrev.setVisibility(View.INVISIBLE);
                txtMatch.setVisibility(View.GONE);
                txtResult.setVisibility(View.GONE);
            }
        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Languages"); // Object      is classname
        query.setLimit(1000);
        //query.whereEqualTo("English", "Test");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                listWord = new ArrayList<SetObject>();
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getString("Name"); //get data from column Name
                        String jap = list.get(i).getString("Japanese");
                        String eng = list.get(i).getString("English");
                        String th = list.get(i).getString("Thai");

                        if (i == 0) txtWord.setText(eng);

                        Log.e("name", name);
                        Log.e("jap", jap);
                        Log.e("eng", eng);
                        Log.e("th", th);
                        SetObject dataObject = new SetObject(name, eng, jap, th);  // create box for 1 roll  --> create dataObject from type SetObject

                        listWord.add(dataObject);
                    }
                    Log.e("size", list.size() + "");
                    btnSearch.setVisibility(View.VISIBLE);
                } else {
                    Log.d("list", "Error: " + e.getMessage());
                }


            }
        });




    }
}
