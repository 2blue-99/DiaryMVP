package com.example.diarymvp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView_title;
    private final TextView textView_content;
    private final TextView textView_score;
    private final TextView textView_weather;
    private final TextView textView_date;
    private String title;
    private String content;
    private String score;
    private String weather;
    private String date;


    public CustomViewHolder(View itemView) {
        super(itemView);

        textView_title = itemView.findViewById(R.id.textViewTitle);
        textView_content = itemView.findViewById(R.id.textViewContent);
        textView_score = itemView.findViewById(R.id.textViewScore);
        textView_weather = itemView.findViewById(R.id.textViewWeather);
        textView_date = itemView.findViewById(R.id.textViewDate);

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                System.out.println("position : " + position);

                // putExtra로 데이터를... 보내려고 했지만 Room이란것으로 바뀌었다
            }
        });*/
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
    public void setDate(String date) {
        this.date = date;
        textView_date.setText(date);
    }
}
