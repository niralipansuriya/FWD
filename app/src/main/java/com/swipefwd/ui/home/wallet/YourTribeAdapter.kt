package com.swipefwd.ui.home.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemYourTribeBinding

class YourTribeAdapter(val context: Context) :
    RecyclerView.Adapter<YourTribeAdapter.YourTribeHolder>() {

    class YourTribeHolder(val binding: ItemYourTribeBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourTribeHolder =
        ItemYourTribeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { YourTribeHolder(this) }

    override fun onBindViewHolder(holder: YourTribeHolder, position: Int) {
        holder.binding.apply {

        }
    }

    override fun getItemCount(): Int = 3

}
