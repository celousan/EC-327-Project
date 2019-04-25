package com.example.cellomini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterWord extends Activity implements View.OnClickListener {

    private Button ebutton1;

    private EditText et;

    String inputString;

    @Override
	/*An onCreate method/function is what is called when a screen is first showed.
	  In this case, this is the main menu screen.
	*/
    protected void onCreate(Bundle savedInstanceState)
    {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.enter_word);

        //The buttons have parameters corresponding to the IDs in Main.xml
        ebutton1 = (Button) findViewById(R.id.button_gen_circuit);

        /*The buttons now have onClickListeners set, a method/function of the button class
         * to start a new activity/intent when pressed. In this case, pressing a button
         * will go to the results page.
         * */
        ebutton1.setOnClickListener(this);

        // The edit text has a string value set by the editText1 ID in Main.xml
        et = findViewById(R.id.editText1);
    }

    @Override
    /*onClick is what is called when the buttons are pressed and they take in Views as arguments
     * as buttons are children of the view class, buttons can polymorphically be passed in. The button
     * that called the onClick is automatically fed in*/
    public void onClick(View v)
    {
        //The switch statements grab the id values of the button pressed
        switch(v.getId()) {

            case R.id.button_gen_circuit: {
                //saves name in variable
                inputString = et.getText().toString();
                //go to fillCircuit page and pass inputString variable
               openFillCircuitActivity();
            }
            default: {
                break;
            }
        }
    }

    public void openFillCircuitActivity(){
        Intent fillCircuitActivity = new Intent(EnterWord.this, FillCircuit.class);
        fillCircuitActivity.putExtra("word", inputString);
        startActivity(fillCircuitActivity);
    }
}
