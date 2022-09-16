package com.swipefwd.ui.home.tribe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemFriendsFwdListBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppConstants

class FriendsOnFwdAdapter(
    val context: Context,
    private val inviteListener: (UserContactDetails) -> Unit
) :
    RecyclerView.Adapter<FriendsOnFwdAdapter.ContactListHolder>() {
    var myHolder: ContactListHolder? = null

    class ContactListHolder(val binding: ItemFriendsFwdListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder =
        ItemFriendsFwdListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ContactListHolder(this) }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        val user = usersList[position]
        myHolder = holder
        holder.binding.apply {
            if (user.isInvited || user.invitedStage == AppConstants.STAGE_PENDING) {
                txtAddTribe.visibility = View.INVISIBLE
                txtAddedTribe.visibility = View.VISIBLE
            } else {
                txtAddTribe.visibility = View.VISIBLE
                txtAddedTribe.visibility = View.GONE
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
            txtAddTribe.setOnClickListener {
                inviteListener.invoke(usersList[position])
                notifyItemChanged(position)
            }
        }
    }

    fun showAdded(isSuccess: Boolean) {
        myHolder?.let {
            if (isSuccess) {
                it.binding.apply {
                    txtAddTribe.visibility = View.INVISIBLE
                    txtAddedTribe.visibility = View.VISIBLE
                }
            }
        }
    }

    private val usersList = ArrayList<UserContactDetails>()

    fun addAll(list: ArrayList<UserContactDetails>) {
        val size = usersList.size
        usersList.clear()
        notifyItemRangeRemoved(0, size)
        usersList.addAll(list.filter {
            it.invitedStage != "completed" //not showing those users who are completed (In tribe)
        })
        notifyItemRangeInserted(0, list.size)
    }

    override fun getItemCount(): Int = usersList.size
}
