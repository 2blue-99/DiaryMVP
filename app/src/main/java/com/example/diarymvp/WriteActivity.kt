package com.example.diarymvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mydiary.databinding.ActivityMainBinding

class WriteActivity : AppCompatActivity() {

    private var mainBinding: ActivityMainBinding? = null
    private val b get() = mainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val intent = Intent(this, loading::class.java)
        startActivity(intent)
    }

    fun save(view: View) {
        if(b.mainText.text.toString() != "" && b.titleText.text.toString() != ""){
            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

            var dataList = arrayListOf(
                b.mainText.text.toString(),
                b.titleText.text.toString()
            )
            var intent = Intent(this, WriteActivity::class.java)
            intent.putExtra("data", dataList)
            startActivity(intent)

        }
        else
            Toast.makeText(this, "제목과 내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    fun back(view: View) {
        Toast.makeText(this, "뒤로가기", Toast.LENGTH_SHORT).show()
        finish()
    }
}