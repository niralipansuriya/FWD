package com.swipefwd.ui.home.message

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.swipefwd.R
import com.swipefwd.databinding.ItemMessageMatchesBinding
import com.swipefwd.utils.AppUtils.nextActivity

class MessageMatchAdapter(val activity: Activity, private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MessageMatchAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemMessageMatchesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemMessageMatchesBinding.inflate(LayoutInflater.from(activity), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apply {
            Glide.with(activity)
                .load(R.drawable.user_girl_image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(binding.imgMatches)

            binding.imgMatches.setOnClickListener {
                itemClickListener.invoke(position)
            }
            itemView.setOnClickListener {
                activity.nextActivity(MatchChatActivity::class.java)
            }
        }
    }

    override fun getItemCount(): Int = 7
}