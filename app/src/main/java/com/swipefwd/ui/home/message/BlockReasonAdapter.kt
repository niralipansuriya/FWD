package com.swipefwd.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemDeleteReasonBinding

class BlockReasonAdapter(val context: Context, private val listener: (String) -> Unit) :
    RecyclerView.Adapter<BlockReasonAdapter.MyHolder>() {
    private var selectedPosition = 0
    private val reasonList = ArrayList<String>()

    fun addAll(list: ArrayList<String>) {
        val size = reasonList.size
        reasonList.clear()
        notifyItemRangeRemoved(0, size)
        reasonList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    inner class MyHolder(val binding: ItemDeleteReasonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemDeleteReasonBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val reason = reasonList[position]
        holder.apply {
            binding.txtReason.text = reason

            if (selectedPosition == position) {
                binding.imgReason.setImageResource(R.drawable.oval_inviteselect)
            } else {
                binding.imgReason.setImageResource(R.drawable.oval_invite)
            }

            itemView.setOnClickListener {
                val pos = selectedPosition
                if (selectedPosition != position) {
                    selectedPosition = position
                    notifyItemChanged(pos)
                    notifyItemChanged(position)
                    listener.invoke(reason)
                }
            }
        }
    }

    override fun getItemCount(): Int = reasonList.size
}