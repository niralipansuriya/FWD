package com.swipefwd.ui.swiping.dater_both

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R
import com.swipefwd.databinding.ActivitySwipeChatBinding
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.message.MessageListAdapter
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppConstants
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.xmpp.XmppRosterEntry
import com.swipefwd.xmpp.XmppServiceBroadcastEventReceiver
import com.swipefwd.xmpp.XmppServiceCommand
import com.swipefwd.xmpp.database.ChatListener
import com.swipefwd.xmpp.database.Message
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.*

class SwipeChatActivity : AppCompatActivity(), ChatListener {

    private lateinit var binding: ActivitySwipeChatBinding
    private val mActivity by lazy {
        this@SwipeChatActivity
    }
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(mActivity, AppRepository()) }
    }
    lateinit var xmppBroadcastReceiver: XmppServiceBroadcastEventReceiver
    private lateinit var messageListAdapter: MessageListAdapter
    private var jid = ""
    private var name = ""
    private var phone = ""
    private var profilePicture = ""
    private var swipedProfileImage = ""
    private var isGreen: String = ""
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwipeChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        if (intent.hasExtra("jid")) {
            jid = intent.getStringExtra("jid")!!
        }
        if (intent.hasExtra("isGreenProfile")) {
            isGreen = intent.getStringExtra("isGreenProfile")!!
        }
        if (intent.hasExtra("name")) {
            name = intent.getStringExtra("name")!!
        }
        if (intent.hasExtra("swipedProfileImage")) {
            swipedProfileImage = intent.getStringExtra("swipedProfileImage")!!
        }
        xmppBroadcastReceiver = XmppServiceBroadcastEventReceiver(mActivity)
        binding.apply {
            lifecycleScope.launch {
                userName = swipeViewModel.getFirstName.firstOrNull() ?: ""
                profilePicture = swipeViewModel.getProfileImage.firstOrNull() ?: ""
                phone = swipeViewModel.phoneNumber.firstOrNull() ?: ""
                messageListAdapter = MessageListAdapter(phone, profilePicture, userName, name)
                rcvChat.adapter = messageListAdapter
            }
            ivCancel.setOnClickListener {
                onBackPressed()
            }
            ivSend.setOnClickListener {
                val message = edtMessage.text.toString()
                if (message.isNotEmpty()) {
                    XmppServiceCommand.addContactToRoster(mActivity, jid, name)
                    sendMessage(message)
                }
            }
        }
    }

    private fun sendMessage(message: String) {
        binding.apply {
            edtMessage.setText("")
            rcvChat.visibility = View.VISIBLE
            txt2.visibility = View.GONE
        }
        XmppServiceCommand.sendTypingStop(mActivity, jid)
        XmppServiceCommand.sendMessage(mActivity, jid, message)

    }

    override fun onDestroy() {
        super.onDestroy()
        XmppServiceCommand.sendTypingStop(mActivity, jid)
        xmppBroadcastReceiver.unregister(mActivity)
    }

    override fun onResume() {
        super.onResume()
        xmppBroadcastReceiver.register(mActivity)
        showUI()
        binding.edtMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    XmppServiceCommand.sendTypingStop(mActivity, jid)
                } else {
                    XmppServiceCommand.sendTyping(mActivity, jid)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                XmppServiceCommand.sendTyping(mActivity, jid)
            }

            override fun afterTextChanged(s: Editable?) {
                binding.apply {
                    if (s.toString().trim { it <= ' ' }.isEmpty()) {
                        XmppServiceCommand.sendTypingStop(mActivity, jid)
                        ivSend.visibility = View.GONE
                        ivRecord.visibility = View.VISIBLE
                    } else {
                        ivSend.visibility = View.VISIBLE
                        ivRecord.visibility = View.GONE
                    }
                }
            }
        })
    }

    /**
     * XMPP functionality
     */
    override fun setRosterList(list: ArrayList<XmppRosterEntry>) {
    }

    override fun onMessageSent(messageId: Long, message: Message) {
    }

    override fun onMessageDeleted(messageId: Long) {
    }

    override fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean) {
        messageListAdapter.add(message)
        binding.apply {
            rcvChat.smoothScrollToPosition(messageListAdapter.itemCount - 1)
            txt2.visibility = View.GONE
        }
    }

    override fun onRosterChanged() {
    }

    override fun onRemoveContact(contact: String?) {
    }

    override fun onTyping(from: String?) {
        if (from!!.contains(jid)) {
            binding.edtMessage.hint = getString(R.string.person_typing, name)
        }
    }

    override fun onTypingStop(from: String?) {
        binding.edtMessage.hint = getString(R.string.start_message)
    }

    private fun showUI() {
        runOnUiThread {
            val textDrawable = mActivity.createPlaceholderImage(
                name,
                100,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            if (isGreen == AppConstants.PROFILE_GREEN) {
                binding.apply {
                    txtYou.visibility = View.VISIBLE
                    img1.visibility = View.GONE
                    txt2.apply {
                        visibility = View.VISIBLE
                        text = getString(R.string.chat_message, name)
                    }
                    val ss = SpannableString(getString(R.string.chat_message_1))
                    ss.setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(
                                mActivity,
                                R.color.blue_gradient_2
                            )
                        ), 9, 17, 0
                    )
                    ss.setSpan(
                        StyleSpan(Typeface.BOLD),
                        9,
                        17,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    txt3.apply {
                        visibility = View.VISIBLE
                        text = ss
                    }
                }
                Glide.with(mActivity)
                    .load(swipedProfileImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(binding.img2)
            } else {
                binding.apply {
                    img1.visibility = View.VISIBLE
                    txtYou.visibility = View.GONE
                    txt2.apply {
                        visibility = View.VISIBLE
                        text = getString(R.string.chat_message, name)
                    }
                    val ss = SpannableString(getString(R.string.chat_message_1))
                    ss.setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(
                                mActivity,
                                R.color.blue_gradient_2
                            )
                        ), 9, 17, 0
                    )
                    ss.setSpan(
                        StyleSpan(Typeface.BOLD),
                        9,
                        17,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    txt3.apply {
                        visibility = View.VISIBLE
                        text = ss
                    }
                }
                Glide.with(mActivity)
                    .load(swipedProfileImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(textDrawable)
                    .into(binding.img2)
                Glide.with(mActivity)
                    .load(profilePicture)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.img1)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry) {

    }
}