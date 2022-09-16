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


class AddMorePhotosAdapter(
    val context: Context,
  //  private val deleteListener: (UserImagesResponseModel.Item) -> Unit,
    private val deleteListener: (ProfilePhotosModel.DataProfile.UserPhotos) -> Unit,
    private val addImageListener: (Int) -> Unit
) : RecyclerView.Adapter<AddMorePhotosAdapter.MyItemHolder>() {

    var myHolder: MyItemHolder? = null
    var newList   = ArrayList<Boolean>()
    //private val imageList = ArrayList<UserImagesResponseModel.Item>()
    private val imageList = ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>()
    private var imageListMore = ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>()

   // fun addAll(list: List<UserImagesResponseModel.Item>) {
    fun addAll(list: List<ProfilePhotosModel.DataProfile.UserPhotos>) {
        Log.d("ImageListClear",imageList.toString())
        imageList.clear()
        var x = list.sortedBy { it.position }
        if(x.elementAt(0).position==0) {
            val elementWithId0 = x.elementAt(0)
            x = x.filter { it.position!=0 }
            while(x.size<6){
                var index = ( x as MutableList).lastIndex
                x.add(index+1,elementWithId0)
            }
        }
        imageList.addAll(x)
        Log.d("ImageListAddAll",imageList.toString())
        notifyDataSetChanged()

    }
    fun addImage(list: List<ProfilePhotosModel.DataProfile.UserPhotos>) {
        imageListMore.clear()
        Log.d("imageListMore",imageListMore.toString())
//        var x = list.sortedBy { it.position }
        /*val posList = arrayListOf<Int>()
        list.forEach {
            posList.add(it.position!!)
        }*/
        for (index in 0..5){
            imageListMore.add(ProfilePhotosModel.DataProfile.UserPhotos(position = index, isNull = true))
        }
        list.forEach {
            imageListMore.removeAt(it.position!!)
            imageListMore.add(it.position!!,it)
        }

//        imageListMore.addAll(list)
       /* if (imageListMore.size<=6){
            Log.d("ImageListAddAll New Size =====",imageListMore.size.toString())
                val empty = 6 - imageListMore.size
            Log.d("empty =====",empty.toString())
                for (i in 1..empty){
                    if (imageListMore[i] == null){
                    imageListMore.add(ProfilePhotosModel.DataProfile.UserPhotos(isNull = true))
                    }
                }
        }*/
        Log.d("ImageListAddAll New=====",imageListMore.toString())
        Log.d("ImageListAddAll New Size =====",imageListMore.size.toString())
        notifyDataSetChanged()
    }

    inner class MyItemHolder(val binding: ItemSelectedPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvImage1.setOnClickListener {
                addImageListener.invoke(position)
            }
            if (imageListMore[position].photo != "" && !imageListMore[position].isNull) {
                binding.apply {
                    groupId.visibility = View.VISIBLE
                    tvImage1.visibility = View.GONE
                    Glide.with(context)
                        .asBitmap()
                        .load(imageListMore[position].photo)
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
                    tvImage1.apply {
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.image_grey_border
                        )
                    }
                }
            }
            binding.ivClose.setOnClickListener {
                deleteListener.invoke(imageListMore[position])
            }

            binding.apply {
                if (imageListMore[position].isInAppropriate) {
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
                                R.drawable.image_grey_border
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
    ): AddMorePhotosAdapter.MyItemHolder {
        val binding =
            ItemSelectedPhotoBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyItemHolder(binding)
    }

  //  fun removeImage(model: UserImagesResponseModel.Item) {
    fun removeImage(model: ProfilePhotosModel.DataProfile.UserPhotos) {
        val position = imageListMore.indexOf(model)
        if (position >= 0) {
            imageListMore.remove(model)
            //notifyItemRemoved(position)
            imageListMore.add(ProfilePhotosModel.DataProfile.UserPhotos())
            //notifyItemInserted(imageList.size)
            notifyItemRangeChanged(0, imageListMore.size)
        }
    }

 //  fun removeImage(model: UserImagesResponseModel.Item) {
    fun removeImage(pos: Int) {
        if (pos >= 0) {
            imageListMore.removeAt(pos)
            //notifyItemRemoved(position)
            imageListMore.add(pos,ProfilePhotosModel.DataProfile.UserPhotos(isNull = true))
            //notifyItemInserted(imageList.size)
            notifyItemRangeChanged(0, imageListMore.size)
        }
    }

    override fun onBindViewHolder(holder: AddMorePhotosAdapter.MyItemHolder, position: Int) {
        myHolder = holder
        holder.apply {
            try {
                this.bind(position)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getList() = imageListMore

    fun changePhotoInvalid(position: Int, isOk: Boolean) {
        if (isOk) {
            imageListMore[position].isInAppropriate = true
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

    override fun getItemCount(): Int = imageListMore.size
}