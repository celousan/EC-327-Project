package com.example.cellomini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends Activity implements View.OnClickListener{

    private Button abutton1;

    @Override
	/*An onCreate method/function is what is called when a screen is first showed.
	  In this case, this is the main menu screen.
	*/
    protected void onCreate(Bundle savedInstanceState)
    {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.about_us);

        //The buttons have parameters corresponding to the IDs in Main.xml
        abutton1 = (Button) findViewById(R.id.button_back);

        /*The buttons now have onClickListeners set, a method/function of the button class
         * to start a new activity/intent when pressed. In this case, pressing a button
         * will go to the results page.
         * */
        abutton1.setOnClickListener(this);
    }

    @Override
    /*onClick is what is called when the buttons are pressed and they take in Views as arguments
     * as buttons are children of the view class, buttons can polymorphically be passed in. The button
     * that called the onClick is automatically fed in*/
    public void onClick(View v)
    {
        //The switch statements grab the id values of the button pressed and calculates the tip accordingly
        switch(v.getId()) {

            case R.id.button_back: {
                //go to information page
                Intent mainActivity = new Intent(AboutUs.this, MainActivity.class);
                startActivity(mainActivity);
            }
            default: {
                break;
            }
        }
    }

}
