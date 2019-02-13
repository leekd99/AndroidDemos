package edu.temple.adapterdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    Button addButton;
    Button switchButton;
    ArrayList<String> dataSetOne;
    ArrayList<String> dataSetTwo;

    ListView secondView;

    ArrayAdapter myFirstAdapter;
    ArrayAdapter mySecondAdapter;
    int set = 0;
    int state = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().commit();*/

        findViews();
        functionality();;
        //myListView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSetOne));

        myListView.setAdapter(myFirstAdapter);

    }//end onCreate

    @Override
    protected void onStart() {
        super.onStart();

        loadState();

    }//end onStart

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveState();

    }//end onDestroy

    public void findViews(){

        myListView = findViewById(R.id.myList);
        addButton = findViewById(R.id.add_button);
        switchButton = findViewById(R.id.switch_button);

        dataSetOne = new ArrayList<>();
        dataSetTwo = new ArrayList<>();

        myFirstAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataSetOne);
        mySecondAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataSetTwo);

        //myListView.setAdapter(myFirstAdapter);

    }//end findviews

    public void functionality(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDataSet();
                myFirstAdapter.notifyDataSetChanged();
                mySecondAdapter.notifyDataSetChanged();

            }
        });//end add

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int switchState = state%2;

                if(switchState == 0) {

                    myListView.setAdapter(myFirstAdapter);
                    state++;

                } else {

                    myListView.setAdapter(mySecondAdapter);
                    state--;

                }//end

            }
        });//end switch

    }//end functionality

    public void addDataSet(){

        set++;
        dataSetOne.add("Item " + set);
        dataSetTwo.add("Thing " + set);

    }//end add to one

    public void saveState(){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> listOne = new HashSet<>();
        Set<String> listTwo = new HashSet<>();

        //check to see if there is anything to save
        //if there is save it
        if( (dataSetOne.size() != 0) && (dataSetTwo.size() != 0) ){

            listOne.addAll(dataSetOne);
            listTwo.addAll(dataSetTwo);

            editor.putStringSet(getString(R.string.first_list),listOne);
            editor.putStringSet(getString(R.string.second_list),listTwo);
            editor.apply();

        }//end check for empty

    }//end saveState

    //load state back
    public void loadState(){

        Set<String> listOne = new HashSet<>();
        Set<String> listTwo = new HashSet<>();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        listOne = sharedPref.getStringSet(getString(R.string.first_list),new HashSet<String>());
        listTwo = sharedPref.getStringSet(getString(R.string.second_list),new HashSet<String>());

        if ( (listOne.size() != 0) && (listTwo.size() != 0) ) {

            dataSetOne.addAll(listOne);
            dataSetTwo.addAll(listTwo);

            myFirstAdapter.notifyDataSetChanged();
            mySecondAdapter.notifyDataSetChanged();

        }//end if

    }//end loadState

}//end main
