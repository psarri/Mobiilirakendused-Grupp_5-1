package com.tlu.mathapp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultsTable extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_table); // Sätestab layouti
        ListView listView = (ListView)findViewById(R.id.list_view);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "results").allowMainThreadQueries().build();
        ArrayList<Result> results = (ArrayList<Result>) db.resultsDao().getAll();
        // Sort the array of objects
        Collections.sort(results, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                int comp = Integer.parseInt(o1.getScore()) - Integer.parseInt(o2.getScore());
                return -comp; // For Ascendid order -comp
            }
        });

        ResultsListAdapter adapter = new ResultsListAdapter(this, R.layout.adapter_view_layout, results);
        listView.setAdapter(adapter);
    }
}
