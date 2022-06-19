package com.example.diarymvp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListData.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract RoomDao mainDao();
}