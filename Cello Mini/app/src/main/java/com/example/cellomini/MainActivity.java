
/*****************************************************************************************
 * Adapted and tortured by Julian Trinh and Connor Richmond for use in ENG EC 327
 * Source code from https://github.com/academicode/app-simple-tip-calculator/tree/session-7
 * Sweet tutorial at https://www.youtube.com/watch?v=Z3jzIYkxB1s (where the source is from)
 * Boston University: College of Engineering
 *****************************************************************************************/
package com.example.cellomini;

//This is the class that will first be run when the app is first opened

//import is #include from C++
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//Extends = inherits, implements means it interfaces with another class
public class MainActivity extends Activity {

    //Creating button objects, which extend (inherit) the View class
    private Button mbutton1;
    private Button mbutton2;

    @Override
	/*An onCreate method/function is what is called when a screen is first showed.
	  In this case, this is the main menu screen.
	*/
    protected void onCreate(Bundle savedInstanceState)
    {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.activity_main);

        //The buttons have parameters corresponding to the IDs in Main.xml
        mbutton1 = (Button) findViewById(R.id.button_get_started);
        mbutton2 = (Button) findViewById(R.id.button_about_us);

        //Spinning logo
        ImageView spin_logo = (ImageView) this.findViewById(R.id.logo);

        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(8000);
        rotate.setRepeatCount(Animation.INFINITE);
        spin_logo.setAnimation(rotate);

        /*The buttons now have onClickListeners set, a method/function of the button class
         * to start a new activity/intent when pressed. In this case, pressing a button
         * will go to the results page.
         * */
        mbutton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tutorialActivity= new Intent(MainActivity.this, Tutorial.class);
                startActivity(tutorialActivity);
            }
        });
        mbutton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutUsActivity = new Intent(MainActivity.this, AboutUs.class);
                startActivity(aboutUsActivity);
            }
        });
    }
}
