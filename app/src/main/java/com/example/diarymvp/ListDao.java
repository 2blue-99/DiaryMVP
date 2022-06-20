package com.example.diarymvp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ListDao {
    @Query("SELECT * FROM ListData")
    List<ListData> getAll();

    @Query("SELECT * FROM ListData WHERE ID IN (:listid)")
    List<ListData> loadAllByIds(int[] listid);

    @Insert
    void insertAll(ListData... listdata);

    @Delete
    void delete(ListData listdata);
}
