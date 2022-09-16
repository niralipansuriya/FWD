package com.swipefwd.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.countrypicker.Country
import com.swipefwd.databinding.ItemCountryCodeBinding
import com.swipefwd.utils.AppUtils.asListOfType


class CountryAdapter(
    val context: Context,
    private val countryList: ArrayList<Country>,
    private val topCountryList: ArrayList<Country>,
    private val countryListWithoutTop: ArrayList<Country>,
    private val listener: (Country) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.MyHolder>(), Filterable {
    private var filterList = ArrayList<Country>()
    private var onNothingFound: ((Boolean) -> Unit)? = null

    inner class MyHolder(val binding: ItemCountryCodeBinding) :
        RecyclerView.ViewHolder(binding.root)

    init {
        filterList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemCountryCodeBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val country = filterList[position]
        holder.apply {
            binding.apply {
                imgFlag.setImageResource(country.flag)
                txtCountry.text = country.name
                txtCode.text = country.dialCode

                if (position == 2)
                  viewDivider.visibility = View.VISIBLE
                else
                  viewDivider.visibility = View.GONE
            }
            binding.layoutMain.setOnClickListener {
                listener.invoke(country)
            }
        }
    }

    override fun getItemCount(): Int = filterList.size
    fun search(s: String?, onNothingFound: ((Boolean) -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            filterResult.values = if (p0.isNullOrEmpty()) {
                countryList
            } else {
                countryList.filter {
                    it.name.contains(p0, true) || it.dialCode.startsWith("+$p0",true)
                }.toCollection(ArrayList())
            }
            return filterResult
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            val values = p1?.values
            if (values is ArrayList<*>) {
                val finalArray : ArrayList<Country> = ArrayList<Country>()
                val tempArray : ArrayList<Country> = values.asListOfType()
                val restArray : ArrayList<Country> = ArrayList<Country>()
                finalArray.addAll(topCountryList)
                for (i in 0 until tempArray.size) {
                    if (tempArray[i].dialCode == "+91" || tempArray[i].dialCode == "+86" || tempArray[i].dialCode == "+44" )  { }
                    else{
                        restArray.add(tempArray[i])
                    }
                }
                finalArray.addAll(restArray)
                //  tempArray.addAll(topCountryList)

               /* var newTempArray : ArrayList<Country> = ArrayList<Country>()
                var finalTempArray : ArrayList<Country> = ArrayList<Country>()
                for (i in 0 until tempArray.size) {
                   if (newTempArray.size != 0)  {
                       for (j in 0 until newTempArray.size) {
                           if (tempArray[i].dialCode !=  newTempArray[j].dialCode) {
                               newTempArray.add(tempArray[i])
                           }
                       }
                   }
                   else {
                       newTempArray.add(tempArray[i])
                   }

                }
                finalTempArray.addAll(newTempArray)*/
                filterList = finalArray
           //     filterList = values.asListOfType()
           //     val tempData : ArrayList<Country> = values.asListOfType()
           //     Log.e("gggggg--->>1",Gson().toJson(tempArray))
            //    Log.e("gggggg--->>2",tempData.size.toString())
                notifyDataSetChanged()
            }
            onNothingFound?.invoke(filterList.isNullOrEmpty())
        }
    }
}