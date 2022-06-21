package com.example.diarymvp

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.diarymvp.room.AppDatabase
import com.example.diarymvp.room.Entity

class Model(applicationContext: Context) : LifecycleOwner {
    val db = Room.databaseBuilder(
        applicationContext, AppDatabase::class.java, "database"
    ).allowMainThreadQueries().build()

    fun saveData(dataList: ArrayList<String>) {
        db.dao().insert(Entity("${dataList[0]}","${dataList[1]}","${dataList[2]}","${dataList[3]}"))
        println("@@@save!@@@${dataList[0]} ${dataList[1]} ${dataList[2]} ${dataList[3]}")
    }

    fun showData() {
        db.dao().getAll().observe(this, Observer { todos ->
            println("@@@@@"+todos.toString())
        })
    }

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }
}