package com.swipefwd.ui.home.tribe

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.daimajia.swipe.SwipeLayout
import com.swipefwd.R
import com.swipefwd.databinding.ItemMatchMakerListBinding
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.models.TribeDaterConnectionsResponseModel
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppConstants
import com.swipefwd.xmpp.XmppRosterEntry

class MyMatchMakerListAdapter(
    val activity: Activity,
//    private var startChat: (DaterConnectionResponseModel.User) -> Unit,
//    private val deleteListener: (DaterConnectionResponseModel.User) -> Unit
private var startChat: (TribeDaterConnectionsResponseModel.TribeModel.Tribe) -> Unit,
    private val deleteListener: (TribeDaterConnectionsResponseModel.TribeModel.Tribe) -> Unit
) :
    RecyclerView.Adapter<MyMatchMakerListAdapter.FriendsListHolder>() {

    class FriendsListHolder(val binding: ItemMatchMakerListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListHolder =
        ItemMatchMakerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { FriendsListHolder(this) }

    override fun onBindViewHolder(holder: FriendsListHolder, position: Int) {
        val model = mTribeList[position]
        holder.binding.apply {
           /* if (model.stage.equals(AppConstants.STAGE_PENDING) || model.stage.equals(AppConstants.STAGE_EXPIRED)) {
                swipeMatchMaker.apply {
                    alpha = 0.5f
                    isSwipeEnabled = false
                }
            } else {*/
                swipeMatchMaker.apply {
                    alpha = 1f
                    isSwipeEnabled = true
                    showMode = SwipeLayout.ShowMode.PullOut
                    addDrag(
                        SwipeLayout.DragEdge.Right,
                        findViewById(R.id.layoutDelete)
                    )
                }
//            }
            ivUserStatus.visibility = if (mList[position].isAvailable) {
                View.VISIBLE
            } else {
                View.GONE
            }
            if (model.firstName != "") {
                txtName.text = model.firstName!!.split(" ")[0]
                val textDrawable = activity.createPlaceholderImage(
                    model.firstName!!,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )

                Glide.with(activity)
                    .load(model.profilePic).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(imgProfile)
            }
            layoutConnector.setOnClickListener {
                startChat.invoke(model)
            }
            btnDelete.setOnClickListener {
                if (swipeMatchMaker.openStatus == SwipeLayout.Status.Open) {
                    deleteListener.invoke(model)
                    swipeMatchMaker.close()
                }
            }
        }
    }

    override fun getItemCount(): Int = mTribeList.size

    private val mList = ArrayList<DaterConnectionResponseModel.User>()
    private val mTribeList = ArrayList<TribeDaterConnectionsResponseModel.TribeModel.Tribe>()
    /*fun addAll(list: ArrayList<DaterConnectionResponseModel.User>?) {
        val size = mList.size
        mList.clear()
        notifyItemRangeRemoved(0, size)
        list?.let {
            mList.addAll(it.filter { user ->
                user.username!!.isNotEmpty()
            })
        }
        notifyItemRangeInserted(0, list!!.size)
    }*/

   fun addAll(list: ArrayList<TribeDaterConnectionsResponseModel.TribeModel.Tribe>?) {
        val size = mTribeList.size
       mTribeList.clear()
        notifyItemRangeRemoved(0, size)
        list?.let {
            mTribeList.addAll(it.filter { user ->
                user.firstName!!.isNotEmpty()
            })
        }
        notifyItemRangeInserted(0, list!!.size)
    }

    fun statusOnlineOFfline(from: XmppRosterEntry) {
        val position = 0
        /*mTribeList.forEachIndexed { _, rosters ->
            if (from.xmppJID!!.contains(rosters.phone_number!!)) {
                notifyItemChanged(position, PayLoad.IN_STATUS)
            }
        }*/
    }

    private enum class PayLoad {
        IN_STATUS
    }

    override fun onBindViewHolder(
        holder: FriendsListHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if (payloads.isNotEmpty()) {
            with(holder) {
                binding.apply {
                    payloads.forEach {
                        when (it) {

                            PayLoad.IN_STATUS -> {
                                ivUserStatus.visibility = if (mTribeList[position].isAvailable) {
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
}
