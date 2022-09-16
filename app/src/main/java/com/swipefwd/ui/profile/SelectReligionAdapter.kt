package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.ReligionListModel
import com.swipefwd.data.models.ReligionModel

class SelectReligionAdapter :
    RecyclerView.Adapter<SelectReligionAdapter.PreferenceHolder>() {
    class PreferenceHolder(
        val binding: ItemSelectCommonBinding,

        ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceHolder =
        ItemSelectCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { PreferenceHolder(this) }

    override fun onBindViewHolder(holder: PreferenceHolder, position: Int) {
        holder.binding.apply {
            imgPreference.visibility = View.GONE
            txtPreferenceName.text = arrayList[position].value
            val mContext = txtPreferenceName.context
            if (selectedPos == position) {
                layoutPreference.setBackgroundResource(R.drawable.astro_gradient_enable_selector)
                txtPreferenceName.setTextColor(
                    ResourcesCompat.getColor(
                        mContext.resources,
                        R.color.whiteColor,
                        mContext.theme
                    )
                )
            } else {
                layoutPreference.setBackgroundResource(R.drawable.grey_border_bg)
                txtPreferenceName.setTextColor(
                    ResourcesCompat.getColor(
                        mContext.resources,
                        R.color.colorPagerDesc,
                        mContext.theme
                    )
                )
            }
            layoutPreference.setOnClickListener {
                if (selectedPos != position) {
                    val oldPosition = selectedPos
                    selectedPos = position
                    notifyItemChanged(selectedPos)
                    notifyItemChanged(oldPosition)
                }
            }
        }

    }

    private var selectedPos = 0
   // private val arrayList = ArrayList<ReligionListModel.ReligionModel>()
    private val arrayList = ArrayList<ReligionModel.ReligionData.ReligionLevel>()
    override fun getItemCount(): Int = arrayList.size
  //  fun addAll(list: ArrayList<ReligionListModel.ReligionModel>) {
    fun addAll(list: ArrayList<ReligionModel.ReligionData.ReligionLevel>) {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
//        list.last().name = "Prefer not to say"
        arrayList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun setSelected(model: ReligionModel.ReligionData.ReligionLevel) {
        arrayList.find {
            it._id == model._id
        }?.let {
            val pos = arrayList.indexOf(it)
            if (selectedPos != pos) {
                val oldPosition = selectedPos
                selectedPos = pos
                notifyItemChanged(selectedPos)
                notifyItemChanged(oldPosition)
            }
        }
    }

    fun selectedReligion() = if (arrayList.size!=0) arrayList[selectedPos] else null
}