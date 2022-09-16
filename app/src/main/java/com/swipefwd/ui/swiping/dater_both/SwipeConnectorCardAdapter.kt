package com.swipefwd.ui.swiping.dater_both

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.chip.Chip
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.gson.Gson
import com.rommansabbir.animationx.Fade
import com.rommansabbir.animationx.animationXFade
import com.squareup.picasso.Picasso
import com.swipefwd.R
import com.swipefwd.databinding.DialogBottomListFriendsBinding
import com.swipefwd.databinding.ItemSwipingCardNewBinding
import com.swipefwd.data.models.ChipGroupModel
import com.swipefwd.data.models.SwipingCustomModel
import com.swipefwd.data.models.SwipingProfileModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppConstants

class SwipeConnectorCardAdapter(
    private val activity: AppCompatActivity,
    private val itemClickListener: (Int, Int) -> Unit,//1:Dislike 2:Revert
) : RecyclerView.Adapter<SwipeConnectorCardAdapter.ViewHolder>() {

    private val profileList: ArrayList<SwipingCustomModel> = ArrayList()
    private var instagramAdapter: InstagramImageAdapter? = null

    class ViewHolder(val binding: ItemSwipingCardNewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ItemSwipingCardNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ViewHolder(this) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isVisible = true
        val profile = profileList[position].userProfileModel
        val context = holder.binding.btnFwd.context
        val chipList = ArrayList<ChipGroupModel>().apply {
            if (!profile?.gender.isNullOrEmpty()) {
                val name = when (profile?.gender) {
                    AppConstants.USER_MALE -> {
                        context.getString(R.string.gender_male)
                    }
                    AppConstants.USER_FEMALE -> {
                        context.getString(R.string.gender_female)
                    }
                    else -> {
                        context.getString(R.string.gender_none)
                    }
                }
                add(
                    ChipGroupModel(
                        name = name,
                        image = ContextCompat.getDrawable(context, R.drawable.gender)
                    )
                )
            }
            if (profile?.height != 0) {
                val height = when (profile?.height_in) {
                    AppConstants.HEIGHT_CM -> {
                        (profile.height!!.div(0.3937)).toInt().toString() + "cm"
                    }
                    else -> {
                        val feet = (profile?.height!!.toFloat() / 12).toInt()
                        val inches = profile.height!!.minus(feet * 12)
                        context.resources.getString(
                            R.string.feet_inches_show,
                            feet.toString(),
                            inches.toString()
                        )
                    }
                }
                add(
                    ChipGroupModel(
                        name = height,
                        image = ContextCompat.getDrawable(context, R.drawable.height)
                    )
                )
            }
            if (!profile.religion.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.religion,
                        image = ContextCompat.getDrawable(context, R.drawable.religion)
                    )
                )
            if (!profile.institution.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.institution,
                        image = ContextCompat.getDrawable(context, R.drawable.education)
                    )
                )
            if (!profile.sunsign.isNullOrEmpty()) {

                val test = if (!profile.sunsign_url.isNullOrEmpty()) {
                    AppUtils.drawableFromUrl(profile.sunsign_url)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.aquarius_black)
                }

                add(
                    ChipGroupModel(
                        name = profile.sunsign,
                        image = test
                    )
                )
            }
            if (!profile.caste.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.caste,
                        image = ContextCompat.getDrawable(context, R.drawable.icon_cast)
                    )
                )
            if (!profile.relationshipInterest.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.relationshipInterest,
                        image = ContextCompat.getDrawable(context, R.drawable.search_for)
                    )
                )
            if (!profile.children.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.children,
                        image = ContextCompat.getDrawable(context, R.drawable.children)
                    )
                )
            if (!profile.vaccinationStatus.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.vaccinationStatus,
                        image = ContextCompat.getDrawable(context, R.drawable.vaccinated)
                    )
                )
            if (!profile.smoking.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.smoking,
                        image = ContextCompat.getDrawable(context, R.drawable.smoking)
                    )
                )
            if (!profile.languages.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.languages!!.joinToString(", "),
                        image = ContextCompat.getDrawable(context, R.drawable.icon_language)
                    )
                )
        }
        holder.binding.apply {
            layoutFb.setOnClickListener {
                //open Dialog
                if (!profile!!.mutualFriends.isNullOrEmpty())
                    profile.mutualFriends?.let {
                        openFacebookFriendDialog(true, it, context, profile.firstName ?: "")
                    }
            }
            if (!profile!!.mutualFriends.isNullOrEmpty()) {
                view1.visibility = View.VISIBLE
                txtConnection.visibility = View.VISIBLE
                layoutFb.visibility = View.VISIBLE
                txtFbFriends.text = if (profile.mutualFriends!!.size == 1) {
                    context.getString(
                        R.string.mutual_fwd_facebook_friends_with_you,
                        profile.mutualFriends!!.size.toString(), "friend"
                    )
                } else {
                    context.getString(
                        R.string.mutual_fwd_facebook_friends_with_you,
                        profile.mutualFriends!!.size.toString(), "friends"
                    )
                }
                if (profile.mutualFriends!!.size == 1) {
                    iv1.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![0].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![0].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![0].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                } else {
                    iv2.visibility = View.INVISIBLE
                    iv3.visibility = View.INVISIBLE
                    txtCount.visibility = View.INVISIBLE
                }
                if (profile.mutualFriends!!.size == 2) {
                    iv1.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![0].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![0].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![0].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    iv2.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![1].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![1].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![1].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                } else {

                    iv3.visibility = View.INVISIBLE
                    txtCount.visibility = View.INVISIBLE
                }
                if (profile.mutualFriends!!.size == 3) {
                    iv1.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![0].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![0].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![0].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    iv2.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![1].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![1].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![1].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    iv3.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![2].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![2].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![2].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                } else {
                    txtCount.visibility = View.INVISIBLE
                }
                if (profile.mutualFriends!!.size >= 4) {
                    iv1.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![0].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![0].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![0].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    iv2.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![1].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![1].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![1].image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    iv3.apply {
                        visibility = View.VISIBLE
                        if (profile.mutualFriends!![2].name != "") {
                            val textDrawable = context.createPlaceholderImage(
                                profile.mutualFriends!![2].name.toString(),
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(context)
                                .load(profile.mutualFriends!![2].image)
                                .diskCacheStrategy(
                                    DiskCacheStrategy.ALL
                                )
                                .placeholder(textDrawable)
                                .into(this)
                        }
                    }
                    txtCount.apply {
                        visibility = View.VISIBLE
                        text = context.getString(
                            R.string.total_count,
                            (profile.mutualFriends!!.size - 3).toString()
                        )
                    }
                }
            } else {
                view1.visibility = View.GONE
                txtConnection.visibility = View.GONE
                layoutFb.visibility = View.GONE
            }
            scrollView.scrollTo(0, 0)
            rlTitle.visibility = View.GONE
            //not showing below views for connector
            btnFwd.visibility = View.VISIBLE
            btnFwd1.visibility = View.VISIBLE
            btnLike.visibility = View.INVISIBLE
            btnLike1.visibility = View.INVISIBLE
            btnSuperlike.visibility = View.GONE
            btnSuperlike1.visibility = View.GONE
            txtSuperCount.visibility = View.GONE
            txtSuperCount1.visibility = View.GONE
            imgCheck.visibility = View.VISIBLE
            if (profile.images.uploads.isNotEmpty()) {
                profile.images.uploads.forEach {
                    try {
                        Picasso.get().load(it).fetch()
                    } catch (e: Exception) {

                    }
                }
            }
            pagerImages.isUserInputEnabled = false // to disable manual swipe
            pagerImages.adapter = SwipePagerAdapter(
                (context as AppCompatActivity).supportFragmentManager,
                context.lifecycle,
                profile.images.uploads
            )
            viewLeft.setOnClickListener {
                if (pagerImages.currentItem > 0) {
                    pagerImages.setCurrentItem(pagerImages.currentItem - 1, false)
                }
            }
            viewRight.setOnClickListener {
                if (pagerImages.currentItem < profile.images.uploads.size - 1) {
                    pagerImages.setCurrentItem(pagerImages.currentItem + 1, false)
                }
            }
            val onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
                if (scrollView.scrollY == 0) {
                    if (!isVisible) {
                        isVisible = true
                        btnDislike1.animationXFade(Fade.FADE_IN, 300, null)
                        btnRevert1.animationXFade(Fade.FADE_IN, 300, null)
                        btnFwd1.animationXFade(Fade.FADE_IN, 300, null)
                    }
                } else {
                    if (isVisible) {
                        isVisible = false
                        btnDislike1.animationXFade(Fade.FADE_OUT, 300, null)
                        btnRevert1.animationXFade(Fade.FADE_OUT, 300, null)
                        btnFwd1.animationXFade(Fade.FADE_OUT, 300, null)
                    }
                }
            }

            scrollView.setOnTouchListener(object : View.OnTouchListener {
                private var observer: ViewTreeObserver? = null

                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    if (observer == null) {
                        observer = scrollView.viewTreeObserver
                        observer!!.addOnScrollChangedListener(onScrollChangedListener)
                    } else if (!observer!!.isAlive) {
                        observer!!.removeOnScrollChangedListener(onScrollChangedListener)
                        observer = scrollView.viewTreeObserver
                        observer!!.addOnScrollChangedListener(onScrollChangedListener)
                    }
                    return false
                }
            })
            val params =
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            var actionBarHeight = 0
            val tv = TypedValue()
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data,
                    context.getResources().displayMetrics
                )
            }
            val displayMetrics = DisplayMetrics()
            context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            val height = displayMetrics.heightPixels - actionBarHeight - 30
            params.height = height
            params.width = width
            pagerImages.layoutParams = params
            layoutIndicator.setViewPager(pagerImages)
            //setting user's details
            if (!profile.dob.isNullOrEmpty()) {
                val dob = profile.dob!!.split("-") //1996-06-20 (actual format from server)
                val age = AppUtils.getAgeFromCurrentDate(dob[0].toInt(), dob[1].toInt(), dob[2].toInt())
                txtDaterName.text =
                    activity.getString(R.string.user_name_age, profile.firstName, age)
            }
            val workList = ArrayList<String>()
            if (!profile.occupationTitle.isNullOrEmpty())
                workList.add(profile.occupationTitle!!)
            if (!profile.occupationCompany.isNullOrEmpty())
                workList.add(profile.occupationCompany!!)
            if (!workList.isNullOrEmpty()) {
                txtOccupation.apply {
                    visibility = View.VISIBLE
                    text = workList.joinToString(" at ")
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            } else {
                val educationList = ArrayList<String>()
                if (!profile.institution.isNullOrBlank() && !profile.graduationYear.isNullOrEmpty()) {
                    educationList.add(profile.institution ?: "")
                    if (!profile.graduationYear.isNullOrBlank()) {
                        educationList.add(profile.graduationYear ?: "")
                    }
                }
                if (!educationList.isNullOrEmpty()) {
                    txtOccupation.apply {
                        visibility = View.VISIBLE
                        text = educationList.joinToString(" ")
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.white_institutemark,
                            0,
                            0,
                            0
                        )
                    }
                } else {
                    txtOccupation.apply {
                        visibility = View.GONE
                        text = ""
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                }
            }
            if (!profile.location.isNullOrEmpty()) {
                txtAddress.apply {
                    visibility = View.VISIBLE
                    text = profile.location
                }
            } else {
                txtAddress.apply {
                    visibility = View.INVISIBLE
                    text = ""
                }
            }
            if (profile.bio.toString().trim().isEmpty()) {
                layoutAboutMe.visibility = View.GONE
                txtAbout.visibility = View.GONE
                viewAboutMe.visibility = View.INVISIBLE
            } else {
                layoutAboutMe.visibility = View.VISIBLE
                txtAbout.visibility = View.VISIBLE
                viewAboutMe.visibility = View.VISIBLE
                txtAboutMe.text = profile.bio.toString()
            }
            if (profile.instagramName!!.isNotEmpty() && profile.instagramName != "@null" && profile.instagramName != "null") {
                txtInstaName.apply {
                    visibility = View.VISIBLE
                    text = if (profile.instagramName!!.startsWith("@", true)) {
                        profile.instagramName
                    } else {
                        "@${profile.instagramName}"
                    }
                }
            } else {
                txtInstaName.apply {
                    visibility = View.GONE
                    text = ""
                }
            }
            instagramAdapter = InstagramImageAdapter {
                context.startActivity(
                    Intent(
                        context,
                        InstagramFullScreenActivity::class.java
                    ).putExtra("image", it).putExtra("user", Gson().toJson(profile))
                )
            }
            rcvInstaImages.adapter = instagramAdapter
            profile.images.instagram.let {
                if (profile.instagramPosts!!) {
                    if (it.isNotEmpty()) {
                        rcvInstaImages.visibility = View.VISIBLE
                        instaImageIndicator.visibility = View.VISIBLE
                        view.visibility = View.VISIBLE
                        instagramAdapter?.addAll(it as ArrayList<String>)
                    }
                } else {
                    rcvInstaImages.visibility = View.GONE
                    instaImageIndicator.visibility = View.GONE
                    view.visibility = View.GONE
                }
            }
            chipGroup.apply {
                removeAllViews()
                chipList.onEach {
                    chipGroup.addView(Chip(context).apply {
                        text = it.name
                        chipBackgroundColor =
                            ResourcesCompat.getColorStateList(
                                resources,
                                R.color.white,
                                context.theme
                            )
                        isCloseIconVisible = false
                        setTextAppearance(R.style.ChipTextAppearance)
                        iconStartPadding = 8f
                        shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(8f)
                        chipIcon = it.image!!
                        setChipIconTintResource(R.color.colorPagerDesc)

                    })
                }
            }
            btnDislike1.setOnClickListener {
                btnDislike.performClick()
            }
            btnDislike.setOnClickListener {
                itemClickListener.invoke(1, position)
            }
            btnRevert.setOnClickListener {
                itemClickListener.invoke(2, position)
            }
            btnRevert1.setOnClickListener {
                btnRevert.performClick()
            }
            btnFwd.setOnClickListener {
                itemClickListener.invoke(3, position)
            }
            btnFwd1.setOnClickListener {
                btnFwd.performClick()
            }
        }
    }

    private fun openFacebookFriendDialog(
        isFbMutual: Boolean,
        model: ArrayList<SwipingProfileModel.ProfileModel.MutualFriend>,
        context: Context,
        name: String
    ) {
        val dialog = Dialog(context, R.style.SlideDialogTheme)
        val binding = DialogBottomListFriendsBinding.inflate(activity.layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                val mList = ArrayList<SwipingProfileModel.ProfileModel>()
                rvFriend.apply {
                    model.onEach {
                        val newModel = SwipingProfileModel.ProfileModel()
                        newModel.firstName = it.name?.let { name ->
                            name.split(" ")[0]
                        }
                        newModel.profilePictureUrl = it.image
                        mList.add(newModel)
                    }
                    adapter = FacebookFriendsListAdapter(false).apply {
                        addAll(mList)
                    }
                    setDivider()
                }
                txtTitle.text = if (isFbMutual) {
                    if (mList.size > 1) {
                        context.getString(R.string.fb_friends_mutual_list, name, "friends")
                    } else {
                        context.getString(
                            R.string.fb_friends_mutual_list,
                            name,
                            "friend"
                        )
                    }
                } else {
                    context.getString(R.string.fb_friends_list, name)
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun addAll(list: ArrayList<SwipingCustomModel>) {
        val size = profileList.size
        profileList.clear()
        notifyItemRangeRemoved(0, size)
        profileList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun getItemCount() = profileList.size
}