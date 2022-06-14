package com.example.diarymvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListData> listDataArrayList;

    private ArrayList<String> titleArrayList;
    private ArrayList<String> contentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), loading.class);
        startActivity(intent);

        titleArrayList = new ArrayList<>();
        contentArrayList = new ArrayList<>();

        // SubActivity에서 데이터 잘 넘어오는지 테스트함
        // 잘 넘어옴! 그냥 RecyclerView layout에 안보이는 거 뿐임
        Intent intent2 = getIntent();
        String title = intent2.getStringExtra("title");
        TextView textView = findViewById(R.id.textView3);
        if (title != null) {
            textView.setText(title);
            Toast.makeText(getApplicationContext(),"데이터 넘어옴!", Toast.LENGTH_SHORT);
        }
        else
            Toast.makeText(getApplicationContext(),"데이터 안 넘어옴", Toast.LENGTH_SHORT);
        System.out.println("밖에 있는 title : " + title);

        /*
        // 배열 형식을 어떻게 하나씩 꺼내서 넣을지 알아보기!
        Intent intent1 = getIntent();
        String[] data = new String[2];
        for(int i = 0; i < data.length; i++)
            data[i] = intent1.getStringArrayExtra("data");
        TextView textView = findViewById(R.id.textView3);
        textView.setText(data[0]);
        */

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
            }

            // 실제 각 뷰 홀더에 데이터를 연결해주는 함수
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                CustomViewHolder viewHolder = (CustomViewHolder) holder;
                ListData listData = listDataArrayList.get(position);

                viewHolder.setTitle(titleArrayList.get(position));
                viewHolder.setContent(contentArrayList.get(position));

                Intent intent = getIntent();
                String title = intent.getStringExtra("title");
                TextView textViewTitle = findViewById(R.id.textViewTitle);
                if (title != null) {
                    textViewTitle.setText(title);
                    viewHolder.setTitle(title);
                    Toast.makeText(getApplicationContext(),"데이터 넘어옴!", Toast.LENGTH_SHORT);
                }
                else
                    Toast.makeText(getApplicationContext(),"데이터 안 넘어옴", Toast.LENGTH_SHORT);
                System.out.println("리사이클러뷰에 있는 title : " + title);

                //viewHolder.setTitle(listData.getTitle());  //임시
                //viewHolder.setContent(listData.getContent());  //임시
            }

            @Override
            public int getItemCount() {
                return titleArrayList.size();
                //return listDataArrayList.size();  //임시
            }
        });

        // 버튼 누를 시 SubActivity로 이동
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        });
    }



    /*Intent intent = getIntent();
    String text = intent.getStringExtra("text");
    TextView textViewContent = findViewById();*/

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