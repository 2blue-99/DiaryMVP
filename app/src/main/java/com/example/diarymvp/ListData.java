package com.example.diarymvp;

public class ListData {
    private String title;
    private String content;
    private String score;
    private String weather;

    public ListData(String title, String content, String score, String weather){
        this.title = title;
        this.content = content;
        this.score = score;
        this.weather = weather;
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
}
