package com.swipefwd.ui.home.wallet

import android.app.Activity
import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityUserListForGiftCoinsBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setShaderView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListForGiftCoinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListForGiftCoinsBinding
    private var mActivity = Activity()
    private var mAdapter: ContactListForWalletAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListForGiftCoinsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        mActivity = this
        binding.apply {
            imgBack.setOnClickListener {
                onBackPressed()
            }
            btnSend.setOnClickListener {
                onBackPressed()
            }
            rvYourTribe.adapter = YourTribeAdapter(mActivity)

            CoroutineScope(Dispatchers.IO).launch {
                val contactList = getContacts()
                withContext(Dispatchers.Main) {
                    contactList?.let {
                        rvContacts.apply {
                            try {
                                binding.progressCircular.visibility = View.GONE
                                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                setDivider()
                                var defaultPosition = -1
                                mAdapter = ContactListForWalletAdapter(mActivity, it, listener = {
                                    defaultPosition = it
                                }, onClickListener = {
                                    binding.btnSend.visibility = View.VISIBLE
                                    mActivity.setShaderView(
                                        binding.edtTo,
                                        R.color.blue_gradient_2,
                                        R.color.blue_gradient_3
                                    )
                                    binding.edtTo.setText(it.name)
                                }).apply {
                                    adapter = this
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getContacts(): ArrayList<UserContactDetails>? =
        withContext(Dispatchers.IO) {
            binding.progressCircular.visibility = View.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            val resolver: ContentResolver = contentResolver
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null
            )
            val mContactList = ArrayList<UserContactDetails>()
            if (cursor!!.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                    val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                    )).toInt()
                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            arrayOf(id),
                            null
                        )
                        if (cursorPhone!!.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                val phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                                val phonesCursor = resolver.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null,
                                    null
                                )
                                while (phonesCursor!!.moveToNext()) {
                                    val type: Int =
                                        phonesCursor.getInt(
                                            phonesCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.TYPE
                                            )
                                        )
                                    mContactList.add(
                                        UserContactDetails(
                                            numberType = type.toString(),
                                            name = name,
                                            formattedNumber = phoneNumValue
                                        )
                                    )
                                    Log.e("TYPE ===>", "TYPE:==$type")
                                }
                                phonesCursor.close()
                            }
                        }
                        cursorPhone.close()
                    }
                }
                cursor.close()
                return@withContext mContactList
            } else {
                cursor.close()
                Log.e("TAG", "No contacts available!")
                return@withContext null
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}