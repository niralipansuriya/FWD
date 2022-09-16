package com.swipefwd.ui.swiping.dater_both

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemHomeMembersBinding
import com.swipefwd.data.models.SwipingProfileModel
import com.swipefwd.utils.AppUtils.createPlaceholderImage

class FacebookFriendsListAdapter(private var isGreenProfile: Boolean) :
    RecyclerView.Adapter<FacebookFriendsListAdapter.ImageHolder>() {

    private val profileList: ArrayList<SwipingProfileModel.ProfileModel> = ArrayList()

    class ImageHolder(val binding: ItemHomeMembersBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ItemHomeMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ImageHolder(this) }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val connectorUser = profileList[position]
        holder.binding.apply {
            if(connectorUser.firstName != "") {
                txtName.text = connectorUser.firstName
                val context = imgProfile.context
                val textDrawable = context.createPlaceholderImage(
                    connectorUser.firstName.toString(),
                    50,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
                Glide.with(context)
                    .load(connectorUser.profilePictureUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgProfile)
            }
        }
    }

    fun addAll(list: ArrayList<SwipingProfileModel.ProfileModel>) {
        val size = profileList.size
        profileList.clear()
        notifyItemRangeRemoved(0, size)
        profileList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun getItemCount(): Int = profileList.size

}
