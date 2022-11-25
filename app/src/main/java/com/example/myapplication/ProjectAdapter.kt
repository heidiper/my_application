package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProjectItemBinding

//自訂adapter
class ProjectAdapter: RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    private lateinit var binding: ProjectItemBinding

//    建立資料清單
    var dataList:Array<MyProject> = arrayOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field=value

//        資料更新時刷新頁面
        notifyDataSetChanged()
    }
    class ViewHolder(val view:ProjectItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ProjectItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        資料整理顯示
        holder.view.STAGE.text=dataList[position].STAGE
        holder.view.QTY.text=dataList[position].QTY.toString()
        holder.view.HW.text=dataList[position].HW_VER.toString()
        holder.view.SW.text=dataList[position].SW_VER.toString()
        holder.view.DATE.text=dataList[position].DATE
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}