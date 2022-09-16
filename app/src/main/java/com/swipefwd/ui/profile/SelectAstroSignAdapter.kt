package com.swipefwd.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.AstroSignListModel
import com.swipefwd.data.models.AstrologicalModel
import java.util.*
import kotlin.collections.ArrayList

class SelectAstroSignAdapter(
    private val mContext: Context
) :
    RecyclerView.Adapter<SelectAstroSignAdapter.SignHolder>() {

    class SignHolder(val binding: ItemSelectCommonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignHolder =
        ItemSelectCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { SignHolder(this) }

    override fun onBindViewHolder(holder: SignHolder, position: Int) {
        val sign = signList[position]
        holder.binding.apply {
            txtPreferenceName.text = sign.value
            if (sign.logo?.trim() != "" && sign.logo != null) {
                imgPreference.visibility = View.VISIBLE
                Glide
                    .with(mContext)
                    .load(sign.logo)
                    .centerCrop()
                    .into(imgPreference)
            } else {
                imgPreference.visibility = View.GONE
            }

            if (selectedPos == position) {
                layoutPreference.setBackgroundResource(R.drawable.astro_gradient_enable_selector)
                txtPreferenceName.setTextColor(
                    ResourcesCompat.getColor(
                        mContext.resources,
                        R.color.whiteColor,
                        mContext.theme
                    )
                )
                imgPreference.setColorFilter(ContextCompat.getColor(mContext, R.color.white))
            } else {
                layoutPreference.setBackgroundResource(R.drawable.grey_border_bg)
                txtPreferenceName.setTextColor(
                    ResourcesCompat.getColor(
                        mContext.resources,
                        R.color.colorPagerDesc,
                        mContext.theme
                    )
                )
                imgPreference.setColorFilter(
                    ContextCompat.getColor(
                        mContext,
                        R.color.blue_gradient_2
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
    private val signList = ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>()
    override fun getItemCount(): Int = signList.size
    fun addAll(signs: List<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>) {
        val size = signList.size
        signList.clear()
        notifyItemRangeRemoved(0, size)
        signList.addAll(signs)
        notifyItemRangeInserted(0, signs.size)
    }

    fun setSelected(model: AstrologicalModel.AstrologicalData.AstrologicalSignLevel) {
        signList.find {
            it._id == model._id
        }?.let {
            val pos = signList.indexOf(it)
            if (selectedPos != pos) {
                val oldPosition = selectedPos
                selectedPos = pos
                notifyItemChanged(selectedPos)
                notifyItemChanged(oldPosition)
            }
        }
    }

    fun selectedSign() = if (signList.size!=0) signList[selectedPos] else null
}