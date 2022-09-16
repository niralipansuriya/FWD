package com.swipefwd.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemActivityHorizontalBinding
import com.swipefwd.data.models.ActiveExpireRequestListModel
import com.swipefwd.utils.AppUtils.asListOfType
import com.swipefwd.utils.AppUtils.createPlaceholderImage

class InvitesExpiredAdapter(val context: Context) :
    RecyclerView.Adapter<InvitesExpiredAdapter.MyHolder>(), Filterable {
    inner class MyHolder(val binding: ItemActivityHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var usersList = ArrayList<ActiveExpireRequestListModel.Expire>()
    private var filterList: ArrayList<ActiveExpireRequestListModel.Expire> = ArrayList()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    fun addAll(list: ArrayList<ActiveExpireRequestListModel.Expire>) {
        val size = usersList.size
        usersList.clear()
        notifyItemRangeRemoved(0, size)
        usersList.addAll(list)
        filterList = usersList
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemActivityHorizontalBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val user = filterList[position]
        holder.binding.apply {
            layoutMain.alpha = 0.5f
            if (!user.username.isNullOrEmpty()) {
                txtUserName.text = user.username.split(" ")[0]
                val textDrawable = context.createPlaceholderImage(
                    user.username,
                    50,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )

                Glide.with(context)
                    .load(user.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(img)
            }
        }
    }

    override fun getItemCount(): Int = filterList.size

    fun search(s: String?, onNothingFound: ((Boolean) -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                usersList
            } else {
                usersList.filter {
                    it.username!!.contains(p0, true)
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
}