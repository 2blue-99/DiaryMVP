package com.example.diarymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private String list;
    private ArrayList<ArrayList<String>> datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listDataArrayList = new ArrayList<ListData>();
        textViewDate = findViewById(R.id.textViewDate);

        datalist = new ArrayList<ArrayList<String>>();

        // 데이터 받고, 저장하고, 불러오기
        setData();
        getData();


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
                startActivityForResult(intent, 1234);
//                finish();
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

    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        Log.d(MainActivity.class.getSimpleName(), "getTime: ");
        System.out.println("@@onActivityResult@@");
        super.onActivityResult(requestCode, resultCode, resultIntent);
        if (requestCode == 1234 && resultCode == 5678) {
            list = resultIntent.getStringExtra("data");
            System.out.println("onActivityResult!!!! "+ list);
        }
    }
    @Override
    // SubActivity에서 데이터 넘기면 윗 함수 다음 일로 옴
    // 여기서 넘겨받을때마다 리스트에 추가시키면 될듯함.
    protected void onResume() {
        System.out.println("@@onResume@@");
        super.onResume();
        setData();
    }


    protected void setData(){ // 데이터 받아와서 저장하는 함수
        preferences = getSharedPreferences("listData", MODE_PRIVATE);  // SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences.Editor editor = preferences.edit();  // 저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        getTime();

        /*Intent intent = getIntent();
        String getString = intent.getStringExtra("data");*/
        if(list != null){
            list = list.replace("[", "");
            list = list.replace("]", "");
            data = list.split(", ");
            System.out.println("data : " + data[0] + "," + data[1] + "," + data[2] + "," + data[3]);
            System.out.println("getTime : " + getTime);

            editor.putString("title", data[0]); // key, value를 이용하여 저장하는 형태
            editor.putString("content", data[1]);
            editor.putString("score", data[2]);
            editor.putString("weather", data[3]);  // for문으로 줄여볼까 했지만 key 때문에 안됨.
            editor.putString("date", getTime);

            editor.commit();  //항상 commit & apply 를 해주어야 저장이 된다.
            getData();
        }
    }
    protected void getData(){ // 저장한 데이터 불러와서 집어넣는 함수
        System.out.println("getData 함수 실행은 됨");

        if(data != null){
            listDataArrayList.add(new ListData(
                    preferences.getString("title", ""), preferences.getString("content", ""),
                    preferences.getString("score", ""), preferences.getString("weather", ""),
                    preferences.getString("date", "")
            ));

            /*listDataArrayList.add(new ListData(data[0], data[1], data[2], data[3], getTime));
            testlist = new ArrayList<String>();

            for(int i = 0; i < data.length; i++) testlist.add(data[i]);
            testlist.add(getTime);

            for(int i = 0; i < testlist.size() - 4; i++) datalist.add(testlist);
            */
            adapter.notifyDataSetChanged();
        }
    }
}

/*
viewholder 디자인 변경
데이터 받고, 저장하고, 저장한거 부르기 성공! -> but 데이터가 쌓이지 않음. 해결하기
 */