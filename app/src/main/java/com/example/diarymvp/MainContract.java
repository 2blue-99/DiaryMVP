package com.example.diarymvp;

import android.content.Context;

public interface MainContract {

    interface View {

    }
    interface Presenter {
        void attachView(View view);  // 뷰를 세팅함

        void detachView();  // 뷰를 제거함

        void loadItems(Context context);


    }
}
