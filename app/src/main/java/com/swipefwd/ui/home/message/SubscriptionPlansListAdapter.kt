package com.swipefwd.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemRematchPlansBinding
import com.swipefwd.data.models.AllSubPlansModel
import com.swipefwd.data.models.SettingSubscriptionModel

class SubscriptionPlansListAdapter(
    val context: Context,
    private val listener: () -> Unit,
) :
    RecyclerView.Adapter<SubscriptionPlansListAdapter.MyHolder>() {

    var selectedPosition = 0
    private var subPlansList = ArrayList<AllSubPlansModel>()
    fun addAll(list: ArrayList<AllSubPlansModel>) {
        subPlansList.clear()
        when (mModel.planType) {
            context.getString(R.string.elite) -> {
                list.filter {
                    it.title.split(" ")[0] == context.getString(R.string.elite)
                }.let {
                    subPlansList.addAll(it.sortedBy { model ->
                        model.description
                    }
                    )
                }
            }
            else -> {
                list.filter {
                    it.title.split(" ")[0] == context.getString(R.string.premier)
                }.let {
                    subPlansList.addAll(it.sortedBy { model ->
                        model.description
                    }
                    )
                }
            }
        }
        notifyDataSetChanged()
    }

    private var mModel = SettingSubscriptionModel.Plan()
    fun setValues(model: SettingSubscriptionModel.Plan) {
        mModel = model
        notifyDataSetChanged()
    }


    inner class MyHolder(val binding: ItemRematchPlansBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemRematchPlansBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val plan = subPlansList[position]
        holder.binding.apply {
            txtPlanMonth.text = plan.description
            txtPlanValue.text = plan.priceValue
            val white = ContextCompat.getColor(context, R.color.white)
            val gray = ContextCompat.getColor(context, R.color.colorPagerDesc)
            when (position) {
                2 -> {
                    when (mModel.planType) {
                        context.getString(R.string.elite) -> {
                            txtSavePlan.apply {
                                background =
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.profile_progress_bg
                                    )
                            }
                        }
                        else -> {
                            txtSavePlan.apply {
                                background =
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.corner_mint_bottom_gradient_bg
                                    )
                            }
                        }
                    }
                    txtSavePlan.visibility = View.VISIBLE
                    txtPlanMonthOffer.visibility = View.GONE
                }
                else -> {
                    txtSavePlan.visibility = View.GONE
                    txtPlanMonthOffer.visibility = View.VISIBLE
                }
            }
            if (selectedPosition == position) {
                when (mModel.planType) {
                    context.getString(R.string.elite) -> {
                        txtPlanMonthOffer.setTextColor(gray)
                        txtPlanMonth.setTextColor(gray)
                        txtPlanValue.setTextColor(gray)

                        layoutItemPlans.background =
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.corner_mint_bottom_gradient_bg
                            )

                    }
                    else -> {
                        txtPlanMonthOffer.setTextColor(white)
                        txtPlanMonth.setTextColor(white)
                        txtPlanValue.setTextColor(white)

                        layoutItemPlans.background =
                            ContextCompat.getDrawable(context, R.drawable.profile_progress_bg)
                    }
                }
            } else {
                txtPlanMonthOffer.setTextColor(gray)
                txtPlanMonth.setTextColor(gray)
                txtPlanValue.setTextColor(gray)
                layoutItemPlans.background =
                    ContextCompat.getDrawable(context, R.drawable.grey_border_bg)
            }
            layoutItemPlans.setOnClickListener {
                val pos = selectedPosition
                if (selectedPosition != position) {
                    selectedPosition = position
                    notifyItemChanged(pos)
                    notifyItemChanged(position)
                    listener.invoke()
                }
            }
        }
    }

    override fun getItemCount(): Int = subPlansList.size
}