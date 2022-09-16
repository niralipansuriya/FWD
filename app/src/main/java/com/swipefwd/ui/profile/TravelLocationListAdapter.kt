package com.swipefwd.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemInstituteListBinding
import com.swipefwd.data.models.TravelLocationListModel

class TravelLocationListAdapter(
    private val context: Context,
    private val listener: (TravelLocationListModel) -> Unit
) :
    RecyclerView.Adapter<TravelLocationListAdapter.MyHolder>() {
    private val placesList = ArrayList<TravelLocationListModel>()

    fun addAll(list: ArrayList<TravelLocationListModel>) {
        val size = placesList.size
        placesList.clear()
        notifyItemRangeRemoved(0, size)
        placesList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    inner class MyHolder(val binding: ItemInstituteListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemInstituteListBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val institute = placesList[position]
        holder.apply {
            binding.tvSchoolName.text =
                context.getString(R.string.city_country, institute.city, institute.country)
            itemView.setOnClickListener {
                listener.invoke(institute)
            }
        }
    }

    override fun getItemCount(): Int = placesList.size
}