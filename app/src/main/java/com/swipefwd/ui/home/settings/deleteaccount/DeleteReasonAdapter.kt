package com.swipefwd.ui.home.settings.deleteaccount

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemDeleteReasonBinding

class DeleteReasonAdapter(val context: Context, private val listener: (String) -> Unit) :
    RecyclerView.Adapter<DeleteReasonAdapter.MyHolder>() {

    public var selectedPosition = 0
    private val reasonsList = ArrayList<String>()

    fun addAll(list: ArrayList<String>) {
        reasonsList.clear()
        reasonsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyHolder(val binding: ItemDeleteReasonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemDeleteReasonBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val reason = reasonsList[position]
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

    override fun getItemCount(): Int = reasonsList.size
}