package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

//    畫面初始化
    private fun initView(){

//    抓取輸入框元件
        val username=binding.username

//    登入按鈕監聽事件
        binding.login.setOnClickListener {

//            如果輸入框不為空
            if (username.text.isNotEmpty()){

//                請求資料庫 user資料
                Fuel.get("https://projectv1-f8c20-default-rtdb.firebaseio.com/user.json").responseString{
                    result ->

//                    請求成功
                    result.success {
                        val name=it.trim('"')

//                        對比輸入框及資料庫內容
                        if (username.text.toString()==name){

//                            切換到第二頁
                            Intent(this,MainActivity2::class.java).apply {
//                                傳遞名稱到下一頁
                                putExtra("name",name)
                                startActivity(this)
                            }

//                            結束當前頁面
                            finish()
                        }else{
//                            帳號錯誤
                            runOnUiThread {
                                Toast.makeText(applicationContext,"登入失敗",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

//                    請求失敗
                    result.failure {
                        runOnUiThread {
                            Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(applicationContext,"輸入框為空白",Toast.LENGTH_SHORT).show()
            }
        }
    }
}