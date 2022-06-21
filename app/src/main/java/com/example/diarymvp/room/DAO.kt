package com.example.diarymvp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

//data class UserMinimal(val title: String,
//                       val main: String,
//                       val rating: String,
//                       val weather: String)

interface DAO {

    // 데이터 베이스 불러오기
    @Query("SELECT * from Entity")
    fun getAll(): LiveData<List<Entity>>

    // 데이터 베이스 추가
    @Insert
    fun insert(entity: Entity)

    @Query("DELETE from entity")
    fun delete()

//    @Query("SELECT title from entity")
//    fun getGap(): List<Entity>
}