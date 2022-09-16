package com.swipefwd.ui.home.message

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.DialogRematchBinding
import com.swipefwd.databinding.FragmentMessageMatchesBinding
import com.swipefwd.data.models.SwipingProfileModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.settings.UserSubscriptionActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.xmpp.Utils
import com.swipefwd.xmpp.XmppRosterEntry
import com.swipefwd.xmpp.XmppServiceBroadcastEventReceiver
import com.swipefwd.xmpp.XmppServiceCommand
import com.swipefwd.xmpp.database.ChatListener
import com.swipefwd.xmpp.database.Message
import com.swipefwd.xmpp.database.Rosters
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MessageMatchesFragment : Fragment(), ChatListener {

    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(requireActivity(), AppRepository()) }

    }
    private lateinit var xmppBroadcastReceiver: XmppServiceBroadcastEventReceiver
    private var phone = ""
    private var matchesList = ArrayList<SwipingProfileModel>()

    private val mDatabase by lazy {
        AppDatabase.getInstance(requireActivity())
    }
    private lateinit var binding: FragmentMessageMatchesBinding

    private val adapter by lazy {
        TribeMessageAdapter {
            XmppServiceCommand.removeContactFromRoster(requireActivity(), it.xmppJid)
        }
    }


    private val horizontalAdapter by lazy {
        TribeMessageHorizontalAdapter(listener = {
            XmppServiceCommand.removeContactFromRoster(requireActivity(), it.xmppJid)
        }, clickListener = {
            openRematchDialog(it)
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageMatchesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {


            xmppBroadcastReceiver = XmppServiceBroadcastEventReceiver(this@MessageMatchesFragment)
            lifecycleScope.launch {
                phone = swipeViewModel.phoneNumber.firstOrNull() ?: ""
                val token = swipeViewModel.getAuthToken.firstOrNull()!!
                val userId = swipeViewModel.getUserId.firstOrNull() ?: 0
                swipeViewModel.matchesListApi(
                    userId, "Bearer $token"
                )

            }
            rvMatches.adapter = horizontalAdapter
            rvMessage.adapter = adapter
            activity?.let {


                initObserver()

            }
        }
    }

    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }

    private fun initObserver() {
        swipeViewModel.apply {
            showLoading.observe(requireActivity(), {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(requireActivity(), {
                Log.e("TAG", "ERROR === $it")
                AppUtils.showMessageOK(
                    requireActivity(),
                    getString(R.string.app_name),
                    getString(R.string.common_ok),
                    it,
                    null
                )
            })
            swipeUserProfileData.observe(requireActivity(), { profileModelList ->
                Log.e("TAG", "Profiles === ${profileModelList.size}")
                binding.apply {
                    profileModelList.forEach { profileModel ->
                        matchesList.add(profileModel)
                    }
                    Log.e("matches", matchesList.size.toString())
                    horizontalAdapter.clear()
                    try {
                        XmppServiceCommand.sendRosterToActivity(requireActivity())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    binding.apply {
                        if (matchesList.isNotEmpty()) {
                            llMatches.visibility = View.VISIBLE
                            llNoMatches.visibility = View.GONE
                            horizontalAdapter.addMatches(matchesList.toList())
                            sortingRosters()
                        } else {
                            llMatches.visibility = View.GONE
                            llNoMatches.visibility = View.VISIBLE
                        }
                    }
                }
            })
            matchesBool.observe(requireActivity(), {
                if (!it) {
                    binding.apply {
                        llNoMatches.visibility = View.VISIBLE
                        llMatches.visibility = View.GONE
                    }
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        sortingRosters()
    }

    override fun onMessageSent(messageId: Long, message: Message) {

    }

    override fun onMessageDeleted(messageId: Long) {

    }

    override fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean) {
        sortingRosters()
    }

    override fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry) {
        adapter.statusOnlineOFfline(xmppRosterEntry)
    }

    override fun setRosterList(list: ArrayList<XmppRosterEntry>) {
    }

    override fun onRosterChanged() {
        sortingRosters()
    }

    override fun onRemoveContact(contact: String?) {
        sortingRosters()
    }

    override fun onTyping(from: String?) {
        adapter.isTyping(from)
    }

    override fun onTypingStop(from: String?) {
        adapter.stopTyping(from)
    }

    private fun sortingRosters() {
        Thread {
            val list = mDatabase.chatDao().getAllRosters()
            val rosterSet = HashSet<Rosters>()
            if (list.isNotEmpty()) {
                Log.e("size", matchesList.size.toString())

                matchesList.forEach { tribe ->
                    list.forEachIndexed { index, roster ->
                        if (roster.xmppJid!!.contains(
                                tribe.data.phoneNumber!!,
                                true
                            )
                        ) {
                            if (roster.name!!.contains("null", true)) {
                                roster.name =
                                    tribe.data.firstName + " " + tribe.data.lastName
                                roster.profile_url = tribe.data.profilePictureUrl
                            }
                            rosterSet.add(roster)
                        } else {
                        }
                    }
                }
                Log.e("list size", rosterSet.size.toString())
            }
            if (rosterSet.isNotEmpty()) {

                rosterSet.forEach {
                    try {
                        val chatUsers =
                            Utils.getChatUsers("$phone${AppConstants.XMPP_EXTENSION}", it.xmppJid!!)
                        Log.e("chatUsers", "TAG: $chatUsers")
                        val model = mDatabase.chatDao().getLastMessageByRoster(chatUsers)
                        if (model.messageType.equals("image")) {

                            it.lastMessage = "\uD83D\uDDBC️"
                        } else if(model.messageType.equals("voice")){
                            it.lastMessage = "\uD83C\uDFA4"
                        }else {
                            it.lastMessage = model.message
                        }

                        it.lastMessgeTimeStamp = model.timeStamp!!
                        Log.e("message", it.lastMessage + "")
                        Log.e("message", it.lastMessgeTimeStamp.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                val list = ArrayList<Rosters>(rosterSet)
                activity?.runOnUiThread {
                    adapter.addRoster(list)
                }
            }
        }.start()
        //val rosterList = list.sort { it.lastMessgeTimeStamp }
    }

    override fun onStart() {
        super.onStart()
        activity?.let { xmppBroadcastReceiver.register(it) }
    }

    override fun onDetach() {
        super.onDetach()
        activity?.let { xmppBroadcastReceiver.unregister(it) }
    }

    private fun openRematchDialog(model: SwipingProfileModel.ProfileModel) {
        val dialog = Dialog(requireContext(), R.style.SlideBottomSheetDialogTheme)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val dialogRematchBinding = DialogRematchBinding.inflate(layoutInflater)
            setContentView(dialogRematchBinding.root)
            dialogRematchBinding.apply {
                Glide.with(requireActivity())
                    .load(model.profilePictureUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.ic_launcher)
                    .into(roundedUserImage)
                if (model.dob!!.isNotEmpty()) {
                    val dob = model.dob?.split("-") //1996-06-20 (actual format from server)
                    val age =
                        AppUtils.getAgeFromCurrentDate(dob!![0].toInt(), dob[1].toInt(), dob[2].toInt())
                    tvUserName.text = activity?.getString(
                        R.string.user_name_age,
                        model.firstName,
                        age
                    )
                }
                val workList = ArrayList<String>()
                if (!model.occupationTitle.isNullOrEmpty())
                    workList.add(model.occupationTitle ?: "")
                if (!model.occupationCompany.isNullOrEmpty())
                    workList.add(model.occupationCompany ?: "")
                if (!workList.isNullOrEmpty()) {
                    tvUserProfession.apply {
                        visibility = View.VISIBLE
                        text = workList.joinToString(" at ")
                    }
                } else {
                    tvUserProfession.apply {
                        visibility = View.GONE
                        text = ""
                    }
                }
                if (model.location!!.isNotEmpty()) {
                    tvUserCity.apply {
                        visibility = View.VISIBLE
                        text = model.location
                    }
                } else {
                    tvUserCity.apply {
                        visibility = View.INVISIBLE
                        text = ""
                    }
                }
                context.setShaderView(
                    tvMessage,
                    R.color.color_orange,
                    R.color.orange_gradient_1
                )
                txtRemove.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                txtRematch.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        //open subscription plan activity
                        requireActivity().nextActivity(UserSubscriptionActivity::class.java)
                    }
                }
                try {
                    setZoomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}