package com.tlu.mathapp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class ResultsTable extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_table); // Sätestab layouti
        ListView listView = (ListView)findViewById(R.id.list_view);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "results").allowMainThreadQueries().build();
        List<String> names = db.resultsDao().getAllNames();
        List<String> scores = db.resultsDao().getAllScores();
        List<String> merged = new ArrayList<>();
        for(int i = 0; i<names.size(); i++){
            String mergedValue = names.get(i) + " - " + scores.get(i) + " points";
            merged.add(mergedValue);
        }
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, merged);
        listView.setAdapter(adapter);
    }
}
