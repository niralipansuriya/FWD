package com.swipefwd.ui.home.tribe

import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemContactListBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.utils.AppUtils.asListOfType
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.setShaderView

class ContactListAdapter(
    private val isoCode: String,
    private val listener: (UserContactDetails, Int) -> Unit,
    private var onNothingFound: (Boolean) -> Unit
) :
    RecyclerView.Adapter<ContactListAdapter.ContactListHolder>(),
    Filterable {

    private val contactList: ArrayList<UserContactDetails> = ArrayList()
    private var filterList: ArrayList<UserContactDetails> = ArrayList()
    var myHolder: ContactListHolder? = null

    class ContactListHolder(val binding: ItemContactListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder =
        ItemContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ContactListHolder(this) }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        myHolder = holder
        try {
            val filter = filterList[position]
            holder.binding.apply {
                if(position==0){
                    layoutUserDetails.setPadding(0,15.dpToPx(),15.dpToPx(),30.dpToPx())
                }
                val context = txtInvite.context
                txtContactName.text = filter.name
                var countryPhone = ""
                if(filter.simpleNumber!=null && filter.simpleNumber!!.length>=6) {
                    println("null check======> ${filter.simpleNumber}")
                    countryPhone = if (filter.simpleNumber!!.startsWith("+", false)) {
                        PhoneNumberUtils.formatNumber(filter.simpleNumber, isoCode)
                            .replace(" ", "-")
                    } else if (filter.simpleNumber!!.startsWith("0", false)) {
                        PhoneNumberUtils.formatNumber(
                            "$isoCode${filter.simpleNumber!!.removeRange(0,1)}",
                            isoCode
                        ).replace(" ", "-")
                    } else {
                        PhoneNumberUtils.formatNumber(
                            "$isoCode${filter.simpleNumber}",
                            isoCode
                        ).replace(" ", "-")
                    }
                    txtContactNumber.text = countryPhone//filter.phonebookNumber
                }
                if (filter.invitedStage == "expired") {
                    txtReinvite.apply {
                        visibility = View.VISIBLE
                    }
                    context.setShaderView(
                        txtReinvite,
                        R.color.gradient_pink_1,
                        R.color.gradient_pink_2
                    )
                }  else if (filter.invitedStage == "pending") {
                    if (filter.reinvited) {
                        txtReinvite.visibility = View.GONE
                        txtReinvited.visibility = View.VISIBLE
                    } else {
                        txtInvite.visibility = View.GONE
                        txtAddedTribe.visibility = View.VISIBLE
                    }
                } else {
                    txtInvite.apply {
                        visibility = View.VISIBLE
                    }
                    txtAddedTribe.visibility = View.GONE
                    context.setShaderView(
                        txtInvite,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                }

                if (filter.friendsOnFwd != 0) {
                txtFriendsOnFwd.apply {
                    visibility = View.VISIBLE
                    text = context.getString(
                        R.string.number_friend_on_fwd, filter.friendsOnFwd.toString()
                    )
                }
                context.setShaderView(
                    txtFriendsOnFwd,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
            }
                /*if (filter.isInvited) {
                    txtReinvite.apply {
                        visibility = View.VISIBLE
                    }
                    context.setShaderView(
                        txtReinvite,
                        R.color.gradient_pink_1,
                        R.color.gradient_pink_2
                    )
                } else {
                    if (filter.invitedStage == "pending" && filter.user_id == 0) {
                        if (filter.reinvited) {
                            txtReinvite.visibility = View.GONE
                            txtReinvited.visibility = View.VISIBLE
                        } else {
                            txtInvite.visibility = View.GONE
                            txtAddedTribe.visibility = View.VISIBLE
                        }
                    } else {
                        txtInvite.apply {
                            visibility = View.VISIBLE
                        }
                        txtAddedTribe.visibility = View.GONE
                        context.setShaderView(
                            txtInvite,
                            R.color.blue_gradient_2,
                            R.color.blue_gradient_3
                        )
                    }
                }*/

                txtInvite.setOnClickListener {
//                    listener.invoke(filterList[position], 0)
//                    notifyItemChanged(position)
                }
                txtReinvite.setOnClickListener {
                    //txtReinvited.visibility = View.VISIBLE
                    //txtReinvite.visibility = View.GONE
                    //txtInvite.performClick()
                    listener.invoke(filterList[position], 1)
                    notifyItemChanged(position)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun search(s: String?) {
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                contactList
            } else {
                contactList.filter {
                    it.name!!.startsWith(p0, true) ||
                            it.simpleNumber!!.contains(p0, true)
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
            onNothingFound.invoke(filterList.isNullOrEmpty())
        }
    }

    fun addAll(list: ArrayList<UserContactDetails>) {
        contactList.clear()
        list.sortBy { it.name }
        list.filter {
            /*it.invitedStage != "pending" && */!it.simpleNumber.isNullOrEmpty()
        }.toCollection(arrayListOf()).let {
            /*val mList = ArrayList<UserContactDetails>()
            it.filter { userModel->
                userModel.invitedStage == "expired"
            }.apply {
                mList.addAll(this)
            }
            it.filter { userModel->
                userModel.invitedStage == "pending" && userModel.reinvited
            }.apply {
                mList.addAll(this)
            }
            it.filter { userModel->
                userModel.invitedStage == "pending" && userModel.isInvited && !userModel.reinvited
            }.apply {
                mList.addAll(this)
            }
            it.removeAll(mList)
            mList.addAll(it)*/
            contactList.addAll(it)
            /*it.sortedBy { model ->
                model.invitedStage.equals("pending")
            }.apply {
                this.sortedBy { userModel ->
                    userModel.invitedStage.equals("expired")
                }.apply {
                    contactList.addAll(this)
                }
            }*/
        }
        filterList = contactList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filterList.size

    fun showAdded(isReInvite: Int, isSuccess: Boolean) {
        myHolder?.let {
            if (isSuccess) {
                it.binding.apply {
                    when (isReInvite) {
                        0 -> { //for normal invite
                            txtInvite.visibility = View.GONE
                            txtAddedTribe.visibility = View.VISIBLE
                        }
                        1 -> {//for reInvite
                            txtReinvite.visibility = View.GONE
                            txtReinvited.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}
