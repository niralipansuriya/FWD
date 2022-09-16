package com.swipefwd.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ItemRematchPagerBinding
import com.swipefwd.data.models.SettingSubscriptionModel
import com.swipefwd.data.models.SubscriptionPlansModel

class SubscriptionPlansPagerAdapter(
    private val context: Context
) : RecyclerView.Adapter<SubscriptionPlansPagerAdapter.PlansViewHolder>() {

    private val plansList = ArrayList<SubscriptionPlansModel>()

    fun addAll(list: ArrayList<SubscriptionPlansModel>) {
        plansList.clear()
        list.removeAt(4) // remove share friend slider
        list.removeAt(4) //remove Fire profile slider
        plansList.addAll(list)
        notifyDataSetChanged()
    }

    private var mModel = SettingSubscriptionModel.Plan()
    fun setValues(model: SettingSubscriptionModel.Plan) {
        mModel = model
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlansViewHolder {
        return PlansViewHolder(
            ItemRematchPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return plansList.size
    }

    override fun onBindViewHolder(holder: PlansViewHolder, position: Int) {
        val introSlide = plansList[position]
        holder.apply {
            binding.apply {
                txtHeader.text = introSlide.title
                Glide.with(context).load(introSlide.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivPager)
                when (mModel.planType) {
                    context.getString(R.string.elite) -> {
                        mainLayout.background =
                            ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.top_mint_corner_bottom_gradient_bg,
                                context.theme
                            )
                        txtHeader.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.colorPagerDesc,
                                context.theme
                            )
                        )
                    }
                    else -> {
                        mainLayout.background =
                            ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.top_blue_corner_gradient_bg,
                                context.theme
                            )
                        txtHeader.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.white,
                                context.theme
                            )
                        )
                    }
                }
            }
        }
    }

    inner class PlansViewHolder(val binding: ItemRematchPagerBinding) :
        RecyclerView.ViewHolder(binding.root)
}