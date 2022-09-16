package com.swipefwd.ui.home.tribe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemActiveTribesBinding
import com.swipefwd.data.models.ActiveExpireRequestListModel
import com.swipefwd.utils.AppUtils.asListOfType
import com.swipefwd.utils.AppUtils.createPlaceholderImage

class ExpiredTribeRequestAdapter : RecyclerView.Adapter<ExpiredTribeRequestAdapter.MyHolder>(),
    Filterable {

    private val mList = ArrayList<ActiveExpireRequestListModel.Expire>()
    private var filterList: ArrayList<ActiveExpireRequestListModel.Expire> = ArrayList()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    inner class MyHolder(val binding: ItemActiveTribesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemActiveTribesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.apply {
            val model = filterList[position]
            if (model.username != "") {
                txtUserName.text = model.username!!.split(" ")[0]
                val textDrawable = imgProfile.context.createPlaceholderImage(
                    model.username,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )

                Glide.with(imgProfile.context)
                    .load(model.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgProfile)
            }

            ivTribe.visibility = View.GONE
            layoutUserDetails.apply {
                alpha = 0.5f
                isClickable = false
                isFocusable = false
            }
        }
    }

    override fun getItemCount(): Int = filterList.size

    fun addAll(list: ArrayList<ActiveExpireRequestListModel.Expire>) {
        val size = mList.size
        mList.clear()
        notifyItemRangeRemoved(0, size)
        filterList.addAll(list.filter {
            it.username!!.isNotEmpty()
        })
        notifyItemRangeInserted(0, filterList.size)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                mList
            } else {
                mList.filter {
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

    fun search(s: String?, onNothingFound: ((Boolean) -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }
}