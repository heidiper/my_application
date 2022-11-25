package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMain3Binding
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private val viewAdapter=ProjectAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

//    畫面初始化
    @SuppressLint("SetTextI18n")
    private fun initView(){
//    取得接收到的專案代號
        val planName=intent.getStringExtra("pid")

//    設定返回按鈕監聽事件
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
//    設定顯示文字
        binding.planName.text="$planName MODEL PLAN"

//    讀取資料庫內容
        if (planName != null) {
            readDataBase(planName)
        }

//    設定RecyclerView
        binding.projectList.apply {
            val layoutManager=LinearLayoutManager(applicationContext)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

//            添加分隔線
            addItemDecoration(DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            ))

//            尺寸固定
            setHasFixedSize(true)
            setLayoutManager(layoutManager)
            adapter=viewAdapter
        }

    }

//    讀取資料庫
    private fun readDataBase(pid:String){
        Fuel.get("https://projectv1-f8c20-default-rtdb.firebaseio.com/$pid.json")
            .responseObject(MyProject.Deserializer()){
                result ->
                result.success {
                    runOnUiThread {
//                        將資料傳遞到adapter
                        viewAdapter.dataList=it
                    }
                }
                result.failure {
                    runOnUiThread {
                        Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

//    點擊返回時結束頁面
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}