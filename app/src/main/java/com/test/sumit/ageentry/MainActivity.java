package com.test.sumit.ageentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Integer> mID;
    private List<Integer> mAGE;
    ArrayAdapter adapterAge;
    ArrayAdapter adapterID;

    private static final long TIME_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mID = new ArrayList<>();
        mAGE = new ArrayList<>();
        // initialiseing list so as the adapter finds an entry onCreating the listview
        mID.add(0);
        mAGE.add(11);

        adapterID = new ArrayAdapter<Integer>(this,
                R.layout.textview, mID);
        adapterAge = new ArrayAdapter<Integer>(this,
                R.layout.textview, mAGE);

        // listview for person id field
        ListView listViewId = (ListView) findViewById(R.id.personId);
        // listview for person age field
        ListView listViewAge = (ListView) findViewById(R.id.personAge);

        // setting adapter for id and age listview columns
        listViewId.setAdapter(adapterID);
        listViewAge.setAdapter(adapterAge);

        // adds entry into id and age after 2 seconds
        addEntry();

    }

    private void addEntry() {
        Timer newTimer = new Timer();

        // using timer task to run a task after an interval
        // here the interval is TIME_INTERVAL and the starting time for
        // recurring task is current time
        newTimer.scheduleAtFixedRate(new TimerTask() {
            Integer id= 1;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // increment id after every addition to listview
                        mID.add(id++);
                        // using age range of 10-40 years for demo purpose
                        // can be set as constants and changed accordingly
                        // here we choose a random age between 10 to 40 years
                        mAGE.add(new Random().nextInt(40-10)+10);
                        // notify the listview about change in dataset to update
                        // the views which is called on uithread
                        adapterAge.notifyDataSetChanged();
                        adapterID.notifyDataSetChanged();
                    }
                });

            }
        }, new Date(), TIME_INTERVAL);
    }

}
