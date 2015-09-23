package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TIW on 9/23/2015.
 */


public class showResult extends ActionBarActivity {

    Button btnOK;
    TextView txtPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        final int point = getIntent().getIntExtra("pointS", 0);

//        ArrayList<String> list = getIntent().getStringArrayListExtra("key");
        Toast.makeText(getApplicationContext(), "" + point, Toast.LENGTH_LONG).show();
        // Get the message from the intent

        btnOK = (Button) findViewById(R.id.btnOK);
        txtPoint = (TextView) findViewById(R.id.txtPoint);
        txtPoint.setText("Your score" + point);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showResult.this, MainActivity.class);

                startActivity(intent);

            }
        });




    }
}


