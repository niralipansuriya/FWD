package com.swipefwd.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemRematchPlansBinding

class RematchPlansAdapter(val context: Context) :
    RecyclerView.Adapter<RematchPlansAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemRematchPlansBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnPlanClickListener {
        fun onPlanClickListener(position: Int)
    }

    private var mListener: OnPlanClickListener? = null

    fun setOnPlanClickListener(listener: OnPlanClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemRematchPlansBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apply {
            binding.txtSavePlan.setOnClickListener {
                mListener?.onPlanClickListener(position)
            }
        }
    }

    override fun getItemCount(): Int = 4
}