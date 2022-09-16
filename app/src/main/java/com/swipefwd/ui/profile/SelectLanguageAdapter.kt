package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.data.models.LanguageDataModel
import com.swipefwd.databinding.ItemInstituteListBinding
import com.swipefwd.data.models.LanguageListModel
import com.swipefwd.utils.AppUtils.asListOfType

class SelectLanguageAdapter(
    private val listener: (LanguageDataModel.LanguageData.Language) -> Unit
) :
    RecyclerView.Adapter<SelectLanguageAdapter.MyHolder>(), Filterable {
    private val languageList = ArrayList<LanguageDataModel.LanguageData.Language>()
    private var filterList: ArrayList<LanguageDataModel.LanguageData.Language> = ArrayList()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    fun addAll(list: ArrayList<LanguageDataModel.LanguageData.Language>) {
        val size = languageList.size
        languageList.clear()
        notifyItemRangeRemoved(0, size)
        languageList.addAll(list.sortedBy {
            it.value
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
                languageList
            } else {
                languageList.filter {
                    it.value!!.startsWith(p0, true)
//                    it.name!!.contains(p0, true)
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

    inner class MyHolder(val binding: ItemInstituteListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemInstituteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val language = filterList[position]
        holder.binding.apply {
            tvSchoolName.text = language.value ?: ""
            tvSchoolName.setOnClickListener {
                listener.invoke(language)
            }
        }
    }

    override fun getItemCount(): Int = filterList.size
}