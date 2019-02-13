package edu.temple.statedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.loneStar);

        if (savedInstanceState != null) {

            mEditText.setText(savedInstanceState.getString(getResources().getString(R.string.to_save)));

        }//end check for previous save

    }//end onCreate

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String temp = mEditText.toString();

        if ( !(temp.contains(getResources().getString(R.string.edit_text_label))) ||
                !(temp.contains("")) ) {

            //putExtra(<key>, <value>)
            outState.putString(getResources().getString(R.string.to_save),temp);

        }//end check

    }//end onSaveInstanceState

}//end MainActivity
