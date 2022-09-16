package com.swipefwd.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.facebook.CallbackManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationBarView
import com.google.gson.JsonObject
import com.skydoves.balloon.balloon
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.data.models.ChangeRoleModel
import com.swipefwd.data.models.SocialMediaUserModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityTabManagerBinding
import com.swipefwd.databinding.DeleteAccountBinding
import com.swipefwd.databinding.DialogUserVerifiedBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.home.settings.SettingsFragment
import com.swipefwd.ui.home.tribe.HomeFragmentViewModel
import com.swipefwd.ui.home.tribe.MyMatchMakerListFragment
import com.swipefwd.ui.home.tribe.MySingleFriendsListFragment
import com.swipefwd.ui.profile.EmailActivity
import com.swipefwd.ui.tutorial.onboard.OnBoardingViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.BubbleListener
import com.swipefwd.utils.ChangeScreenListener
import com.swipefwd.utils.ToolTipFactory
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.xmpp.XmppAccount
import com.swipefwd.xmpp.XmppService.Companion.setXmppAccount
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter
import com.swipefwd.xmpp.XmppServiceCommand
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class TabManagerActivity : AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener, BubbleListener, ChangeScreenListener {

    private lateinit var binding: ActivityTabManagerBinding
    private var doubleBackToExitPressedOnce: Boolean = false
    private val toolTipBinding by balloon<ToolTipFactory>()
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(this, AppRepository()) }
    }
    private val userViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(this, AppRepository()) }
    }

    private val viewModel: OnBoardingViewModel by viewModels {
        viewModelFactory {
            OnBoardingViewModel(
                Injection.getInternalAppDataStore(applicationContext)
            )
        }
    }


    var callbackmanager: CallbackManager? = null

    private var tabPosition = 0
    private var itemView: BottomNavigationItemView? = null
    private var badgeView: View? = null
    var accountType = ""
    var device_id = ""
    var device_token = ""

    var daterDisabled = false
    var connectorDisabled = false
    private var socialUser = SocialMediaUserModel()
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()


    }

    public fun getValues() {
        lifecycleScope.launch {
            daterDisabled = userViewModel.getDaterDisabled.firstOrNull() ?: false
            connectorDisabled = userViewModel.getConnectorDisabled.firstOrNull() ?: false
            accountType = homeViewModel.getAccountType.firstOrNull() ?: ""
        }

    }

    override fun onResume() {
        super.onResume()
        getValues()
        if (getDisableLogic()) {
            binding.bottomTab.menu.findItem(R.id.nav_activity).isEnabled = false
            binding.bottomTab.menu.findItem(R.id.nav_message).isEnabled = false
            binding.bottomTab.menu.findItem(R.id.nav_wallet).isEnabled = false
        } else {

            binding.bottomTab.menu.findItem(R.id.nav_activity).isEnabled = true
            binding.bottomTab.menu.findItem(R.id.nav_message).isEnabled = true
            binding.bottomTab.menu.findItem(R.id.nav_wallet).isEnabled = true
        }

    }

    private fun init() {
        homeViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "8")
        binding.apply {
            lifecycleScope.launch {





                userViewModel.savePreference(
                    PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED,
                    true
                )
                viewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
                device_id = Settings.Secure.getString(
                    contentResolver,
                    Settings.Secure.ANDROID_ID
                )
                getValues()
                Log.e("TAG", "ACCOUNT TYPE === $accountType")
                //chatService = ChatService.newInstance(this@TabManagerActivity)
                val fName = homeViewModel.userFirstName.firstOrNull() ?: ""
                val lName = homeViewModel.userLastName.firstOrNull() ?: ""
                val userFullName = "$fName $lName"
                loginOrRegister(
                    homeViewModel.userPhoneNumber.firstOrNull() ?: "",
                    homeViewModel.getUserId.firstOrNull() ?: 0,
                    userFullName,
                    homeViewModel.userEmail.firstOrNull() ?: "",
                    homeViewModel.getProfileImage.firstOrNull() ?: "",
                    homeViewModel.userFirstName.firstOrNull() ?: "",
                    homeViewModel.userLastName.firstOrNull() ?: ""
                )


            }
            bottomTab.itemIconTintList = null
            bottomTab.setOnItemSelectedListener(this@TabManagerActivity)
            //addBadge()
            bottomTab.selectedItemId = if (intent.hasExtra("message")) {
                R.id.nav_message
            } else {
                R.id.nav_home
            }
            val menuView: BottomNavigationMenuView =
                binding.bottomTab.getChildAt(0) as BottomNavigationMenuView

            itemView = menuView.getChildAt(1) as BottomNavigationItemView
            badgeView = LayoutInflater.from(this@TabManagerActivity)
                .inflate(R.layout.custom_friends_badge, menuView, false)


/*
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", it.exception)
                    return@addOnCompleteListener
                }
                val token = it.result
                val apiRequest = JsonObject().apply {
                    addProperty("device_id", device_id)
                    addProperty("device_token", token)
                    addProperty("type", "android")
                }
                lifecycleScope.launch {
                    userViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        userViewModel.updateDeviceToken(
                            "Bearer $authToken", apiRequest
                        )
                    }


                }

            }*/


        }
        val apiRequest = JsonObject().apply {
            addProperty("type", "email_popup")
        }
        userViewModel.getDateList(apiRequest)


        userViewModel.dateData.observe(this)
        {
            val c = Calendar.getInstance().time
            println("Current time => $c")

            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)
            println("formattedDate--------------------${formattedDate}")

            if (it.code == 1) {
                if (it.data.date == "") {

                    val dateFormat = SimpleDateFormat("dd-MMM-yyyy")

                    val myDate: Date = dateFormat.parse(formattedDate)

                    val calendar = Calendar.getInstance()
                    calendar.time = myDate
                    calendar.add(Calendar.DAY_OF_YEAR, +7)
                    val newDate = calendar.time
                    println("newDate after 7 days--------------------${df.format(newDate)}")

                    val finalUpdatedate = df.format(newDate)


                    val apiRequest = JsonObject().apply {
                        addProperty("type", "email_popup")
                        addProperty("current_date", finalUpdatedate)
                    }
                    userViewModel.updateDateList(apiRequest)

                  /*  var faceBook = AppUtils.getFaceBook(this@TabManagerActivity)
                    var google = AppUtils.getGoogle(this@TabManagerActivity)
                    var linkedIn = AppUtils.getLinkedIn(this@TabManagerActivity)
                    var email = AppUtils.getEmail(this@TabManagerActivity)

                    if (faceBook.isBlank() && google.isBlank() && linkedIn.isBlank() && email.isBlank()) {

                        nextActivity(EmailActivity::class.java)
                    }*/

                } else if (formattedDate == it.data.date) {
                    val dateFormat = SimpleDateFormat("dd-MMM-yyyy")

                    val myDate: Date = dateFormat.parse(formattedDate)

                    val calendar = Calendar.getInstance()
                    calendar.time = myDate
                    calendar.add(Calendar.DAY_OF_YEAR, +7)
                    val newDate = calendar.time
                    val finalUpdatedate = df.format(newDate)


                    val apiRequest = JsonObject().apply {
                        addProperty("type", "email_popup")
                        addProperty("current_date", finalUpdatedate)
                    }

                    var faceBook = AppUtils.getFaceBook(this@TabManagerActivity)
                    var google = AppUtils.getGoogle(this@TabManagerActivity)
                    var linkedIn = AppUtils.getLinkedIn(this@TabManagerActivity)
                    var email = AppUtils.getEmail(this@TabManagerActivity)

                    if (faceBook.isBlank() && google.isBlank() && linkedIn.isBlank() && email.isBlank()) {

                        nextActivity(EmailActivity::class.java)
                    }
                    userViewModel.updateDateList(apiRequest)

                }
            }


        }

        userViewModel.deleteAccount.observe(this@TabManagerActivity)
        {
            if (it.code == 1) {
                homeViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "9")

                AppUtils.removePrefData(this)
                //  AppUtils.clearSharedPreferences(this)


                nextActivity(LoginActivity::class.java)
                finish()
            }

        }
    }

    private fun addBadge() {
        itemView?.removeView(badgeView)
        itemView?.addView(badgeView)
    }

    private fun Fragment.replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutContainer, this)
            .commitNow()
        //changed due to crash FragmentManager is already executing transactions
        // https://stackoverflow.com/questions/38722325/fragmentmanager-is-already-executing-transactions-when-is-it-safe-to-initialise
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return
        }
        if (binding.bottomTab.selectedItemId == R.id.nav_home) {
            if (doubleBackToExitPressedOnce) {
                finish()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, resources.getString(R.string.exit_msg), Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(
                { doubleBackToExitPressedOnce = false },
                2000
            )
        } else {
            binding.bottomTab.selectedItemId = R.id.nav_home
        }
    }

    public fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutContainer, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun openDeleteDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val dialogBinding = DeleteAccountBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                btnYes.apply {
                    setOnClickListener {
                        dismiss()
                        //delete user api call
                        userViewModel.deleteUserAccount()

                    }
                }

                btnNo.apply {
                    setOnClickListener {
                        dismiss()

                    }
                }
            }
            setZoomDialogWindowAttributes()
            show()
            dialogBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        getValues()
        when (item.itemId) {
            R.id.nav_settings -> {
                //   openDeleteDialog()

                SettingsFragment().replaceFragment()
                return true
            }
            R.id.nav_home -> {
                MyMatchMakerListFragment().replaceFragment()

                //HomeFragment().replaceFragment()
                /*  when (accountType) {
                      AppUtils.AccountTypes.Matchmaker -> {
                          MySingleFriendsListFragment().replaceFragment()
                      }
                      AppUtils.AccountTypes.Dater -> {
                          MyMatchMakerListFragment().replaceFragment()
                      }
                  }*/
                return true
            }
            R.id.nav_activity -> {

//                ActivityFragment.newInstance(tabPosition).replaceFragment()
                tabPosition = 0
                return true
            }
            R.id.nav_wallet -> {
                //this is not visible(26/05/21)
//                showWalletToolTip()
//                WalletFragment().replaceFragment()
                return true
            }
            R.id.nav_message -> {
                // openVerifiedDialog()
//                MessagesFragment().replaceFragment()
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    public fun swapFragment(changeRoleModel: ChangeRoleModel) {
        homeViewModel.savePreference(
            PreferenceKeys.PREF_ACCOUNT_TYPE,
            changeRoleModel.roleType
        )
        when (changeRoleModel.roleType) {
            AppUtils.AccountTypes.Matchmaker -> {
                MySingleFriendsListFragment().replaceFragment()
            }
            AppUtils.AccountTypes.Dater -> {
                MyMatchMakerListFragment().replaceFragment()
            }
        }
    }

    private fun openVerifiedDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val verifiedBinding = DialogUserVerifiedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(verifiedBinding.root)
            verifiedBinding.btnContinue.setOnClickListener {
                verifiedBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            setZoomDialogWindowAttributes()
            show()
            verifiedBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    fun showSingleFriendToolTip() {
        val menuView: BottomNavigationMenuView =
            binding.bottomTab.getChildAt(0) as BottomNavigationMenuView
        val itemView: BottomNavigationItemView = menuView.getChildAt(1) as BottomNavigationItemView
        toolTipBinding.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.tip_single_title)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.tip_single_message)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                toolTipBinding.dismiss()
            }
        }
        toolTipBinding.showAlignTop(itemView)
    }

    private fun showWalletToolTip() {
        val menuView: BottomNavigationMenuView =
            binding.bottomTab.getChildAt(0) as BottomNavigationMenuView
        val itemView: BottomNavigationItemView = menuView.getChildAt(2) as BottomNavigationItemView
        toolTipBinding.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.tooltip_wallet)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.tooltip_wallet_desc)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                toolTipBinding.dismiss()
            }
        }
        toolTipBinding.showAlignTop(itemView)
    }

    override fun onShowBubble(isShow: Boolean) {
        if (isShow) {
            addBadge()
        } else {
            //remove badge if showing
            itemView?.removeView(badgeView)
        }
    }

    override fun setScreen(position: Int) {
        tabPosition = position
        binding.bottomTab.selectedItemId = R.id.nav_activity
    }

    private fun loginOrRegister(
        phone: String,
        password: Int,
        name: String,
        email: String,
        filePath: String,
        firstName: String,
        lastName: String
    ) {

        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute {

            XmppServiceBroadcastEventEmitter.initialize(this@TabManagerActivity, "test")
            val xmppAccount = XmppAccount().apply {
                this.name = name
                this.xmppJid = phone
                this.password = password.toString()

                this.firstName = firstName
                this.lastName = lastName
            }


            XmppServiceCommand.connect(this@TabManagerActivity, xmppAccount)
            setXmppAccount(xmppAccount)
            saveImage(filePath)
        }
    }

    private fun saveImage(url: String?) {
        var fileUri = ""
        Glide.with(this).asBitmap().load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap?>?) {
                    try {
                        val mediaDir = externalMediaDirs.firstOrNull()?.let {
                            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
                        }
                        fileUri =
                            mediaDir!!.absolutePath + File.separator + System.currentTimeMillis()
                                .toString() + ".jpg"
                        val outputStream = FileOutputStream(fileUri)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.flush()
                        outputStream.close()

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    XmppServiceCommand.setAvatar(this@TabManagerActivity, fileUri)
                    Log.d("TAG", "Image Saved")
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    fun getDisableLogic(): Boolean {
        var isDisbled = false
        if (accountType == AppUtils.AccountTypes.Matchmaker) {
            if (connectorDisabled) {
                isDisbled = true
            }
        } else if (accountType == AppUtils.AccountTypes.Dater) {
            if (daterDisabled) {
                isDisbled = true
            }
        }
        return isDisbled
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}