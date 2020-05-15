package com.tlu.mathapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsListAdapter extends ArrayAdapter<Result> {

    private static final String TAG = "ResultsListAdapter";

    private Context mContext;
    int mResource;

    public ResultsListAdapter(Context context, int resource, ArrayList<Result> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String score = getItem(position).getScore();

        Result result = new Result(name, score);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvScore = (TextView) convertView.findViewById(R.id.textView2);

        tvName.setText(name);
        tvScore.setText(score);

        return convertView;
    }

}
