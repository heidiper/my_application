package com.example.myapplication

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

// 資料類別
data class MyProject(val STAGE:String,val QTY:Int,val HW_VER:Double,val SW_VER:Double,val DATE:String) {
//    解析json
    class Deserializer : ResponseDeserializable<Array<MyProject>> {
        override fun deserialize(content: String): Array<MyProject>
                = Gson().fromJson(content, Array<MyProject>::class.java)
    }
}