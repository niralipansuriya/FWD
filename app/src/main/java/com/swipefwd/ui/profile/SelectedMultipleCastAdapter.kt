package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.databinding.ItemSeletedLanguagesBinding
import com.swipefwd.data.models.CastListModel

class SelectedMultipleCastAdapter(private val removeListener: (CastListModel.CastModel) -> Unit) :
    RecyclerView.Adapter<SelectedMultipleCastAdapter.MyHolder>() {

    private val castList = ArrayList<CastListModel.CastModel>()

    fun addLanguage(model: CastListModel.CastModel) {
        castList.add(model)
        notifyDataSetChanged()
    }

    inner class MyHolder(val binding: ItemSeletedLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemSeletedLanguagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val language = castList[position]
        holder.binding.apply {
            txtLanguage.text = language.name ?: ""
            close.setOnClickListener {
                removeListener.invoke(language)
                castList.removeAt(position)
                notifyItemRemoved(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = castList.size
}