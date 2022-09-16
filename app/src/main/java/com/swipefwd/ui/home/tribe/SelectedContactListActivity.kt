package com.swipefwd.ui.home.tribe

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swipefwd.databinding.ActivitySelectedContactListBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.factory.viewModelFactory
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SelectedContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedContactListBinding
    private val mActivity by lazy {
        this
    }
    private var contactDetailsList = ArrayList<UserContactDetails>()
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(this, AppRepository()) }
    }
    private var deletedContactList = ArrayList<String>()
    private val contactAdapter by lazy {
        SelectedContactAdapter(mActivity, deleteClickListener = {
            contactDetailsList.remove(it)
            deletedContactList.add(it.id)
            Log.d("TAG:", "contactDetailsList: == ${contactDetailsList.size}")
            if (contactDetailsList.size == 0) {
                binding.ivBack.performClick()
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            btnConfirm.setOnClickListener {
                ivBack.performClick()
            }
            btnAddMore.setOnClickListener {
                ivBack.performClick()
            }
            ivBack.setOnClickListener {
                onBackPressed()
            }
            ivClose.setOnClickListener {
                ivBack.performClick()
            }
            rvSelectedList.apply {
                lifecycleScope.launch {
                    val contactList = /*homeViewModel.getContactList.firstOrNull() ?:*/ ""
                    val type: Type =
                        object : TypeToken<List<UserContactDetails?>?>() {}.type
                    contactDetailsList =
                        Gson().fromJson(contactList, type) as ArrayList<UserContactDetails>
                    setDivider()
                    contactAdapter.addAll(contactDetailsList)
                    adapter = contactAdapter
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}