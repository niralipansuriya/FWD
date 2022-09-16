package com.swipefwd.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemHomeMembersBinding

class HomeMembersAdapter : RecyclerView.Adapter<HomeMembersAdapter.MemberHolder>() {
    class MemberHolder(binding: ItemHomeMembersBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberHolder =
        ItemHomeMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { MemberHolder(this) }

    override fun onBindViewHolder(holder: MemberHolder, position: Int) {
    }

    override fun getItemCount(): Int = 10

}
