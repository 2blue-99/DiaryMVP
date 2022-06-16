package com.example.diarymvp;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private ArrayList<ListData> listDataArrayList;
    private TextView textViewDate;
    private String[] data;
    private SharedPreferences preferences;

    private RecyclerView.Adapter adapter;
    private String getTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listDataArrayList = new ArrayList<ListData>();
        textViewDate = findViewById(R.id.textViewDate);

        // 데이터 받고, 저장하고, 불러오기
        setData();
        if (data != null){ // 처음 시작할 때는 파일 안에 데이터가 없어서, null 오류가 뜸. 그래서 if로 오류 안나게 함. if-else문에 똑같은 코드가 들어가서 너무 비효율적! 방법 생각해보기
            getData();
        }
        else {
            getData();
        }
        // 이렇게 한 이유는 처음 시작할 때는 파일 안에 데이터가 없어서 null로 인식되어 오류가 남.
        // else로 하면 시작할 때마다, 맨 마지막으로 저장한 데이터가 뜸
        // 데이터 저장을 파일로하는건 했는데, 쌓이는건 어떻게하는지 모르겠....다.


        // RecyclerView 설정 코드들
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RecyclerView.Adapter() {
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
                viewHolder.setDate(listData.getDate());
            } // recyclerView 자체와 item 데이터셋을 서로 연결해주는 과정

            @Override
            public int getItemCount() {
                return listDataArrayList.size();
            } // 데이터셋의 데이터 개수이다.
        };
        recyclerView.setAdapter(adapter);

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

    protected String getTime(){  // 오늘 날짜 구하는 함수
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        getTime = dateFormat.format(date);

        return getTime;
    }
    protected void setData(){ // 데이터 받아와서 저장하는 함수
        preferences = getSharedPreferences("listData", MODE_PRIVATE);  // SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences.Editor editor = preferences.edit();  // 저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        getTime();

        Intent intent = getIntent();
        String getString = intent.getStringExtra("data");
        if(getString != null){
            getString = getString.replace("[", "");
            getString = getString.replace("]", "");
            data = getString.split(", ");

            editor.putString("title", data[0]); // key, value를 이용하여 저장하는 형태
            editor.putString("content", data[1]);
            editor.putString("score", data[2]);
            editor.putString("weather", data[3]);  // for문으로 줄여볼까 했지만 key 때문에 안됨.
            editor.putString("date", getTime);

            editor.commit();  //항상 commit & apply 를 해주어야 저장이 된다.
        }
    }
    protected void getData(){ // 저장한 데이터 불러와서 집어넣는 함수
        listDataArrayList.add(new ListData(
                preferences.getString("title", ""), preferences.getString("content", ""),
                preferences.getString("score", ""), preferences.getString("weather", ""),
                preferences.getString("date", "")
        ));
        //adapter.notifyDataSetChanged();
    }
}

/*
viewholder 디자인 변경
데이터 받고, 저장하고, 저장한거 부르기 성공! -> but 데이터가 쌓이지 않음. 해결하기
 */