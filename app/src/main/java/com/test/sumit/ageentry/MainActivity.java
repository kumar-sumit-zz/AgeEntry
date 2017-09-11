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

    private static final long TIME_INTERVAL = 2000;

    ArrayAdapter adapterAge;
    ArrayAdapter adapterID;

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

        ListView listViewId = (ListView) findViewById(R.id.personId);
        ListView listViewAge = (ListView) findViewById(R.id.personAge);

        // setting adapter for id and age listview columns
        listViewId.setAdapter(adapterID);
        listViewAge.setAdapter(adapterAge);

        // adds entry into id and age after 2 seconds
        addEntry();

    }

    private void addEntry() {
        Timer newTimer = new Timer();
        newTimer.scheduleAtFixedRate(new TimerTask() {
            Integer id= 1;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mID.add(id++);
                        mAGE.add(new Random().nextInt(40-10)+10);
                        adapterAge.notifyDataSetChanged();
                        adapterID.notifyDataSetChanged();
                    }
                });

            }
        }, new Date(), TIME_INTERVAL);
    }

}
