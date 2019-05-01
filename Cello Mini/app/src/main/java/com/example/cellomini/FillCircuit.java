package com.example.cellomini;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class FillCircuit extends Activity {

    Button returnButton, evaluateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_circuit);

        //drag listeners - done
        findViewById(R.id.input1).setOnTouchListener(new TouchListener());
        findViewById(R.id.input2).setOnTouchListener(new TouchListener());
        findViewById(R.id.input3).setOnTouchListener(new TouchListener());
        findViewById(R.id.input4).setOnTouchListener(new TouchListener());
        findViewById(R.id.input5).setOnTouchListener(new TouchListener());
        findViewById(R.id.input6).setOnTouchListener(new TouchListener());
        findViewById(R.id.input7).setOnTouchListener(new TouchListener());
        findViewById(R.id.input8).setOnTouchListener(new TouchListener());

        //drop listeners - done

        findViewById(R.id.target2).setOnDragListener(new MyDragListener());
        findViewById(R.id.target3).setOnDragListener(new MyDragListener());
        findViewById(R.id.target4).setOnDragListener(new MyDragListener());
        findViewById(R.id.target5).setOnDragListener(new MyDragListener());

        //button to return to main activity, with alert window - done

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


        //get intent from main activity - done

        Intent inp = getIntent();
        String word = inp.getStringExtra("word");

        //pass input word to circuit class - done

        final Circuit wordInput = new Circuit(8, word);

        //button to evaluate answer - mostly done, still need to display answer

        evaluateButton = findViewById(R.id.evalButton);
        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(wordInput.checkSol(toPromoterArray())){
                    //alert message with congratulatory message, exit button and return button.
                    //exit returns to mainActivity, return does nothing.

                    AlertDialog.Builder exitAlert = new AlertDialog.Builder(FillCircuit.this);
                    exitAlert.setTitle("Congratulations!");
                    exitAlert.setMessage("You have solved the circuit!");
                    exitAlert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //return app to main activity
                            openActivityMain();
                        }
                    });
                    exitAlert.setNegativeButton("Return", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //no definition
                        }
                    });
                    exitAlert.create().show();
                }
                else{
                    //alert window with fail message, keep trying or display answer buttons
                    //keep trying does nothing, display answer calls answer function

                    AlertDialog.Builder exitAlert = new AlertDialog.Builder(FillCircuit.this);
                    exitAlert.setTitle("Oh no!");
                    exitAlert.setMessage("It looks like your circuit is not correct!");
                    exitAlert.setPositiveButton("Continue Game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //no definition
                        }
                    });
                    exitAlert.setNegativeButton("Display Answer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //get answer to circuit !!!!!
                            Promoter[] solution = wordInput.getSol();
                            //display answer to circuit
                        }
                    });
                    exitAlert.create().show();
                }

            }
        }); //on click listener end bracket


        //randomize the promoter table - done - this is probably what's messing with the promoter

        String temporaryText;
        int[] inputViewIds = new int[]{R.id.input1, R.id.input2, R.id.input3, R.id.input4, R.id.input5, R.id.input6, R.id.input7, R.id.input8};
        Promoter[] promo = wordInput.getPromoters();
        for(int i = 0; i < promo.length; i++)
        {
            TextView currentInput = findViewById(inputViewIds[i]);
            if(promo[i].getLogic()) {
                temporaryText = promo[i].getLet1() + " or " + promo[i].getLet2();
                currentInput.setText(temporaryText);
            }
            else {
                currentInput.setText(promo[i].getLet1());
            }
        }

        //sets text view as variable - done
        int[] outputTextID = {R.id.outputText1, R.id.outputText2, R.id.outputText3, R.id.outputText4, R.id.outputText5};
        for (int i = 0; i < 5; i++) {
            TextView textView = findViewById(outputTextID[i]);
            String letter = String.valueOf(word.charAt(i));
            textView.setText(letter);
        }
    }


    //method defined to return a promoter array with user answers - done
    public Promoter[] toPromoterArray(){
        //saves promoters in variables
        int[] targetViewIds = new int[]{R.id.target2, R.id.target3, R.id.target4, R.id.target5};
        Promoter[] ansInputs = new Promoter[4];
        //for loop assigns user chosen promoters to a promoter array so answer can be verified
        for(int i = 0; i < 4 ; i++){
            TextView currentTarget = findViewById(targetViewIds[i]);
            //pass the string answer from the input textview back as a promoter
            if(currentTarget.getText().length() > 1){
                ansInputs[i] = new Promoter(currentTarget.getText().charAt(0),currentTarget.getText().charAt(5));
            }
            else{
                ansInputs[i] = new Promoter(currentTarget.getText().charAt(0));
            }

        }
        return ansInputs;
    }

    //function defined to send the app back to the mainActivity - done
    public void openActivityMain(){
        Intent mainActivity = new Intent(FillCircuit.this, MainActivity.class);
        startActivity(mainActivity);
    }

    //Drag and Drop Code - Allows for promoters to be inserted in circuit elements - done

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


            //call this method once drag event is sent to the listener
            public boolean onDrag(View view, DragEvent event) {
                TextView dropTarget = (TextView) view;
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        //checks if view can accept dragged data.
                        if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                            //defines the dragged textview
                            draggedView = (View) event.getLocalState();
                            draggedView.setVisibility(View.INVISIBLE);
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
                        //Dragging to the circuit
                        //defines the receiving textView
                        //sets the new text data
                        dropTarget.setText(dropped.getText().toString());
                        draggedView = (View) event.getLocalState();
                        Object tag = dropTarget.getTag();
                        if (tag != null) {
                            int existingID = (Integer) tag;
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }
                        if (draggedView.getId() == R.id.target2 || draggedView.getId() == R.id.target3 || draggedView.getId() == R.id.target4 || draggedView.getId() == R.id.target5) {
                            Object tag_existing = draggedView.getTag();
                            dropTarget.setTag(tag_existing);
                            dropped.setText("");
                            findViewById(draggedView.getId()).setOnClickListener(null);
                            draggedView.setVisibility(View.VISIBLE);
                        }
                        else {
                            dropTarget.setTag(dropped.getId());
                        }
                        findViewById(dropTarget.getId()).setOnTouchListener(new TouchListener());
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (event.getResult()) {
                            draggedView = (View) event.getLocalState();
                            if (draggedView.getId() == R.id.target2 || draggedView.getId() == R.id.target3 || draggedView.getId() == R.id.target4 || draggedView.getId() == R.id.target5) {
                                draggedView.setVisibility(View.VISIBLE);
                            }
                            else {
                              draggedView.setVisibility(View.INVISIBLE);
                            }
                        }
                        else {
                            draggedView = (View) event.getLocalState();
                            if (draggedView.getId() == R.id.target2 || draggedView.getId() == R.id.target3 || draggedView.getId() == R.id.target4 || draggedView.getId() == R.id.target5) {
                                dropped.setText("");
                                findViewById(draggedView.getId()).setOnClickListener(null);
                                Object tag_old = draggedView.getTag();
                                int existingID = (Integer) tag_old;
                                draggedView.setVisibility(View.VISIBLE);
                                findViewById(existingID).setVisibility(View.VISIBLE);
                            }
                            else {
                                draggedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    default:
                        Log.e("DragDrop Result","Unknown action type received by DragListener.");
                        break;
                }
                return false;
            }
        }
} //close class bracket