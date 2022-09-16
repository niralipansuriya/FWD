package com.swipefwd.ui.swiping.dater_both

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.databinding.ItemInstagramImagesBinding

class InstagramImageAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<InstagramImageAdapter.ImageHolder>() {

    class ImageHolder(val binding: ItemInstagramImagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val imagesList: ArrayList<String> = ArrayList()
    fun addAll(list: ArrayList<String>) {
        val size = imagesList.size
        imagesList.clear()
        notifyItemRangeRemoved(0, size)
        imagesList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ItemInstagramImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ImageHolder(this) }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.binding.apply {
            val context = ivImage.context
            val image = imagesList[position]
            Glide.with(context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImage)
            cardInsta.setOnClickListener {
                listener.invoke(image)
            }
        }
    }

    override fun getItemCount(): Int = imagesList.size

}
