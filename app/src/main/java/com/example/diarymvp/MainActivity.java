package com.example.diarymvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListData> listDataArrayList;

    //private ArrayList<String> titleArrayList;
    //private ArrayList<String> contentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //titleArrayList = new ArrayList<>();
        //contentArrayList = new ArrayList<>();
        listDataArrayList = new ArrayList<ListData>();  //임시

        listDataArrayList.add(new ListData("오늘 하루 날씨를 적어보자",
                "오늘 하루는 하늘이 참 맑다. 이야야ㅑㅑ!\n산책을 나가보자."));  //임시
        listDataArrayList.add(new ListData("오늘 하루 기분을 적어보자",
                "오늘 전공 시험을 봤다.\n신나게 망쳤다.\n괜찮다 성적이 인생은 아니다."));  //임시
        listDataArrayList.add(new ListData("오늘 하루 날씨를 적어보자",
                "오늘 하루는 하늘이 참 맑다. 이야야ㅑㅑ!\n산책을 나가보자."));  //임시
        listDataArrayList.add(new ListData("오늘 하루 기분을 적어보자",
                "오늘 전공 시험을 봤다.\n신나게 망쳤다.\n괜찮다 성적이 인생은 아니다."));  //임시
        listDataArrayList.add(new ListData("오늘 하루 날씨를 적어보자",
                "오늘 하루는 하늘이 참 맑다. 이야야ㅑㅑ!\n산책을 나가보자."));  //임시
        listDataArrayList.add(new ListData("오늘 하루 기분을 적어보자",
                "오늘 전공 시험을 봤다.\n신나게 망쳤다.\n괜찮다 성적이 인생은 아니다."));  //임시

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater
                        .from(MainActivity.this)
                        .inflate(R.layout.list_viewholder_layout, parent, false);
                RecyclerView.ViewHolder viewHolder = new CustomViewHolder(itemView);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                CustomViewHolder viewHolder = (CustomViewHolder) holder;
                ListData listData = listDataArrayList.get(position);

                //viewHolder.setTitle(titleArrayList.get(position));
                //viewHolder.setContent(contentArrayList.get(position));
                viewHolder.setTitle(listData.getTitle());  //임시
                viewHolder.setContent(listData.getContent());  //임시
            }

            @Override
            public int getItemCount() {
                //return titleArrayList.size();
                return listDataArrayList.size();  //임시
            }
        });
    }



    public void add(View view) {


    }
}

/*
button 디자인 변경
viewholder 디자인 변경
title, date, content 데이터 받아오기
button 선택 시 일기 쓰는 페이지로 넘어가기
recyclerView 옆에 스크롤 만들어주기
 */