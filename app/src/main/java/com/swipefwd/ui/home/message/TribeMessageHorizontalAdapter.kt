package com.swipefwd.ui.home.message

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.DialogDeleteDaterBinding
import com.swipefwd.databinding.ItemMessageMatchesBinding
import com.swipefwd.data.models.SwipingProfileModel
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppConstants
import com.swipefwd.xmpp.database.Rosters

class TribeMessageHorizontalAdapter(
    private var listener: ((Rosters) -> Unit)? = null,
    private var clickListener: (SwipingProfileModel.ProfileModel) -> Unit
) :
    RecyclerView.Adapter<TribeMessageHorizontalAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemMessageMatchesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var list = ArrayList<SwipingProfileModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemMessageMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val user = list[position]
        holder.binding.apply {
            val context = layoutMain.context

            txtName.text = user.data!!.firstName
            var textDrawable: BitmapDrawable? = null
            if (user.data.firstName != "") {
                textDrawable = context.createPlaceholderImage(
                    user.data.firstName!!,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
                Glide.with(context)
                    .load(user.data.profilePictureUrl)
                    .dontAnimate()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgMatches)
            }
            layoutMain.setOnClickListener {
                //clickListener.invoke(user.data)
                context?.startActivity(
                    Intent(context, ChatActivity::class.java)
                        .putExtra("jid", user.data.phoneNumber + AppConstants.XMPP_EXTENSION)
                        .putExtra("name", user.data.firstName)
                        .putExtra("image_url", user.data.profilePictureUrl)
                )
            }
        }
    }

    private fun Context.openDeleteDaterDialog(rosters: Rosters) {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val deleteDaterBinding = DialogDeleteDaterBinding.inflate(layoutInflater)
            setContentView(deleteDaterBinding.root)
            deleteDaterBinding.let { viewBind ->
                viewBind.btnCancel.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                viewBind.btnYes.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        listener?.invoke(rosters)
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteDaterBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addMatches(tempList: List<SwipingProfileModel>) {
        list.clear()
        list.addAll(tempList.distinctBy {
            it.data!!.firstName
        })
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


}