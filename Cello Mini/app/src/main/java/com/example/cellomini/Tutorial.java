package com.example.cellomini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tutorial extends Activity implements View.OnClickListener {

    //Creating button objects, which extend (inherit) the View class
    private Button tbutton1;
    private Button tbutton2;

    protected void onCreate(Bundle savedInstanceState)
    {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.tutorial);

        //The buttons have parameters corresponding to the IDs in Main.xml
        tbutton1 = (Button) findViewById(R.id.button_continue);
        tbutton2 = (Button) findViewById(R.id.buttonReturn);

        /*The buttons now have onClickListeners set, a method/function of the button class
         * to start a new activity/intent when pressed. In this case, pressing a button
         * will go to the results page.
         * */
        tbutton1.setOnClickListener(this);
        tbutton2.setOnClickListener(this);
    }

    @Override
    /*onClick is what is called when the buttons are pressed and they take in Views as arguments
     * as buttons are children of the view class, buttons can polymorphically be passed in. The button
     * that called the onClick is automatically fed in*/
    public void onClick(View v)
    {
        //The switch statements grab the id values of the button pressed
        switch(v.getId()) {

            case R.id.button_continue: {
                //go to information page
                Intent enterWordActivity = new Intent(Tutorial.this, EnterWord.class);
                startActivity(enterWordActivity);
                break;
            }
            case R.id.buttonReturn: {
                Intent mainActivity = new Intent(Tutorial.this, MainActivity.class);
                startActivity(mainActivity);
                break;
            }
            default: {
                break;
            }
        }
    }
}
