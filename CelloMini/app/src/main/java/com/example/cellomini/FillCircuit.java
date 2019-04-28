package com.example.cellomini;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


public class FillCircuit extends Activity {

    Button returnButton, evaluateButton;

    String promoter2, promoter3, promoter4, promoter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_circuit);

        //needs to randomize the promoter table


        //drag listeners

        findViewById(R.id.input1).setOnTouchListener(new TouchListener());
        findViewById(R.id.input2).setOnTouchListener(new TouchListener());
        findViewById(R.id.input3).setOnTouchListener(new TouchListener());
        findViewById(R.id.input4).setOnTouchListener(new TouchListener());
        findViewById(R.id.input5).setOnTouchListener(new TouchListener());
        findViewById(R.id.input6).setOnTouchListener(new TouchListener());
        findViewById(R.id.input7).setOnTouchListener(new TouchListener());
        findViewById(R.id.input8).setOnTouchListener(new TouchListener());

        //drop listeners

        findViewById(R.id.target2).setOnDragListener(new MyDragListener());
        findViewById(R.id.target3).setOnDragListener(new MyDragListener());
        findViewById(R.id.target4).setOnDragListener(new MyDragListener());
        findViewById(R.id.target5).setOnDragListener(new MyDragListener());


        //button to return to main activity, with alert window

        returnButton = findViewById(R.id.buttonReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alert dialogue window, exit returns to mainActivity, return does nothing.
                AlertDialog.Builder exitAlert = new AlertDialog.Builder(FillCircuit.this);
                exitAlert.setTitle("Do you wish to exit?");
                exitAlert.setMessage("Your work will not be saved.");
                exitAlert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //return app to main activity
                        openActivityMain();
                    }
                });
                exitAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no definition
                    }
                });
                exitAlert.create().show();
            }
        });


        //button to evaluate answer

        evaluateButton = findViewById(R.id.evalButton);
        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saves promoters in variables
                promoter2 = findViewById(R.id.target2).toString();
                promoter3 = findViewById(R.id.target3).toString();
                promoter4 = findViewById(R.id.target4).toString();
                promoter5 = findViewById(R.id.target5).toString();
                //intents to interact these values with the rest of the code
            }
        });


        //get intent from main activity
        Intent inp = getIntent();
        String word = inp.getStringExtra("word");

        //sets text view as variable
        TextView textView = findViewById(R.id.outputText);
        textView.setText(word);

    }

    //function defined to send the app back to the mainActivity
    public void openActivityMain(){
        Intent mainActivity = new Intent(FillCircuit.this, MainActivity.class);
        startActivity(mainActivity);
    }

    //Drag and Drop Code - Allows for promoters to be inserted in circuit elements

        //touch listener

    private class TouchListener implements View.OnTouchListener {

        //view keyword refers to the view that initiated movement

        public boolean onTouch(View view, MotionEvent motionEvent){
            //if user presses on the screen, enter if (if ACTION_DOWN is true for that event)
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData draggedData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(draggedData, shadowBuilder, view, 0);
                return true;
            } else {
                //if there is no touch, function returns false
                return false;
            }
        }
    }

        //drag listener

    private class MyDragListener implements View.OnDragListener {

        View draggedView;
        TextView dropped;

        //call this method once drag event is dispatched to the listener
        public boolean onDrag(View view, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //checks if view can accept dragged data.
                    if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                        draggedView = (View) event.getLocalState();
                        dropped = (TextView) draggedView;
                        return true;
                    }
                    else{
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    return true;
                case DragEvent.ACTION_DROP:
                    //defines the receiving textView
                    TextView dropTarget = (TextView) view;
                    //sets the new text data
                    dropTarget.setText(dropped.getText().toString());
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
                default:
                    Log.e("DragDrop Result","Unknown action type received by DragListener.");
                    break;
            }
            return false;
        }
    }

}