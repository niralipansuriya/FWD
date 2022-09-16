package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemInstituteListBinding
import com.swipefwd.data.models.CastListModel
import com.swipefwd.utils.AppUtils.asListOfType
import java.util.*

class SelectCastAdapter(private val itemClickListener: (CastListModel.CastModel) -> Unit) :
    RecyclerView.Adapter<SelectCastAdapter.CastHolder>(), Filterable {
    class CastHolder(
        val binding: ItemInstituteListBinding,

        ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder =
        ItemInstituteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { CastHolder(this) }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.binding.apply {
            tvSchoolName.text = filterList[position].name
            tvSchoolName.setOnClickListener {
                itemClickListener.invoke(filterList[position])
            }
        }
    }

    private val arrayList = ArrayList<CastListModel.CastModel>()
    private var filterList = ArrayList<CastListModel.CastModel>()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    init {
        filterList = arrayList
    }

    override fun getItemCount(): Int = filterList.size
    fun addAll(list: ArrayList<CastListModel.CastModel>) {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
        arrayList.addAll(list.sortedBy {
            it.name
        })
        notifyItemRangeInserted(0, list.size)
    }

    fun search(s: String?, onNothingFound: ((Boolean) -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                arrayList
            } else {
                arrayList.filter {
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
}