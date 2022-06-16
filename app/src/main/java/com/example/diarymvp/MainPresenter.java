package com.example.diarymvp;

import android.content.Context;

class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;
    private ListData listData;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    //@Override
    public void setListData(ListData listData){
        this.listData = listData;
    }

    @Override
    public void loadItems(Context context) {
    }


}
