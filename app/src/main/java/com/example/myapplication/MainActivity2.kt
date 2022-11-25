package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

//    畫面初始化
    @SuppressLint("SetTextI18n")
    private fun initView(){

//    接收傳遞來的名稱
        intent.getStringExtra("name").also {
//            設定名稱
            binding.name.text="HI $it,"
        }
//    專案一按鈕監聽
        binding.button2.setOnClickListener {
//            切換到第三頁
            Intent(this,MainActivity3::class.java).apply {
                putExtra("pid","WNC_PRJ1")
                startActivity(this)
            }
        }
//    專案二按鈕監聽
        binding.button3.setOnClickListener {
//            切換到第三頁
            Intent(this,MainActivity3::class.java).apply {
                putExtra("pid","WNC_PRJ2")
                startActivity(this)
            }
        }
//    專案三按鈕監聽
        binding.button4.setOnClickListener {
//            切換到第三頁
            Intent(this,MainActivity3::class.java).apply {
                putExtra("pid","WNC_PRJ3")
                startActivity(this)
            }
        }
    }
}