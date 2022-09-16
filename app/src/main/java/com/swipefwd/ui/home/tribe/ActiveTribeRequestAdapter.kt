package com.swipefwd.ui.home.tribe

import android.content.Context
import android.view.LayoutInflater
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

class ActiveTribeRequestAdapter(
    private var mContext: Context,
    private val listener: (Boolean, Int) -> Unit,
    private val listenerSize: () -> Unit
) :
    RecyclerView.Adapter<ActiveTribeRequestAdapter.MyHolder>(), Filterable {

    private val mList = ArrayList<ActiveExpireRequestListModel.Pending>()
    private var filterList: ArrayList<ActiveExpireRequestListModel.Pending> = ArrayList()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    inner class MyHolder(val binding: ItemActiveTribesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemActiveTribesBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.apply {
            val model = filterList[position]
            if (model.username != "") {
                txtUserName.text = model.username!!.split(" ")[0]
                val textDrawable = mContext.createPlaceholderImage(
                    model.username,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )

                Glide.with(mContext)
                    .load(model.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgProfile)
            }

            if (model.isSelected) {
                ivTribe.setImageResource(R.drawable.oval_inviteselect)
            } else {
                ivTribe.setImageResource(R.drawable.oval_invite)
            }
            holder.itemView.setOnClickListener {
                model.isSelected = !model.isSelected
                notifyItemChanged(position)
                val selectedList = filterList.filter {
                    it.isSelected
                }
                if (selectedList.size >= 10 && !model.isSelected) {
                    listenerSize.invoke()
                } else {
                    listener.invoke(filterList.any { it.isSelected }, selectedList.size)
                }
            }
        }
    }

    override fun getItemCount(): Int = filterList.size

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

    fun addAll(list: ArrayList<ActiveExpireRequestListModel.Pending>) {
        mList.clear()
        mList.addAll(list)
        filterList = mList
        notifyDataSetChanged()
    }

    fun getSelectedUser() = filterList.filter {
        it.isSelected
    }
}