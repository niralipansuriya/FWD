package com.swipefwd.ui.home.message

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.swipefwd.R
import com.swipefwd.databinding.ItemMessagesBinding
import com.swipefwd.utils.AppUtils.nextActivity

class MessageAdapter(val activity: Activity) : RecyclerSwipeAdapter<MessageAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemMessagesBinding.inflate(LayoutInflater.from(activity), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apply {

            Glide.with(activity)
                .load(R.drawable.user_girl_image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(binding.imgMessages)

            val spannable = SpannableString(binding.txtExpire.text.toString())
            spannable.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        activity.resources,
                        R.color.colorPagerTitle,
                        activity.theme
                    )
                ),
                25,
                spannable.length - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.txtExpire.apply {
                setText(spannable, TextView.BufferType.SPANNABLE)
                highlightColor = Color.TRANSPARENT
                movementMethod = LinkMovementMethod.getInstance()
            }

            binding.swipeMessage.showMode = SwipeLayout.ShowMode.PullOut
            binding.swipeMessage.addDrag(
                SwipeLayout.DragEdge.Right,
                binding.swipeMessage.findViewById(R.id.llDelete)
            )

            binding.btnDelete.setOnClickListener {
                activity.startActivity(
                    Intent(activity, MatchChatActivity::class.java)
                        .putExtra("extendMatch", true)
                )
            }

            binding.layoutMain.setOnClickListener {
                activity.nextActivity(ChatActivity::class.java)
            }
        }
    }

    override fun getItemCount(): Int = 10

    override fun getSwipeLayoutResourceId(position: Int): Int = R.id.swipeMessage
}