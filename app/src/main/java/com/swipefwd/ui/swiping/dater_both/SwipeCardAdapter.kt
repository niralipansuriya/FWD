package com.swipefwd.ui.swiping.dater_both

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.TypedValue
import android.view.*
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayOval
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
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppConstants


class SwipeCardAdapter(
    private val activity: AppCompatActivity,
    private val itemClickListener: (Int, Int) -> Unit,//0: Check(Open Details) 1:Dislike 2:Like,
    private val connectorClickListener: (ArrayList<SwipingProfileModel.ProfileModel>, SwipingProfileModel.ProfileModel?, String) -> Unit
) : RecyclerView.Adapter<SwipeCardAdapter.ViewHolder>() {

    private val profileList: ArrayList<SwipingCustomModel> = ArrayList()
    private var instagramAdapter: InstagramImageAdapter? = null
    private var binding: ItemSwipingCardNewBinding? = null

    class ViewHolder(val binding: ItemSwipingCardNewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ItemSwipingCardNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ViewHolder(this) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isVisible = true
        binding = holder.binding
        val context = holder.binding.rlTitle.context
        val user = profileList[position]
        val profile = profileList[position].userProfileModel
        val connectorList = profileList[position].connectorProfileModel
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
                        name = profile.religion!!,
                        image = ContextCompat.getDrawable(context, R.drawable.religion)
                    )
                )
            if (!profile.institution.isNullOrEmpty())
                add(
                    ChipGroupModel(
                        name = profile.institution!!,
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
                    profile.mutualFriends?.let { it1 ->
                        profile.firstName?.let { it2 ->
                            openFacebookFriendDialog(
                                true,
                                it1, context, it2
                            )
                        }
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
            when (profileList[position].profileName) {
                AppConstants.PROFILE_BLURPLE -> {
                    if (connectorList.isNotEmpty()) {
                        rlTitle.apply {
                            visibility = View.VISIBLE
                            background =
                                AppCompatResources.getDrawable(
                                    activity,
                                    R.drawable.blue_gradient_bg
                                )
                        }
                        if (connectorList.size == 1) {
                            txtFwd.apply {
                                text =
                                    activity.getString(
                                        R.string.fwd_by,
                                        ": ${connectorList[0].firstName}"
                                    )
                            }
                        } else {
                            txtFwd.apply {
                                text = activity.getString(R.string.fwd_by, "")
                            }
                        }
                        if (connectorList.size == 1 && connectorList[0].firstName!!.isNotEmpty()) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                        }
                        if (connectorList.size == 2 && connectorList[1].firstName!!.isNotEmpty()) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                            if (connectorList[1].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[1].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img2.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[1].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                        }
                        if (connectorList.size > 2) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                            if (connectorList[1].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[1].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img2.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[1].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                            txtCount.text = activity.getString(
                                R.string.total_count,
                                (connectorList.size - 2).toString()
                            )
                        }
                    } else {
                        rlTitle.apply {
                            visibility = View.GONE
                        }
                    }
                }
                AppConstants.PROFILE_NORMAL -> {
                    rlTitle.visibility = View.GONE
                }
                AppConstants.PROFILE_GREEN -> {
                    if (connectorList.isNotEmpty()) {
                        rlTitle.apply {
                            visibility = View.VISIBLE
                            background =
                                AppCompatResources.getDrawable(activity, R.drawable.mint_gradient)
                        }
                        if (connectorList.size == 1) {
                            txtFwd.apply {
                                text =
                                    activity.getString(
                                        R.string.connected_by,
                                        ": ${connectorList[0].firstName}"
                                    )
                            }
                        } else {
                            txtFwd.apply {
                                text = activity.getString(R.string.connected_by, "")
                            }
                        }
                        if (connectorList.size == 1 && connectorList[0].firstName!!.isNotEmpty()) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                        }
                        if (connectorList.size == 2 && connectorList[1].firstName!!.isNotEmpty()) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                            if (connectorList[1].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[1].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img2.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[1].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                        }
                        if (connectorList.size > 2) {
                            if (connectorList[0].firstName.toString().isNotEmpty()) {
                                val textDrawable = activity.createPlaceholderImage(
                                    connectorList[0].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img1.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[0].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable)
                                        .into(this)
                                }
                            }
                            if (connectorList[1].firstName.toString().isNotEmpty()) {
                                val textDrawable1 = activity.createPlaceholderImage(
                                    connectorList[1].firstName.toString(),
                                    50,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )
                                img2.apply {
                                    visibility = View.VISIBLE
                                    Glide.with(activity)
                                        .load(connectorList[1].profilePictureUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(textDrawable1)
                                        .into(this)
                                }
                            }
                            txtCount.apply {
                                visibility = View.VISIBLE
                                text = activity.getString(
                                    R.string.total_count,
                                    (connectorList.size - 2).toString()
                                )
                            }
                        }
                    }
                }
            }
            if (profile.bio.isNullOrEmpty()) {
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
                        instagramAdapter?.addAll(it)
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
                    if (it.name.equals("Prefer not to say", true)) {

                    } else {
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
            }
            if (rlTitle.visibility == View.VISIBLE && position == 0) {
                when (profileList[position].profileName) {
                    AppConstants.PROFILE_BLURPLE -> {
                        (activity as SwipeProfileActivity).showFWDToolTip(rlTitle, false, position)
                    }
                    AppConstants.PROFILE_GREEN -> {
                        (activity as SwipeProfileActivity).showFWDToolTip(rlTitle, true, position)
                    }
                }
            }
            rlTitle.setOnClickListener {
                //open connector list
                connectorClickListener.invoke(
                    connectorList,
                    profile,
                    profileList[position].profileName
                )
            }
            //setting visibility for below views for Dater type
            btnFwd.visibility = View.GONE
            btnFwd1.visibility = View.GONE
            btnLike.visibility = View.VISIBLE
            btnLike1.visibility = View.VISIBLE
            btnSuperlike.visibility = View.VISIBLE
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

            val onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
                if (scrollView.scrollY == 0) {
                    if (!isVisible) {
                        isVisible = true
                        btnDislike1.animationXFade(Fade.FADE_IN, 300, null)
                        btnRevert1.animationXFade(Fade.FADE_IN, 300, null)
                        btnSuperlike1.animationXFade(Fade.FADE_IN, 300, null)
                        btnLike1.animationXFade(Fade.FADE_IN, 300, null)
                    }
                } else {
                    if (isVisible) {
                        isVisible = false
                        btnDislike1.animationXFade(Fade.FADE_OUT, 300, null)
                        btnRevert1.animationXFade(Fade.FADE_OUT, 300, null)
                        btnSuperlike1.animationXFade(Fade.FADE_OUT, 300, null)
                        btnLike1.animationXFade(Fade.FADE_OUT, 300, null)
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
            //val displayMetrics = DisplayMetrics()
            //context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            //val width = displayMetrics.widthPixels
            //val height = displayMetrics.heightPixels - actionBarHeight - 30
            params.height = AppUtils.getScreenHeight(activity) - actionBarHeight - 30
            params.width = AppUtils.getScreenWidth(activity)
            pagerImages.layoutParams = params
            layoutIndicator.setViewPager(pagerImages)
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
            if (profile.dob!!.isNotEmpty()) {
                val dob = profile.dob!!.split("-") //1996-06-20 (actual format from server)
                val age = AppUtils.getAgeFromCurrentDate(dob[0].toInt(), dob[1].toInt(), dob[2].toInt())
                txtDaterName.text = activity.getString(
                    R.string.user_name_age,
                    profile.firstName,
                    age
                )
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
                if (!profile.institution.isNullOrEmpty() && !profile.graduationYear.isNullOrEmpty()) {
                    educationList.add(profile.institution ?: "")
                    if (!profile.graduationYear.isNullOrEmpty()) {
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
            if (profile.location!!.isNotEmpty()) {
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
            activity.setShaderView(
                txtSuperCount,
                R.color.color_orange,
                R.color.orange_gradient_1
            )
            activity.setShaderView(
                txtSuperCount1,
                R.color.color_orange,
                R.color.orange_gradient_1
            )
            btnDislike1.setOnClickListener {
                btnDislike.performClick()
            }
            btnSuperlike1.setOnClickListener {
                imgSuperLike.scaleUp()
                btnSuperlike1.visibility = View.INVISIBLE
                txtSuperCount1.apply {
                    visibility = View.VISIBLE
                    text = "2"
                }
                txtSuperLike.scaleDown {
                    itemClickListener.invoke(4, position)
                    imgSuperLike.visibility = View.GONE
                    txtSuperLike.visibility = View.GONE
                    btnSuperlike1.visibility = View.VISIBLE
                    txtSuperCount1.visibility = View.GONE
                }
                /*imgSuperLike.apply {
                    visibility = View.VISIBLE
                    startAnimation(anim)
                }
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        btnSuperlike1.visibility = View.INVISIBLE
                        txtSuperCount1.apply {
                            visibility = View.VISIBLE
                            text = "2"
                        }
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        itemClickListener.invoke(4, position)
                        imgSuperLike.visibility = View.GONE
                        txtSuperCount1.visibility = View.GONE
                        btnSuperlike1.visibility = View.VISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }
                })*/
            }
            btnLike1.setOnClickListener {
                btnLike.performClick()
            }
            btnRevert1.setOnClickListener {
                btnRevert.performClick()
            }
            btnSuperlike.setOnClickListener {
                imgSuperLike.scaleUp()
                btnSuperlike.visibility = View.INVISIBLE
                txtSuperCount.apply {
                    visibility = View.VISIBLE
                    text = "2"
                }
                txtSuperLike.scaleDown {
                    itemClickListener.invoke(4, position)
                    imgSuperLike.visibility = View.GONE
                    txtSuperLike.visibility = View.GONE
                    btnSuperlike.visibility = View.VISIBLE
                    txtSuperCount.visibility = View.GONE
                }
                /*imgSuperLike.apply {
                    visibility = View.VISIBLE
                    startAnimation(anim)
                }
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        btnSuperlike.visibility = View.INVISIBLE
                        txtSuperCount.apply {
                            visibility = View.VISIBLE
                            text = "2"
                        }
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        itemClickListener.invoke(4, position)
                        imgSuperLike.visibility = View.GONE
                        btnSuperlike.visibility = View.VISIBLE
                        txtSuperCount.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                })*/
            }

            btnDislike.setOnClickListener {
                itemClickListener.invoke(1, position)
            }
            btnLike.setOnClickListener {
                itemClickListener.invoke(2, position)
            }
            btnRevert.setOnClickListener {
                itemClickListener.invoke(3, position)
            }
        }
    }

    fun showSuperLike(position: Int) {
        profileList.onEachIndexed { i, model ->
            if (model.hasSuperLiked && i == position) {
                binding?.tipAnchor?.showTooltip()
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

    private fun View.showTooltip() {
        val toolTip = createBalloon(activity) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            setLayout(R.layout.layout_text_tool_tip)
            setArrowSize(10)
            setCornerRadius(0f)
            setWidthRatio(0f)
            setArrowColor(ContextCompat.getColor(activity, R.color.blue_gradient_2))
            setArrowOrientation(ArrowOrientation.TOP)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setBackgroundColor(Color.TRANSPARENT)
            setArrowPosition(0.8f)
            setElevation(6)
            setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            setOverlayShape(BalloonOverlayOval)
            setDismissWhenOverlayClicked(true)
            setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            setDismissWhenTouchOutside(true)
            setDismissWhenShowAgain(false)
            setLifecycleOwner(activity)
        }
        toolTip.showAlignTop(this)
    }

    override fun getItemCount() = profileList.size

    private fun View.scaleUp() {
        visibility = View.VISIBLE
        val scaleUp = ScaleAnimation(
            0f, 1f,  // Start and end values for the X axis scaling
            0f, 1f,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            fillAfter = true
            fillBefore = true
            duration = 1000
            interpolator = AnticipateOvershootInterpolator()
        }
        val alpha = AlphaAnimation(0f, 1f).apply {
            fillAfter = true
            fillBefore = true
            duration = 300
            interpolator = LinearInterpolator()
        }
        AnimationSet(false).apply {
            addAnimation(scaleUp)
            addAnimation(alpha)
            startAnimation(this)
        }
    }

    private fun View.scaleDown(animEndListener: () -> Unit) {
        visibility = View.VISIBLE
        val scaleDown = ScaleAnimation(
            1.5f, 1f,  // Start and end values for the X axis scaling
            1.5f, 1f,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            fillAfter = true // Needed to keep the result of the animation
            fillBefore = true
            duration = 1000
            interpolator = AnticipateOvershootInterpolator()
        }
        val alpha = AlphaAnimation(0f, 1f).apply {
            fillAfter = true
            fillBefore = true
            duration = 300
            interpolator = LinearInterpolator()
        }
        AnimationSet(false).apply {
            addAnimation(scaleDown)
            addAnimation(alpha)
            startAnimation(this)
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    animEndListener.invoke()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationStart(animation: Animation?) {

                }
            })
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
                    if (mList.size >= 1) {
                        context.getString(R.string.fb_friends_mutual_list, name, "friend")
                    } else {
                        context.getString(
                            R.string.fb_friends_mutual_list,
                            name,
                            "friends"
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

}