package com.swipefwd.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.ReligionListModel
import com.swipefwd.data.models.ReligionModel
import java.util.*

class SelectMultipleReligionAdapter(
    private val mContext: Context,
    val listener: (Boolean) -> Unit
) :
    RecyclerView.Adapter<SelectMultipleReligionAdapter.PreferenceHolder>() {
    class PreferenceHolder(
        val binding: ItemSelectCommonBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceHolder =
        ItemSelectCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { PreferenceHolder(this) }

    override fun onBindViewHolder(holder: PreferenceHolder, position: Int) {
        holder.binding.apply {
            val religion = arrayList[position]
            imgPreference.visibility = View.GONE
            txtPreferenceName.text = religion.value

            if (religion.isSelected) {
                layoutPreference.setBackgroundResource(R.drawable.astro_gradient_enable_selector)
                txtPreferenceName.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                imgPreference.setColorFilter(ContextCompat.getColor(mContext, R.color.white));
            } else {
                txtPreferenceName.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                imgPreference.setColorFilter(
                    ContextCompat.getColor(
                        mContext,
                        R.color.blue_gradient_2
                    )
                );
                layoutPreference.setBackgroundResource(R.drawable.grey_border_bg)
            }

            layoutPreference.setOnClickListener {
                if (position == arrayList.size - 1) {
                    arrayList.onEach { model ->
                        model.isSelected = false
                    }
                    arrayList[position].isSelected = !arrayList[position].isSelected
                } else {
                    arrayList.last().isSelected = false
                    religion.isSelected = !religion.isSelected
                    listener.invoke(false)
                }
               /* if (arrayList[position].isSelected) {
                    arrayList[position].isSelected = false
                    listener.invoke(false)

                } else {
                    arrayList[position].isSelected = true
                }*/
                notifyDataSetChanged()
            }
            var count = 0
            arrayList.take(arrayList.size - 1).forEach {
                if (it.isSelected) {
                    count += 1
                }
            }
            if (count == arrayList.size - 1) {
                listener.invoke(true)
            }
        }

    }

    //private val arrayList = ArrayList<ReligionListModel.ReligionModel>()
    private val arrayList = ArrayList<ReligionModel.ReligionData.ReligionLevel>()
    override fun getItemCount(): Int = arrayList.size
   // fun addAll(list: ArrayList<ReligionListModel.ReligionModel>) {
    fun addAll(list: ArrayList<ReligionModel.ReligionData.ReligionLevel>) {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
        list.last().value = "No preference"
        arrayList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun allReligions() = if (arrayList.size!=0) arrayList else null

    fun setSelected(modelList: ArrayList<ReligionModel.ReligionData.ReligionLevel>) {
        arrayList.onEach { eachModel ->
            modelList.find { findModel ->
                findModel._id == eachModel._id
            }?.let {
                eachModel.isSelected = it.isSelected
            }
        }
        notifyDataSetChanged()
    }

    fun selectAll(select: Boolean) {
        if (!arrayList.isNullOrEmpty()) {
            arrayList.take(arrayList.size - 1).forEach {
                it.isSelected = select
            }
            arrayList.last().isSelected = !select
            notifyDataSetChanged()
        }
    }
}