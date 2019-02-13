package edu.temple.timerlayoutdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout stateOne;
    LinearLayout stateTwo;
    LinearLayout stateThree;

    Button startButton;
    Button stopButton;
    Button lapButton;
    Button resumeButton;
    Button resetButton;

    int stateReference = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().commit();*/

        findEverything();
        setButtons();
        //setButtons_Bad();

        //setStateOne();


    }//end onCreate

    @Override
    protected void onStart() {
        super.onStart();
        loadState();

    }//end onStart

    /*
     * Find all layout resources
     */
    public void findEverything(){

        stateOne = findViewById(R.id.state_one);
        stateTwo = findViewById(R.id.state_two);
        stateThree = findViewById(R.id.state_three);

        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        lapButton = findViewById(R.id.lap_button);
        resumeButton = findViewById(R.id.resume_button);
        resetButton = findViewById(R.id.reset_button);

        startButton.setBackgroundColor(Color.CYAN);
        stopButton.setBackgroundColor(Color.RED);
        lapButton.setBackgroundColor(Color.GREEN);
        resumeButton.setBackgroundColor(Color.GREEN);
        resetButton.setBackgroundColor(Color.CYAN);

    }//end find everything method

    /*
     * Functions to set state of app
     */
    public void setStateOne(){

        stateOne.setVisibility(View.VISIBLE);
        stateTwo.setVisibility(View.GONE);
        stateThree.setVisibility(View.GONE);

        saveState(1);

    }//end setStateOne
    public void setStateTwo(){

        stateOne.setVisibility(View.GONE);
        stateTwo.setVisibility(View.VISIBLE);
        stateThree.setVisibility(View.GONE);

        saveState(2);

    }//end setStateTwo

    public void setStateThree(){

        stateOne.setVisibility(View.GONE);
        stateTwo.setVisibility(View.GONE);
        stateThree.setVisibility(View.VISIBLE);

        saveState(3);

    }//end setStateThree

    /*
     * Wrong way to set states
     */
    public void setStateOne_Bad(){

        stateOne.setVisibility(View.VISIBLE);
        stateTwo.setVisibility(View.INVISIBLE);
        stateThree.setVisibility(View.INVISIBLE);

    }//end setStateOne
    public void setStateTwo_Bad(){

        stateOne.setVisibility(View.INVISIBLE);
        stateTwo.setVisibility(View.VISIBLE);
        stateThree.setVisibility(View.INVISIBLE);

    }//end setStateTwo

    public void setStateThree_Bad(){

        stateOne.setVisibility(View.INVISIBLE);
        stateTwo.setVisibility(View.INVISIBLE);
        stateThree.setVisibility(View.VISIBLE);

    }//end setStateThree

    /*
     * Set initial state and set listeners
     */
    public void setButtons(){

        //initialize by setting state

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateTwo();
            }
        });//end start button

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateThree();
            }
        });//end stop Button

        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.lap_message,Toast.LENGTH_SHORT).show();
            }
        });//end lap button

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateTwo();
            }
        });//end reset button

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateOne();
            }
        });//end reset button

    }//end setButtons

    public void setButtons_Bad(){

        //initialize by setting state
        setStateOne_Bad();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateTwo_Bad();
            }
        });//end start button

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateThree_Bad();
            }
        });//end stop Button

        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.lap_message,Toast.LENGTH_SHORT).show();
            }
        });//end lap button

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateTwo_Bad();
            }
        });//end reset button

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStateOne_Bad();
            }
        });//end reset button

    }//end setButtons

    /*
     * Method to save states
     */
    public void saveState(int stateVal){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.previous_state), stateVal);
        editor.commit();

    }//end saveState

    /*
     * Method to load previous state
     */
    public void loadState(){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultState = getResources().getInteger(R.integer.default_value);
        stateReference = sharedPref.getInt(getString(R.string.previous_state),defaultState);

        if(stateReference > 0){

            switch(stateReference) {

                case 1: setStateOne();
                    break;

                case 2: setStateTwo();
                    break;

                case 3: setStateThree();
                    break;

            }//end switch

        } else {

            setStateOne();

        }//end

    }//end load State

}//end MainActivity
