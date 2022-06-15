package com.example.diarymvp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
                myWeather = items[2]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("암것도 선택안함")
            }
        }
    }


    fun save(view: View) {
        if(b.mainText.text.toString() != "" && b.titleText.text.toString() != ""){
            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

            var dataList = arrayListOf(
                b.titleText.text.toString(),
                b.mainText.text.toString(),
                b.star.rating.toString(),
                myWeather
            )
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", dataList.toString())
            startActivity(intent)
            finish()
            println("@@@보내는 값@@@"+dataList)

        }
        else
            Toast.makeText(this, "제목과 내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    fun back(view: View) {
        Toast.makeText(this, "뒤로가기", Toast.LENGTH_SHORT).show()
        finish()
    }


}