package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.data.models.SmokingDataModel
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.SmokingListModel
import java.util.*

class SelectSmokingAdapter :
    RecyclerView.Adapter<SelectSmokingAdapter.PreferenceHolder>() {
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

    var selectedPos = 0
   // private val arrayList = ArrayList<SmokingListModel.SmokingModel>()
    private val arrayList = ArrayList<SmokingDataModel.SmokingData.Smoking>()

    override fun getItemCount(): Int = arrayList.size

    //fun addAll(list: ArrayList<SmokingListModel.SmokingModel>) {
    fun addAll(list: ArrayList<SmokingDataModel.SmokingData.Smoking>) {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
        arrayList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun setSelected(model: SmokingDataModel.SmokingData.Smoking) {
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

    fun selectedSmoking() = if (arrayList.size!=0) arrayList[selectedPos] else null
}