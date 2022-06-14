package com.example.diarymvp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView_title;
    private final TextView textView_content;
    private String title;
    private String content;

    public CustomViewHolder(View itemView) {
        super(itemView);

        textView_title = itemView.findViewById(R.id.textViewTitle);
        textView_content = itemView.findViewById(R.id.textViewContent);
    }

    public void setTitle(String title) {
        this.title = title;
        textView_title.setText(title);
    }
    public void setContent(String content){
        this.content = content;
        textView_content.setText(content);
    }
}
