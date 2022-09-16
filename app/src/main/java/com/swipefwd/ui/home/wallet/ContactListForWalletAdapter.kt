package com.swipefwd.ui.home.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemContactFromWalletsBinding
import com.swipefwd.data.models.UserContactDetails

class ContactListForWalletAdapter(
    val context: Context,
    private val mList: ArrayList<UserContactDetails>,
    private val listener: (Int) -> Unit,
    private val onClickListener: (UserContactDetails) -> Unit
) :
    RecyclerView.Adapter<ContactListForWalletAdapter.ContactListHolder>(){

    private var selectedPosition = -1

    class ContactListHolder(val binding: ItemContactFromWalletsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(details: UserContactDetails) {
            try {
                binding.txtName.text = details.name

                val strArray = details.name?.split(" ")
                val builder = StringBuilder()
                if (strArray!!.isNotEmpty()) {
                    builder.append(strArray[0], 0, 1)
                }
                if (strArray.size > 1) {
                    builder.append(strArray[1], 0, 1)
                }
                binding.txtLetter.text = builder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder =
        ItemContactFromWalletsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ContactListHolder(this) }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        holder.binding.apply {
            try {
                holder.bind(mList[position])
                layoutUserName.setOnClickListener {
                    onClickListener.invoke(mList[position])
                    val pos = selectedPosition
                    if (selectedPosition != position) {
                        selectedPosition = position
                        notifyItemChanged(pos)
                        notifyItemChanged(position)
                        listener.invoke(position)
                    }
                }
                if (selectedPosition == position) {
                    ivCheck.visibility = View.VISIBLE
                } else {
                    ivCheck.visibility = View.INVISIBLE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int = mList.size
}
