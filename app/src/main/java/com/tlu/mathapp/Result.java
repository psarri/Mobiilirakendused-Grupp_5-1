package com.tlu.mathapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class Result {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "score")
    public String score; // Its safer to store numbers as strings in db


    public Result(String name, String score) {
        this.name = name;
        this.score = score;
    }

}
