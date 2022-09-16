package com.swipefwd.ui.profile


import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.swipefwd.R
import com.swipefwd.data.models.UserImagesResponseModel
import com.swipefwd.databinding.ItemSelectedPhotoBinding


class TempAdapter(
    val context: Context,
    private val deleteListener: (UserImagesResponseModel.Item) -> Unit,
    private val addImageListener: (Int) -> Unit
) : RecyclerView.Adapter<TempAdapter.MyItemHolder>() {

    var myHolder: MyItemHolder? = null
    var newList   = ArrayList<Boolean>()
    private val imageList = ArrayList<UserImagesResponseModel.Item>()
    fun addAll(list: List<UserImagesResponseModel.Item>) {
        Log.d("ImageListClear",imageList.toString())
        imageList.clear()
        var x = list.sortedBy { it.id }
        if(x.elementAt(0).id==0) {
            val elementWithId0 = x.elementAt(0)
            x = x.filter { it.id!=0 }
            while(x.size<6){
                var index = ( x as MutableList).lastIndex
                x.add(index+1,elementWithId0)
            }
        }
        imageList.addAll(x)
        Log.d("ImageListAddAll",imageList.toString())
        notifyDataSetChanged()
        if (newList.size == 0){
            newList.add(false)
            newList.add(false)
            newList.add(false)
            newList.add(false)
            newList.add(false)
            newList.add(false)
        }
        else {
            if (imageList.size > 0){

            }
        }
    }

    inner class MyItemHolder(val binding: ItemSelectedPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvImage1.setOnClickListener {
                addImageListener.invoke(position)
            }
            if (imageList[position].imageUrl != "") {
                binding.apply {
                    groupId.visibility = View.VISIBLE
                    tvImage1.visibility = View.GONE
                    Glide.with(context)
                        .asBitmap()
                        .load(imageList[position].imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(object : SimpleTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                tvSelectedImage.setImageBitmap(resource)
                            }
                        })
                }
            } else {
                binding.apply {
                    groupId.visibility = View.GONE
                    tvImage1.visibility = View.VISIBLE
                }
            }
            binding.ivClose.setOnClickListener {
                deleteListener.invoke(imageList[position])
            }

            binding.apply {
                if (imageList[position].isInAppropriate) {
                    tvImage1.apply {
                        background =
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.corner_orange_border_bg_image
                            )
                        setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.orange_gradient_1
                            )
                        )
                    }
                } else {
                    tvImage1.apply {
                        background =
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.corner_blue_border_bg_image
                            )
                        setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.settings_name
                            )
                        )
                    }
                }

            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TempAdapter.MyItemHolder {
        val binding =
            ItemSelectedPhotoBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyItemHolder(binding)
    }

    fun removeImage(model: UserImagesResponseModel.Item) {
        val position = imageList.indexOf(model)
        if (position >= 0) {
            imageList.remove(model)
            //notifyItemRemoved(position)
            imageList.add(UserImagesResponseModel.Item())
            //notifyItemInserted(imageList.size)
            notifyItemRangeChanged(0, imageList.size)
        }
    }

    override fun onBindViewHolder(holder: TempAdapter.MyItemHolder, position: Int) {
        myHolder = holder
        holder.apply {
            try {
                this.bind(position)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun changePhotoInvalid(position: Int, isOk: Boolean) {
        if (isOk) {
            imageList[position].isInAppropriate = true
            notifyItemChanged(position)
            //  imageList.onEach { it.isInAppropriate = true }
        } else {
            //  imageList[position].isInAppropriate = !isOk
        }
        /*if (newList.size >= position) {
            newList[position] = true
            notifyItemChanged(position, PayLoad.IS_FALSE_IMAGE)
        }*/

    }

    private enum class PayLoad {
        IS_FALSE_IMAGE
    }

    override fun getItemCount(): Int = imageList.size
}