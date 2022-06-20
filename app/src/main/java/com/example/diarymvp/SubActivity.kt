package com.example.diarymvp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.diarymvp.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private var mainBinding: ActivitySubBinding? = null
    private val b get() = mainBinding!!

    private var myWeather = ""

    var items = arrayOf("눈", "비", "흐림", "맑음")

    override fun onCreate(savedInstanceState: Bundle?) {
        mainBinding = ActivitySubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        val myAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)
        b.spinner.adapter = myAdapter
        b.spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                println(items[p2])
                myWeather = items[p2]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("암것도 선택안함")
            }
        }
    }


    fun save(view: View) {
        //db연결
//        val db = Room.databaseBuilder(
//            applicationContext, AppDatabase::class.java, "database"
//        ).allowMainThreadQueries().build()

        //         글을 쓰고 버튼을 누르면 db에 저장
//        db.dao().insert(Entity(b.titleText.text.toString(),"${b.mainText.text.toString()}","${b.star.rating.toString()}","$myWeather"))

        // db에 저장된 데이터 불러오기
//        db.dao().getAll().observe(this, Observer { todos ->
//            println("@@@@@"+todos.toString())
//        })
        if(b.mainText.text.toString() != "" && b.titleText.text.toString() != ""){
            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

            var dataList = arrayListOf(
                b.titleText.text.toString(),
                b.mainText.text.toString(),
                b.star.rating.toString(),
                myWeather
            )

            //프리젠트 활용
            presentInsertGap(dataList)


            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", dataList.toString())
            setResult(5678, intent)
            println("@@@보내는 값@@@"+dataList)
            finish()
        }
        else
            Toast.makeText(this, "제목과 내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    fun back(view: View) {
        Toast.makeText(this, "뒤로가기", Toast.LENGTH_SHORT).show()
        finish()
    }



//     프리젠트에서 모델 호출
    private fun presentInsertGap(dataList: ArrayList<String>) {
        modelInsertGap()
    }


    // 모델에서 데이터 처리
    private fun modelInsertGap(){
        val db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()
        db.dao().insert(Entity("${b.titleText.text.toString()}","${b.mainText.text.toString()}","${b.star.rating.toString()}","$myWeather"))
        db.dao().getAll().observe(this, Observer { todos ->
            println("@@@@@"+todos.toString())
        })
    }

}