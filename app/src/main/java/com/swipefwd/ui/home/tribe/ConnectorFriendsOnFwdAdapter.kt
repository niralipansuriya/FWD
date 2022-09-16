package com.swipefwd.ui.home.tribe

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemConnectorFriendsFwdListBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.utils.AppUtils.createPlaceholderImage

class ConnectorFriendsOnFwdAdapter(
    val context: Context,
    private val listener: (UserContactDetails) -> Unit
) :
    RecyclerView.Adapter<ConnectorFriendsOnFwdAdapter.ContactListHolder>() {
    class ContactListHolder(val binding: ItemConnectorFriendsFwdListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder =
        ItemConnectorFriendsFwdListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
            .run { ContactListHolder(this) }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        val user = usersList[position]
        holder.binding.apply {
            txtConnect.setOnClickListener {
                listener.invoke(user)
            }
            if (user.name != "") {
                txtName.text = user.name!!.split(" ")[0]
                val textDrawable = context.createPlaceholderImage(
                    user.name,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )

                Glide.with(context)
                    .load(user.imageProfile).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgProfile)
            }
        }
    }

    private val usersList = ArrayList<UserContactDetails>()
    fun addAll(list: ArrayList<UserContactDetails>) {
        usersList.clear()
        usersList.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = usersList.size
}
