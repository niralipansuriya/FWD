package com.swipefwd.ui.home.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemSubscriptionPlansBinding
import com.swipefwd.utils.AppUtils.setShaderView

class SubscriptionPlansAdapter(val context: Context, val mPosition: Int) :
    RecyclerView.Adapter<SubscriptionPlansAdapter.MyHolder>() {

    private val plansList = ArrayList<Triple<String, String, String>>()

    fun addAll(list: ArrayList<Triple<String, String, String>>) {
        plansList.clear()
        list.removeAt(0) //need to remove Connection row(as per KI)
        list.removeAt(2) //need to remove Fire profile row(as per KI)
        plansList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyHolder(val binding: ItemSubscriptionPlansBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemSubscriptionPlansBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val plan = plansList[position]
        holder.apply {
            binding.apply {
                txtDesc.text = plan.first
                //txtFreemiumOffer.text = plan.second
                //txtPremierOffer.text = plan.third
                when (mPosition) { //0: Freemium, 1: Premier, 2: Elite
                    0 -> {
                        txtFreemiumOffer.apply {
                            setTextColor(
                                ResourcesCompat.getColor(
                                    context.resources,
                                    R.color.colorPagerDesc,
                                    context.theme
                                )
                            )
                            text = if (plan.second.contains("trial", true)) {
                                plan.second.split("x")[1]
                            } else {
                                plan.second
                            }
                        }
                        txtPremierOffer.apply {
                            context.setShaderView(
                                txtPremierOffer,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )
                            text = if (plan.third.contains("trial", true)) {
                                plan.third.split("x")[1]
                            } else {
                                plan.third
                            }
                        }
                    }
                    else -> {
                        txtFreemiumOffer.apply {
                            context.setShaderView(
                                txtFreemiumOffer,
                                R.color.yellow_gradient_1,
                                R.color.yellow_gradient_3
                            )
                            text = if (plan.second.contains("trial", true)) {
                                plan.second.split("x")[1]
                            } else {
                                plan.second
                            }
                        }
                        txtPremierOffer.apply {
                            context.setShaderView(
                                txtPremierOffer,
                                R.color.mint_gradient2,
                                R.color.mint_gradient1
                            )
                            text = if (plan.third.contains("trial", true)) {
                                plan.third.split("x")[1]
                            } else {
                                plan.third
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = plansList.size
}