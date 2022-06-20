package com.example.diarymvp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "listdata")
public class ListData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "score")
    private String score;
    @ColumnInfo(name = "weather")
    private String weather;
    @ColumnInfo(name = "date")
    private String date;

    public ListData(Integer id, String title, String content, String score, String weather, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.score = score;
        this.weather = weather;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getScore() {
        return score;
    }
    public String getWeather() {
        return weather;
    }
    public String getDate(){ return date; }
}
