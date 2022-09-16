package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.data.models.LanguageDataModel
import com.swipefwd.databinding.ItemSeletedLanguagesBinding
import com.swipefwd.data.models.LanguageListModel

class SelectedLanguagesAdapter(private val removeListener: (LanguageDataModel.LanguageData.Language) -> Unit) :
    RecyclerView.Adapter<SelectedLanguagesAdapter.MyHolder>() {

    private val languageList = ArrayList<LanguageDataModel.LanguageData.Language>()

    fun addLanguage(model: LanguageDataModel.LanguageData.Language) {
        languageList.add(model)
        notifyItemInserted(languageList.size + 1)
    }

    inner class MyHolder(val binding: ItemSeletedLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemSeletedLanguagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val language = languageList[position]
        holder.binding.apply {
            txtLanguage.text = language.value ?: ""
            close.setOnClickListener {
                removeListener.invoke(language)
                languageList.removeAt(position)
                notifyItemRemoved(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = languageList.size
}