package com.example.diarymvp

interface Contractor {
    interface View {
        fun showData()
    }

    interface Presenter {
        fun getData()
        fun setData()
    }
}