package com.swipefwd.ui.home.message

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.swipefwd.R
import com.swipefwd.databinding.FragmentMessageTribeBinding
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.tribe.HomeFragmentViewModel
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.setShaderView
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MessageTribeFragment : Fragment(), ChatListener {

    private var _binding: FragmentMessageTribeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        TribeMessageAdapter {
            XmppServiceCommand.removeContactFromRoster(requireActivity(), it.xmppJid)
        }
    }
    var tribeList = ArrayList<DaterConnectionResponseModel.User>()
    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(requireActivity())
    }
    private lateinit var xmppBroadcastReceiver: XmppServiceBroadcastEventReceiver
    private var phone = ""
    private val messageViewModel: MessageViewModel by viewModels {
        viewModelFactory { MessageViewModel(requireContext(), AppRepository()) }
    }
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(requireContext(), AppRepository()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageTribeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleScope.launch {
                phone = messageViewModel.phoneNumber.firstOrNull() ?: ""
                homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                    homeViewModel.tribeConnectionListApi(
                        "Bearer $authToken"
                    )
                }
            }
            rvTribeMessage.adapter = adapter
            requireActivity().setShaderView(
                txtInvite,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            xmppBroadcastReceiver = XmppServiceBroadcastEventReceiver(this@MessageTribeFragment)
            activity?.let {
                XmppServiceCommand.sendRosterToActivity(requireActivity())
                initObservers()

            }
        }
    }


    public fun initObservers() {


        homeViewModel.apply {
            showLoading.observe(requireActivity()) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(requireActivity()) {
                Log.e("TAG" , "ERROR === $it")
                AppUtils.showMessageOK(
                    requireActivity() ,
                    getString(R.string.app_name) ,
                    getString(R.string.common_ok) ,
                    it ,
                    null
                )
            }
            error.observe(requireActivity()) {
                Log.e("TAG" , "ERROR === $it")
                AppUtils.showMessageOK(
                    requireActivity() ,
                    getString(R.string.app_name) ,
                    getString(R.string.common_ok) ,
                    it.message.toString() ,
                    null
                )
            }

            tribeData.observe(requireActivity()) {
                it.completed?.let { it1 -> tribeList.addAll(it1) }
                sortingRosters()

            }
            tribeError.observe(requireActivity()) {
                Log.e("TAG" , "ERROR=== $it")
                if (it.completed.isNullOrEmpty()) {

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
        activity?.let { xmppBroadcastReceiver.register(it) }
    }

    override fun onDetach() {
        super.onDetach()
        activity?.let { xmppBroadcastReceiver.unregister(it) }
    }

    /**
     * XMPP Listeners
     */
    override fun setRosterList(list: ArrayList<XmppRosterEntry>) {
    }

    override fun onMessageSent(messageId: Long, message: Message) {
    }

    override fun onMessageDeleted(messageId: Long) {
    }

    override fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean) {
        sortingRosters()
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
            var rosterSet = HashSet<Rosters>()
            if (list.isNotEmpty()) {

                Log.e("size", tribeList.size.toString())



                tribeList.forEach { tribe ->
                    list.forEachIndexed { index, roster ->
                        if (roster.xmppJid!!.contains(tribe.phone_number!!, true)) {
                            roster.name = tribe.username
                            roster.profile_url = tribe.image
                            rosterSet.add(roster)
                        } else {
                        }
                    }
                }
                Log.e("list size", rosterSet.size.toString())
            }



            if (rosterSet.isNotEmpty()) {
                activity?.runOnUiThread {
                    binding.apply {
                        rvTribeMessage.visibility = View.VISIBLE
                        layoutNoTribe.visibility = View.GONE
                    }
                }
                rosterSet.forEach {
                    try {
                        val chatUsers =
                            Utils.getChatUsers("$phone${AppConstants.XMPP_EXTENSION}", it.xmppJid!!)
                        Log.e("chatUsers", "TAG: $chatUsers")
                        val model = mDatabase.chatDao().getLastMessageByRoster(chatUsers)
                        if (model.messageType.equals("image")) {
                            it.lastMessage = "\uD83D\uDDBC️"
                        } else if (model.messageType.equals("voice")) {
                            it.lastMessage = "\uD83C\uDFA4"
                        } else {
                            it.lastMessage = model.message
                        }
                        it.lastMessgeTimeStamp = model.timeStamp!!
                        it.profile_url = model.avatar
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
            } else {
                activity?.runOnUiThread {
                    adapter.clear()
                    binding.apply {
                        rvTribeMessage.visibility = View.GONE
                        layoutNoTribe.visibility = View.VISIBLE
                    }
                }
            }
        }.start()
        //val rosterList = list.sort { it.lastMessgeTimeStamp }
    }

    override fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry) {
        adapter.statusOnlineOFfline(xmppRosterEntry)
    }

}