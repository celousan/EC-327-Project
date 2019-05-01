package com.example.cellomini;

//This is the class that will first be run when the app is first opened


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

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

        //on click, button redirects user to tutorial page
        mbutton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tutorialActivity= new Intent(MainActivity.this, Tutorial.class);
                startActivity(tutorialActivity);
            }
        });
        //on click, second button displays information about the app
        mbutton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutUsActivity = new Intent(MainActivity.this, AboutUs.class);
                startActivity(aboutUsActivity);
            }
        });
    }
}
