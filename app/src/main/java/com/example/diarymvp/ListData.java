package com.example.diarymvp;

public class ListData {
    private String title;
    private String content;

    public ListData(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
}
