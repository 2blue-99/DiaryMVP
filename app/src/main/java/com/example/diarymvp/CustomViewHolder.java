package com.example.diarymvp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView_title;
    private final TextView textView_content;
    private final TextView textView_score;
    private final TextView textView_weather;
    private String title;
    private String content;
    private String score;
    private String weather;

    public CustomViewHolder(View itemView) {
        super(itemView);

        textView_title = itemView.findViewById(R.id.textViewTitle);
        textView_content = itemView.findViewById(R.id.textViewContent);
        textView_score = itemView.findViewById(R.id.textViewScore);
        textView_weather = itemView.findViewById(R.id.textViewWeather);
    }

    public void setTitle(String title) {
        this.title = title;
        textView_title.setText(title);
    }
    public void setContent(String content){
        this.content = content;
        textView_content.setText(content);
    }
    public void setScore(String score){
        this.score = score;
        textView_score.setText(score);
    }
    public void setWeather(String weather){
        this.score = weather;
        textView_weather.setText(weather);
    }
}
