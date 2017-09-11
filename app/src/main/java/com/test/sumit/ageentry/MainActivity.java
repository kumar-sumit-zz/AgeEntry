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

    private Integer[] mId= new Integer[]{1,2,3,4,5,6,7,8,9,10};
    private List<Integer> mID;
    private Integer[] mAge = new Integer[]{10,40,20,30,22,12,27,19,16,33};
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
        mID.add(0);
        mAGE.add(11);

        ListView listViewId = (ListView) findViewById(R.id.personId);
        adapterID = new ArrayAdapter<Integer>(this,
                R.layout.textview, mID);
        adapterAge = new ArrayAdapter<Integer>(this,
                R.layout.textview, mAGE);

        ListView listViewAge = (ListView) findViewById(R.id.personAge);
        listViewAge.setAdapter(adapterAge);
        listViewId.setAdapter(adapterID);
        addEntry();

    }

    private void addEntry() {
        long curr_time = System.currentTimeMillis();
        Timer newTimer = new Timer();
        newTimer.scheduleAtFixedRate(new TimerTask() {
            Integer id= 0;
            Integer age = 10;
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
