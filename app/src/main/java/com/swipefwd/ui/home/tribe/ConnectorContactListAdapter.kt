package com.swipefwd.ui.home.tribe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemConnectorContactListBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.utils.AppUtils.asListOfType

class ConnectorContactListAdapter(
    private val listener: (UserContactDetails) -> Unit
) :
    RecyclerView.Adapter<ConnectorContactListAdapter.ContactListHolder>(),
    Filterable {

    private val contactList: ArrayList<UserContactDetails> = ArrayList()
    private var filterList: ArrayList<UserContactDetails> = ArrayList()
    private var onNothingFound: ((Boolean) -> Unit)? = null
    private var selectedPos = -1

    class ContactListHolder(val binding: ItemConnectorContactListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder =
        ItemConnectorContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ContactListHolder(this) }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        try {
            val filter = filterList[position]
            holder.binding.apply {
                val context = txtInvite.context
                if (selectedPos == position) {
                    cbContactName.setImageResource(R.drawable.oval_inviteselect)
                } else {
                    cbContactName.setImageResource(R.drawable.oval_invite)
                }
                when (filter.numberType) {
                    "0" -> {
                        txtInvite.text = context.getString(R.string.telephone)
                    }
                    "1" -> {
                        txtInvite.text = context.getString(R.string.home)
                    }
                    "2" -> {
                        txtInvite.text = context.getString(R.string.mobile)
                    }
                }
                txtContactName.text = filter.name
                txtContactNumber.text = filter.phonebookNumber
                holder.itemView.setOnClickListener {
                    if (selectedPos != position) {
                        val oldPosition = selectedPos
                        selectedPos = position
                        notifyItemChanged(selectedPos)
                        notifyItemChanged(oldPosition)
                        listener.invoke(filterList[selectedPos])
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun search(s: String?, onNothingFound: ((Boolean) -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                contactList
            } else {
                contactList.filter {
                    it.name!!.contains(p0, true)
                }.toCollection(ArrayList())
            }
            return filterResult
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            val values = p1?.values
            if (values is ArrayList<*>) {
                filterList = values.asListOfType()
                notifyDataSetChanged()
            }
            onNothingFound?.invoke(filterList.isNullOrEmpty())
        }
    }

    fun addAll(list: ArrayList<UserContactDetails>) {
        contactList.clear()
        list.sortBy { it.name }
        contactList.addAll(list)
        contactList.sortBy { !it.isInvited } //showing Re-invited User at first
        filterList = contactList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filterList.size
}
