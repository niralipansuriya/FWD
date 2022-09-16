package com.swipefwd.ui.profile

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.data.models.EducationDetailModel
import com.swipefwd.databinding.ItemInstituteListBinding
import com.swipefwd.data.models.EducationLevelListModel
import com.swipefwd.utils.AppConstants

class LevelAdapter(private val context: Context, private val listener: (EducationDetailModel.DataEducatiton.EducationData) -> Unit) :
    RecyclerView.Adapter<LevelAdapter.MyHolder>() {
    private val levelList = ArrayList<EducationDetailModel.DataEducatiton.EducationData>()
    var view : View? = null

    fun addAll(list: ArrayList<EducationDetailModel.DataEducatiton.EducationData>, screen:String) {
        val size = levelList.size
        levelList.clear()
        notifyItemRangeRemoved(0, size)
        if((screen==AppConstants.SCREEN_PROFILE)) {
            list.remove(EducationDetailModel.DataEducatiton.EducationData(19,"All education levels"))
        }
//        if(list[0].name!="All education levels")
//            list.add(0,EducationLevelListModel.LevelModel(null,"All education levels"))
        levelList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    inner class MyHolder(val binding: ItemInstituteListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemInstituteListBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val institute = levelList[position]
        holder.apply {
            binding.tvSchoolName.text = institute.education_level ?: ""
//            if(!institute.name.equals("All education levels")) {
                itemView.setOnClickListener {
                    listener.invoke(institute)
                    if (view != null) {
                        view!!.setBackgroundColor(Color.TRANSPARENT)
                       ( view!! as TextView).setTextColor(context.getResources().getColor(R.color.colorPagerDesc))
                    }
                    view = itemView
                    itemView.setBackgroundResource(R.color.dropdown_selection)
                    (itemView as TextView).setTextColor(context.getResources().getColor(R.color.white))
                }
                itemView.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        v.setBackgroundResource(R.color.dropdown_selection)
                        (v as TextView).setTextColor(context.getResources().getColor(R.color.white))
                    }
                    if (event.action === MotionEvent.ACTION_UP || event.action === MotionEvent.ACTION_CANCEL) {
                        v.setBackgroundColor(Color.TRANSPARENT)
                        (v as TextView).setTextColor(context.getResources().getColor(R.color.colorPagerDesc))
                    }
                    false
                }
//            }
        }
    }

    override fun getItemCount(): Int = levelList.size
}