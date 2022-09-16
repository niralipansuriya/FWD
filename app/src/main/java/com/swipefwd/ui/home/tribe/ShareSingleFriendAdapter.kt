package com.swipefwd.ui.home.tribe

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemMySingleFriendsBinding

class ShareSingleFriendAdapter(val activity: Activity, private val listener: () -> Unit) :
    RecyclerView.Adapter<ShareSingleFriendAdapter.MyHolder>() {

    inner class MyHolder(val binding: ItemMySingleFriendsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemMySingleFriendsBinding.inflate(LayoutInflater.from(activity), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.apply {
            imgOnline.visibility = View.GONE
            imgFwd.setOnClickListener {
                listener.invoke()
            }
        }
    }

    override fun getItemCount(): Int = 6
}