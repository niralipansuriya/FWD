package com.swipefwd.ui.home.message

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.swipefwd.R
import com.swipefwd.databinding.DialogDeleteDaterBinding
import com.swipefwd.databinding.ItemMessagesTribeBinding
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.xmpp.XmppRosterEntry
import com.swipefwd.xmpp.database.Rosters

class TribeMessageAdapter(private var listener: ((Rosters) -> Unit)? = null) :
    RecyclerView.Adapter<TribeMessageAdapter.MyHolder>() {
    inner class MyHolder(val binding: ItemMessagesTribeBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var list = ArrayList<Rosters>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding =
            ItemMessagesTribeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.isNotEmpty()) {
            with(holder) {
                binding.apply {
                    payloads.forEach {
                        when (it) {
                            PayLoad.IN_TYPING -> {

                                val context = txtMessage.context
                                if (list[position].isTyping) {
                                    txtMessage.text = context.getString(R.string.typing)
                                    txtMessage.setTextColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.quantum_googgreen
                                        )
                                    )
                                } else {
                                    txtMessage.text = list[position].lastMessage
                                    txtMessage.setTextColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.colorPagerDesc
                                        )
                                    )

                                }

                            }
                            PayLoad.IN_STATUS -> {
                                imgOnline.visibility = if (list[position].isAvailable) {
                                    View.VISIBLE
                                } else {
                                    View.GONE
                                }
                            }
                        }
                    }
                }

            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val user = list[position]
        holder.binding.apply {
            val context = viewTribe.context
            viewTribe.visibility = if (position == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
            if (user.name!!.isNotEmpty())
                txtName.text = user.name!!.split(" ")[0]
            imgOnline.visibility = if (user.isAvailable) {
                View.VISIBLE
            } else {
                View.GONE
            }
            if (user.isTyping) {
                txtMessage.apply {
                    text = context.getString(R.string.typing)
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.quantum_googgreen
                        )
                    )
                }
            } else {
                txtMessage.apply {
                    text = user.lastMessage
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPagerDesc
                        )
                    )
                }
            }
            var textDrawable: BitmapDrawable? = null
            if (user.name != "") {
                textDrawable = context.createPlaceholderImage(
                    user.name!!,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
                Glide.with(context)
                    .load(user.profile_url)
                    .apply(RequestOptions.circleCropTransform())
                    .dontAnimate()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgMessages)
            }
            layoutMain.setOnClickListener {
                context.startActivity(
                    Intent(context, ChatActivity::class.java)
                        .putExtra("jid", user.xmppJid)
                        .putExtra("name", user.name)
                        .putExtra("image_url", user.profile_url)
                )
            }
            layoutMain.setOnLongClickListener {
                context.openDeleteDaterDialog(list[position])
                return@setOnLongClickListener true
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

    fun addRoster(tempList: List<Rosters>) {
        list.clear()
        list.addAll(tempList.distinctBy {
            it.name
        })
        list.sortByDescending {
            it.lastMessgeTimeStamp
        }
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    fun isTyping(from: String?) {
        list.forEachIndexed { index, rosters ->

            if (from!!.contains(rosters.xmppJid!!)) {
                rosters.isTyping = true

                notifyItemChanged(index, PayLoad.IN_TYPING)
            }
        }
    }

    private enum class PayLoad {
        IN_TYPING,
        IN_STATUS
    }

    fun stopTyping(from: String?) {
        val position = 0
        list.forEachIndexed { _, rosters ->
            if (from!!.contains(rosters.xmppJid!!)) {
                rosters.isTyping = false
                notifyItemChanged(position, PayLoad.IN_TYPING)
            }
        }
    }

    fun statusOnlineOFfline(from: XmppRosterEntry) {
        val position = 0
        list.forEachIndexed { _, rosters ->
            if (from.xmppJID!!.contains(rosters.xmppJid!!)) {
                notifyItemChanged(position, PayLoad.IN_STATUS)
            }
        }
    }
}