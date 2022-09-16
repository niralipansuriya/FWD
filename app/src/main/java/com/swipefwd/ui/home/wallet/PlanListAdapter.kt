package com.swipefwd.ui.home.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemRechargePlanListBinding
import com.swipefwd.data.models.walletModels.PlanListModel

class PlanListAdapter(private val listener: (PlanListModel) -> Unit) :
    RecyclerView.Adapter<PlanListAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemRechargePlanListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var planList = ArrayList<PlanListModel>()

    fun addAll(priceList: ArrayList<PlanListModel>) {
        val size = planList.size
        planList.clear()
        notifyItemRangeRemoved(0, size)
        planList.addAll(priceList)
        notifyItemRangeInserted(0, planList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemRechargePlanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val planModel = planList[position]
        holder.apply {
            binding.apply {
                val context = txtPrice.context
                txtPrice.text = context.getString(
                    R.string.plan_price,
                    planModel.price.split(".")[0]
                )
                txtDataValue.text = planModel.data
                txtTalkTimeValue.text = context.getString(
                    R.string.plan_price,
                    planModel.talkTime
                )
                txtValidityValue.text = planModel.validity
                txtBuy.setOnClickListener {
                    listener.invoke(planModel)
                }
            }
        }
    }

    override fun getItemCount(): Int = planList.size
}