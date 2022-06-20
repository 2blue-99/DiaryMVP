package com.example.diarymvp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ListData.class}, version = 1)
public abstract class ListDB extends RoomDatabase {
    private static ListDB INSTANCE = null;
    public abstract ListDao listDao();

    public static ListDB getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ListDB.class, "list.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
