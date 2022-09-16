package com.swipefwd.ui.home.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemPagerSubscriptionsBinding
import com.swipefwd.data.models.SettingSubscriptionModel

class SubscriptionPagerAdapter(
    val context: Context,
    private val listener: (SettingSubscriptionModel.Plan?) -> Unit
) :
    RecyclerView.Adapter<SubscriptionPagerAdapter.MyHolder>() {

    private val dataList = ArrayList<SettingSubscriptionModel.Plan?>()

    fun addAll(list: List<SettingSubscriptionModel.Plan?>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyHolder(val binding: ItemPagerSubscriptionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemPagerSubscriptionsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position] ?: SettingSubscriptionModel.Plan()
        holder.apply {
            binding.apply {
                txtPackType.text = data.planType
                btnUpgrade.setOnClickListener {
                    listener.invoke(data)
                }
                //bind adapter for plans data
                /*rvFeatures.adapter = SubscriptionPlansAdapter(context, position).apply {
                    val tripleList: ArrayList<Triple<String, String, String>> = arrayListOf()
                    if (position < dataList.size - 1) {
                        dataList[position]?.list?.onEach { pair ->
                            dataList[position + 1]?.list?.find { nextPair ->
                                pair.first == nextPair.first
                            }?.let { finalPair ->
                                tripleList.add(
                                    Triple(
                                        pair.first,
                                        pair.second,
                                        finalPair.second
                                    )
                                )
                            }
                        }
                    } else {
                        dataList[position]?.list?.onEach { pair ->
                            dataList[position - 1]?.list?.find { nextPair ->
                                pair.first == nextPair.first
                            }?.let { finalPair ->
                                tripleList.add(
                                    Triple(
                                        pair.first,
                                        finalPair.second,
                                        pair.second
                                    )
                                )
                            }
                        }
                    }
                    Log.d("TAG:", "tripleList:  $tripleList")
                    this.addAll(tripleList)
                }*/
                when (position) {
                    0 -> {
                        txtPackType.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.whiteColor,
                                context.theme
                            )
                        )
                        txDesc.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.whiteColor,
                                context.theme
                            )
                        )
                        layoutPackType.apply {
                            background = ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.corner_button_gradient_bg,
                                context.theme
                            )
                        }
                        btnUpgrade.visibility = View.INVISIBLE
                        /*txFreemium.visibility = View.VISIBLE
                        txtPremier.visibility = View.VISIBLE
                        txtElite.visibility = View.GONE*/
                    }
                    1 -> {
                        txtPackType.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.whiteColor,
                                context.theme
                            )
                        )
                        txDesc.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.whiteColor,
                                context.theme
                            )
                        )
                        layoutPackType.apply {
                            background = ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.corner_orange_gradient_bg,
                                context.theme
                            )
                        }
                        btnUpgrade.text =
                            context.getString(R.string.upgrade_account, data.planSkuPrice)
                        btnUpgrade.visibility = View.VISIBLE
                        /*txFreemium.visibility = View.GONE
                        txtPremier.visibility = View.VISIBLE
                        txtElite.visibility = View.VISIBLE*/
                    }
                    2 -> {
                        txtPackType.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.colorPagerDesc,
                                context.theme
                            )
                        )
                        txDesc.setTextColor(
                            ResourcesCompat.getColor(
                                context.resources,
                                R.color.colorPagerDesc,
                                context.theme
                            )
                        )
                        layoutPackType.apply {
                            background = ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.corner_mint_gradient_bg,
                                context.theme
                            )
                        }
                        btnUpgrade.text =
                            context.getString(R.string.upgrade_account, data.planSkuPrice)
                        btnUpgrade.visibility = View.VISIBLE
                        /*txFreemium.visibility = View.GONE
                        txtPremier.visibility = View.VISIBLE
                        txtElite.visibility = View.VISIBLE*/
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}