package com.tlu.mathapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultsDao {

    @Query("SELECT * FROM results")
    List<Result> getAll();

    @Query("SELECT * FROM results WHERE name LIKE :name LIMIT 1")
    Result findByName(String name);

    @Insert
    void insertAll(Result... results);

}
