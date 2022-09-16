package com.swipefwd.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemActivityConnectionsBinding

class ConnectionAdapter(val context: Context) :
    RecyclerView.Adapter<ConnectionAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemActivityConnectionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemActivityConnectionsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.apply {
        }
    }

    override fun getItemCount(): Int = 6
}