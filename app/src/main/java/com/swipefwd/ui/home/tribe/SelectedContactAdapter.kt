package com.swipefwd.ui.home.tribe

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectedContactListBinding
import com.swipefwd.data.models.UserContactDetails

class SelectedContactAdapter(
    val context: Context,
    private val deleteClickListener: (UserContactDetails) -> Unit
) :
    RecyclerView.Adapter<SelectedContactAdapter.SelectedContactHolder>() {

    private val contactList = ArrayList<UserContactDetails>()

    fun addAll(list: ArrayList<UserContactDetails>) {
        contactList.clear()
        contactList.addAll(list)
        notifyDataSetChanged()
    }

    class SelectedContactHolder(val binding: ItemSelectedContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedContactHolder =
        ItemSelectedContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { SelectedContactHolder(this) }

    override fun onBindViewHolder(holder: SelectedContactHolder, position: Int) {
        val contactModel = contactList[position]
        holder.binding.apply {
            txtContactName.text = contactModel.name ?: ""
            txtContactNumber.text = contactModel.phonebookNumber ?: ""
            layoutUser.showMode = SwipeLayout.ShowMode.PullOut
            layoutUser.addDrag(
                SwipeLayout.DragEdge.Right,
                layoutUser.findViewById(R.id.layoutDelete)
            )
            btnDelete.setOnClickListener {
                //remove contact from list and notify to screen
                if (layoutUser.openStatus == SwipeLayout.Status.Open) {
                    layoutUser.close()
                    deleteClickListener.invoke(contactModel)
                    contactList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int = contactList.size

}
