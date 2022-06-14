package com.example.diarymvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListData> listDataArrayList;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listDataArrayList = new ArrayList<ListData>();
        textViewDate = findViewById(R.id.textViewDate);

        Intent intent = getIntent();
        String getString = intent.getStringExtra("data");
        if(getString != null){
            getString = getString.replace("[", "");
            getString = getString.replace("]", "");
            String[] data = getString.split(", ");

            listDataArrayList.add(new ListData(data[0], data[1],data[2],data[3]));
            // 데이터 불러오기는 성공했으나, 쌓이지 않음! 이 방법이 아닌 다른 방법을 찾아야될듯함.
        }

        // RecyclerView 설정 코드들
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
            } // adapter와 연결하는 recyclerView에 추가할 item레이아웃과 item Data를 bind함

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                CustomViewHolder viewHolder = (CustomViewHolder) holder;
                ListData listData = listDataArrayList.get(position);

                viewHolder.setTitle(listData.getTitle());
                viewHolder.setContent(listData.getContent());
                viewHolder.setScore(listData.getScore());
                viewHolder.setWeather(listData.getWeather());
            } // recyclerView 자체와 item 데이터셋을 서로 연결해주는 과정

            @Override
            public int getItemCount() {
                return listDataArrayList.size();
            } // 데이터셋의 데이터 개수이다.
        });

        // 버튼 누를 시 SubActivity로 이동
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

/*
button 디자인 변경
viewholder 디자인 변경
데이터 받아오기 성공 -> but 데이터가 안쌓임.
recyclerView 옆에 스크롤 만들어주기
 */