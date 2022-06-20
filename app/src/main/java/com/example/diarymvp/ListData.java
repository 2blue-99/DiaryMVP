package com.example.diarymvp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListData {
    @PrimaryKey @NonNull
    private String title;
    private String content;
    private String score;
    private String weather;
    private String date;

    public ListData(String title, String content, String score, String weather, String date){
        this.title = title;
        this.content = content;
        this.score = score;
        this.weather = weather;
        this.date = date;
    }

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
