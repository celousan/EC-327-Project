package com.example.cellomini;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterWord extends Activity implements View.OnClickListener {

    private Button ebutton1;
    private Button ebutton2;

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
        ebutton2 = (Button) findViewById(R.id.buttonReturn);

        /*The buttons now have onClickListeners set, a method/function of the button class
         * to start a new activity/intent when pressed. In this case, pressing a button
         * will go to the results page.
         * */
        ebutton1.setOnClickListener(this);
        ebutton2.setOnClickListener(this);

        // The edit text has a string value set by the editText1 ID in Main.xml
        et = findViewById(R.id.editText1);

        //Set edit text to all caps
        InputFilter[] editFilters = et.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        et.setFilters(newFilters);
    }

    @Override
    /*onClick is what is called when the buttons are pressed and they take in Views as arguments
     * as buttons are children of the view class, buttons can polymorphically be passed in. The button
     * that called the onClick is automatically fed in*/
    public void onClick(View v)
    {
        //The switch statements grab the id values of the button pressed
        switch(v.getId()) {
            case R.id.buttonReturn: {
                Intent mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                break;
            }
            case R.id.button_gen_circuit: {
                //saves name in variable
                inputString = et.getText().toString();

                //Check for number of letters (want 5)
                int l_count = inputString.length();

                //Less than 5 letters
                if (l_count < 5) {
                    AlertDialog.Builder exitAlert = new AlertDialog.Builder(EnterWord.this);
                    exitAlert.setTitle("Error");
                    exitAlert.setMessage("Your word contains less than 5 letters - please try again.");
                    exitAlert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Clear entered text and return to main page
                        }
                    });
                    exitAlert.create().show();
                    et.getText().clear();
                    break;
                }

                //Check for repeat letters
                int dup_count = 0;
                for (int i = 0; i < inputString.length(); i++){
                    char c1 = inputString.charAt(i);
                    for (int j = i + 1; j < 5; j++) {
                        if (c1 == inputString.charAt(j)) {
                            dup_count++;
                        }
                    }
                }

                //Duplicate letter in entered word
                if (dup_count > 0) {
                    AlertDialog.Builder exitAlert = new AlertDialog.Builder(EnterWord.this);
                    exitAlert.setTitle("Error");
                    exitAlert.setMessage("Your word contains a duplicate letter - please try again.");
                    exitAlert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Clear entered text and return to main page
                        }
                    });
                    exitAlert.create().show();
                    et.getText().clear();
                    break;
                }
                else {
                    //go to fillCircuit page and pass inputString variable
                    openFillCircuitActivity();
                    break;
                }
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
