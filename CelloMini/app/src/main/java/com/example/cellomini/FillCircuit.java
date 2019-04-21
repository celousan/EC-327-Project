package com.example.cellomini;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cellomini.AboutUs;
import com.example.cellomini.MainActivity;

public class FillCircuit extends Activity {

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_circuit);

        //needs to randomize the promoter table

        returnButton = findViewById(R.id.buttonReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return app to previous activity
                openActivityMain();
            }
        });

        //get intent from main activity
        Intent inp = getIntent();
        String word = inp.getStringExtra("word");

        //sets text view as variable
        TextView textView = (TextView) findViewById(R.id.outputText);
        textView.setText(word);
    }

    public void openActivityMain(){
        Intent mainActivity = new Intent(FillCircuit.this, MainActivity.class);
        startActivity(mainActivity);
    }
}
