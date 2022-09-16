package com.swipefwd.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemSelectCommonBinding
import com.swipefwd.data.models.CovidVaccineListModel
import com.swipefwd.data.models.VaccinationModel
import java.util.*

//class SelectVaccineStatusAdapter(private val itemClickListener: (CovidVaccineListModel.CovidModel) -> Unit) :
class SelectVaccineStatusAdapter(private val itemClickListener: (VaccinationModel.VaccinationData.VaccinationLevel) -> Unit) :
    RecyclerView.Adapter<SelectVaccineStatusAdapter.CovidHolder>() {
    class CovidHolder(
        val binding: ItemSelectCommonBinding,

        ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidHolder =
        ItemSelectCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { CovidHolder(this) }

    override fun onBindViewHolder(holder: CovidHolder, position: Int) {
        holder.binding.apply {
            imgPreference.visibility = View.GONE
            txtPreferenceName.text = arrayList[position].value
            itemClickListener.invoke(arrayList[selectedPos])

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
                    itemClickListener.invoke(arrayList[selectedPos])
                }
            }
        }

    }

    private var selectedPos = 0
  //  private val arrayList = ArrayList<CovidVaccineListModel.CovidModel>()
    private val arrayList = ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>()
    override fun getItemCount(): Int = arrayList.size
 //   fun addAll(list: ArrayList<CovidVaccineListModel.CovidModel>) {
    fun addAll(list: ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>) {
        val size = arrayList.size
        arrayList.clear()
        notifyItemRangeRemoved(0, size)
        arrayList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

  //  fun setSelected(model: CovidVaccineListModel.CovidModel) {
    fun setSelected(model: VaccinationModel.VaccinationData.VaccinationLevel) {
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
}