package com.swipefwd.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.AstroSignListModel
import com.swipefwd.data.models.AstrologicalModel
import java.util.*

class SelectMultipleAstroSignAdapter(
    private val mContext: Context, val listener: (Boolean) -> Unit
) :
    RecyclerView.Adapter<SelectMultipleAstroSignAdapter.SignHolder>() {


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

            if (sign.isSelected) {
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
                if (signList[position].isSelected) {
                    signList[position].isSelected = false
                    listener.invoke(false)

                } else {
                    signList[position].isSelected = true
                }
                //signList[position].isSelected = !signList[position].isSelected
                notifyDataSetChanged()
            }
            var count = 0
            signList.forEach {
                if (it.isSelected) {
                    count += 1
                }
            }
            if (count == signList.size) {
                listener.invoke(true)
            }
        }

    }

    private val signList = ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>()
    override fun getItemCount(): Int = signList.size
    fun addAll(signs: List<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>) {
        val size = signList.size
        signList.clear()
        notifyItemRangeRemoved(0, size)
        signList.addAll(signs)
        notifyItemRangeInserted(0, signs.size)
    }

    fun allSigns() = signList

    fun setSelected(modelList: ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>) {
        signList.onEach { eachModel ->
            modelList.find { findModel ->
                findModel._id == eachModel._id
            }?.let {
                eachModel.isSelected = it.isSelected
            }
        }
        notifyDataSetChanged()
    }

    fun selectAll(select: Boolean) {
        signList.forEach {
            it.isSelected = select
        }
        notifyDataSetChanged()

    }
}