package com.example.diarymvp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {
    @Query("")
    List<ListData> getAll();

    @Query("")
    List<ListData> loadAllByIds(int[] userIds);

    @Query("")
    ListData findByName(String first, String last);

    @Insert
    void insertAll(ListData listData);

    @Delete
    void delete(ListData listData);
}
