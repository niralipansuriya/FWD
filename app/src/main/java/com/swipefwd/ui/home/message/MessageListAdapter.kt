package com.swipefwd.ui.home.message

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.xmpp.database.Message
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageListAdapter(
    private var xmppJid: String,
    private var profile: String,
    private var userName: String,
    private var name: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var current_pos = 0.0
    var total_duration = 0.0

    var mediaPlayer = MediaPlayer()
    var wasPlaying = false

    inner class MyHolder(val binding: ItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var messageList = ArrayList<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewBinding
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            binding =
                ItemChatSenderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SentMessageHolder(binding)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            binding =
                ItemChatReceiverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReceivedMessageHolder(binding)
        } else if (viewType == VIEW_VOICE_TYPE_MESSAGE_SENT) {
            binding =
                ItemAudioInBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SentVoiceMessageHolder(binding)
        } else if (viewType == VIEW_VOICE_TYPE_MESSAGE_RECEIVED) {
            binding =
                ItemAudioOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReceiveVoiceMessageHolder(binding)
        }
        throw RuntimeException("There is no type that matches the type $viewType. Make sure you are using view types correctly!")
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = messageList[position]
        if (message.messageType.equals("voice")) {
            if (message.sender == xmppJid) {
                return VIEW_VOICE_TYPE_MESSAGE_SENT
            } else {
                return VIEW_VOICE_TYPE_MESSAGE_RECEIVED
            }
        } else {
            if (message.sender == xmppJid) {
                return VIEW_TYPE_MESSAGE_SENT
            } else {
                return VIEW_TYPE_MESSAGE_RECEIVED
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message: Message = messageList[position]
        when (getItemViewType(position)) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(message)
            VIEW_VOICE_TYPE_MESSAGE_SENT -> (holder as SentVoiceMessageHolder).bind(message)
            VIEW_VOICE_TYPE_MESSAGE_RECEIVED -> (holder as ReceiveVoiceMessageHolder).bind(message)
        }
    }

    override fun getItemCount(): Int = messageList.size


    inner class SentVoiceMessageHolder(val binding: ItemAudioInBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                val context = txtSenderTextTime.context

                if (message.message!!.isNotEmpty()) {
                    txtAudioTime.text = getDuration(message.message!!)
                    val fileName: String =
                        message.message!!.substring(message.message!!.lastIndexOf('/') + 1)
                    val file = File(
                        context.filesDir,
                        fileName
                    )
                    if (file.exists()) {
                        thumbnailVideoDownload.visibility = View.GONE
                        thumbnailVideoIcon.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    } else {
                        thumbnailVideoDownload.visibility = View.VISIBLE
                        thumbnailVideoIcon.visibility = View.GONE
                        progressBar.visibility = View.GONE

                    }
                    thumbnailVideoDownload.setOnClickListener {
                        progressBar.visibility = View.VISIBLE
                        download(
                            message.message!!,
                            fileName,
                            context,
                            progressBar,
                            thumbnailVideoDownload,
                            thumbnailVideoIcon
                        )
                    }
                    thumbnailVideoIcon.setOnClickListener {
                        playFile(message.message!!, seekbar, context)
                    }

                }
                Log.e("message.message", message.message!!)
                txtSenderTextTime.text = message.timeStamp?.let { getTime(it) }

                var textDrawable: BitmapDrawable? = null
                if (userName != "") {
                    textDrawable = context.createPlaceholderImage(
                        userName,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                    Glide.with(context)
                        .load(profile).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgSender)
                }
            }
        }
    }

    inner class ReceiveVoiceMessageHolder(val binding: ItemAudioOutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                val context = txtReceiverTextTime.context

                if (message.message!!.isNotEmpty()) {
                    thumbnailVideoIcon.setOnClickListener {
                        playFile(message.message!!, seekbar, context)
                    }
                    txtAudioTime.text = getDuration(message.message!!)
                    val fileName: String =
                        message.message!!.substring(message.message!!.lastIndexOf('/') + 1)
                    val file = File(
                        context.filesDir,
                        fileName
                    )
                    if (file.exists()) {
                        thumbnailVideoDownload.visibility = View.GONE
                        thumbnailVideoIcon.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    } else {
                        thumbnailVideoDownload.visibility = View.VISIBLE
                        thumbnailVideoIcon.visibility = View.GONE
                        progressBar.visibility = View.GONE

                    }
                    thumbnailVideoDownload.setOnClickListener {
                        progressBar.visibility = View.VISIBLE
                        download(
                            message.message!!,
                            fileName,
                            context,
                            progressBar,
                            thumbnailVideoDownload,
                            thumbnailVideoIcon
                        )
                    }

                }



                Log.e("message.message", message.message!!)
                txtReceiverTextTime.text = message.timeStamp?.let { getTime(it) }

                var textDrawable: BitmapDrawable? = null
                if (userName != "") {
                    textDrawable = context.createPlaceholderImage(
                        userName,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                    Glide.with(context)
                        .load(profile).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgReceiver)
                }
            }
        }
    }

    inner class SentMessageHolder(val binding: ItemChatSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                val context = txtMsgSender.context
                if (message.messageType == "image") {
                    imgSenderGroup.visibility = View.VISIBLE
                    textSenderGroup.visibility = View.GONE
                    txtSenderImageTime.text = message.timeStamp?.let { getTime(it) }
                    Glide.with(context)
                        .load(message.message).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.1f)
                        .into(imgSenderMsg)


                } else {
                    imgSenderGroup.visibility = View.GONE
                    textSenderGroup.visibility = View.VISIBLE
                    txtMsgSender.text = message.message
                    txtSenderTextTime.text = message.timeStamp?.let { getTime(it) }
                }


                var textDrawable: BitmapDrawable? = null
                if (userName != "") {
                    textDrawable = context.createPlaceholderImage(
                        userName,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                    Glide.with(context)
                        .load(profile).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgSender)
                }
            }
        }
    }

    inner class ReceivedMessageHolder(val binding: ItemChatReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                val context = txtReceiverTextTime.context
                if (message.messageType == "image") {
                    imgSenderGroup.visibility = View.VISIBLE
                    textReceiverGroup.visibility = View.GONE
                    txtReceiverImageTime.text = message.timeStamp?.let { getTime(it) }
                    Glide.with(context)
                        .load(message.message).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.1f)
                        .into(imgReceiverMsg)

                } else {
                    imgSenderGroup.visibility = View.GONE
                    textReceiverGroup.visibility = View.VISIBLE
                    txtMsgReceiver.text = message.message
                    txtReceiverTextTime.text = message.timeStamp?.let { getTime(it) }
                }


                var textDrawable: BitmapDrawable? = null
                if (name != "") {
                    textDrawable = context.createPlaceholderImage(
                        name,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                    Glide.with(context)
                        .load(senderAvatar).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgReceiver)
                }
            }
        }
    }

    companion object {
        private var senderAvatar: ByteArray? = null
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
        private const val VIEW_VOICE_TYPE_MESSAGE_SENT = 3
        private const val VIEW_VOICE_TYPE_MESSAGE_RECEIVED = 4
    }

    fun getTime(timestamp: Long): String? {
        val c: Calendar = Calendar.getInstance()
        c.timeInMillis = timestamp
        val d: Date = c.time
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        return sdf.format(d)
    }

    fun addAll(tempMessageList: ArrayList<Message>) {
        val size = messageList.size
        messageList.clear()
        notifyItemRangeRemoved(0, size)
        messageList.addAll(tempMessageList)
        notifyItemRangeInserted(0, tempMessageList.size)
    }

    fun getSenderAvatar(profile: ByteArray?) {
        senderAvatar = profile
    }

    fun add(message: Message) {
        messageList.add(message)
        notifyItemInserted(messageList.size + 1)
    }

    fun getDuration(url: String): String {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(url)
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val timeInmillisec = time!!.toLong()
        val duration = timeInmillisec / 1000
        val hours = duration / 3600
        val minutes = (duration - hours * 3600) / 60
        val seconds = duration - (hours * 3600 + minutes * 60)
        val f: NumberFormat = DecimalFormat("00")
        var stringTime = f.format(minutes) + ":" + f.format(seconds)
        return stringTime
    }


    private fun download(
        url: String,
        fileName: String,
        context: Context,
        progressBar: ProgressBar,
        downloadImage: ImageView,
        playImage: ImageView
    ) {
        PRDownloader.download(
            url,
            context.filesDir.absolutePath,
            fileName
        )
            .build()
            .setOnProgressListener {
                progressBar.max = it.totalBytes.toInt()
                progressBar.progress = it.currentBytes.toInt()
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    progressBar.visibility = View.GONE
                    downloadImage.visibility = View.GONE
                    playImage.visibility = View.VISIBLE
                }

                override fun onError(error: com.downloader.Error?) {
                    progressBar.visibility = View.GONE
                    downloadImage.visibility = View.VISIBLE
                    playImage.visibility = View.GONE
                }


            })
    }

    private fun playFile(fileUrl: String, seekBar: SeekBar, context: Context) {
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(context, Uri.parse(fileUrl))
            mediaPlayer.prepare()
            mediaPlayer.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        current_pos = mediaPlayer.currentPosition.toDouble();
        total_duration = mediaPlayer.duration.toDouble();
        seekBar.max = total_duration.toInt()
        val handler = Handler(Looper.getMainLooper())

        val runnable: Runnable = object : Runnable {
            override fun run() {
                try {
                    current_pos = mediaPlayer.currentPosition.toDouble()
                    seekBar.progress = current_pos.toInt()
                    handler.postDelayed(this, 1000)
                } catch (ed: IllegalStateException) {
                    ed.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable, 1000)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                current_pos = seekBar!!.progress.toDouble()
                mediaPlayer.seekTo(current_pos.toInt())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


    }

}