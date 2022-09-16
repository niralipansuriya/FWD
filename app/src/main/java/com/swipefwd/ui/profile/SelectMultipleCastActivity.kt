package com.swipefwd.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swipefwd.R
import com.swipefwd.databinding.ActivitySelectMultipleCastBinding
import com.swipefwd.data.models.CastListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.showKeyboard
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SelectMultipleCastActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectMultipleCastBinding
    private val mActivity by lazy {
        this@SelectMultipleCastActivity
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private var isFromSettings = false
    private val castViewModel: SelectCastViewModel by viewModels {
        viewModelFactory { SelectCastViewModel(mActivity, AppRepository()) }
    }
    private var selectedCastList = ArrayList<CastListModel.CastModel>()
    private var castDbList = ArrayList<CastListModel.CastModel>()

    private val adapter by lazy {
        SelectCastAdapter(itemClickListener = { castModel ->
            if (selectedCastList.isNotEmpty()) {
                selectedCastList.any {
                    it.id == castModel.id
                }.let {
                    if (it) {
                        binding.txtCastName.setText("")
                        binding.cardList.visibility = View.GONE
                    } else {
                        selectedCastList.add(castModel)
                        updateListForSelectedLanguages(castModel)
                    }
                }
            } else {
                selectedCastList.add(castModel)
                updateListForSelectedLanguages(castModel)
            }
        })
    }
    private val selectedCastAdapter by lazy {
        SelectedMultipleCastAdapter(removeListener = {
            //remove task here
            selectedCastList.remove(it)
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectMultipleCastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isFromSettings = intent.hasExtra("EditProfile")
        init()
    }

    private fun init() {
        binding.apply {
            txtIndex.text = getString(R.string.sequence_preference, "4")
            ivClose.setOnClickListener {
                onBackPressed()
            }
            txtSkip.setOnClickListener {
                nextActivity(SelectChildrenPlanActivity::class.java)
                finish()
            }
            txtSubmit.setOnClickListener {
                castViewModel.saveMultipleCast(Gson().toJson(selectedCastList))
                nextActivity(SelectChildrenPlanActivity::class.java)
                finish()
            }
            lifecycleScope.launch {
                castDbList =
                    mDatabase.userProfileDao().getAllCast() as ArrayList<CastListModel.CastModel>
                if (castDbList.isNotEmpty()) {
                    bindAdapter(castDbList)
                } else {
                    getCastList()
                    initObservers()
                }
            }
            rcvCast.adapter = adapter
            layoutSelectedCast.adapter = selectedCastAdapter
            txtCastName.apply {
                this.showKeyboard()
                setOnClickListener {
                    isCursorVisible = true
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        txtSubmit.performClick()
                        true
                    }
                    false
                }
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (s.isNullOrBlank()) {
                            cardList.visibility = View.GONE
                        } else {
                            cardList.visibility = View.VISIBLE
                            adapter.search(s.toString()) {
                                // do nothing
                            }
                        }
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (count == 0) {
                            txtCastName.setBackgroundResource(R.drawable.grey_border_bg)
                            cardList.visibility = View.GONE
                        }
                    }
                })
            }
        }
    }

    private fun updateListForSelectedLanguages(model: CastListModel.CastModel) {
        selectedCastAdapter.addLanguage(model)
        castDbList.filter {
            it.id != model.id
        }.let {
            adapter.addAll(it as java.util.ArrayList<CastListModel.CastModel>)
        }
        binding.apply {
            txtCastName.setText("")
            if (selectedCastList.isNotEmpty()) {
                txtSubmit.isEnabled = true
                txtSubmit.isClickable = true
            } else {
                txtSubmit.isEnabled = false
                txtSubmit.isClickable = false
            }
        }
    }


    private fun getCastList() {

        lifecycleScope.launch {
            castViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                castViewModel.castList(
                    "Bearer $authToken"
                )
            }
        }

    }

    private fun initObservers() {
        castViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
            })
            data.observe(mActivity, { castList ->
                castDbList = castList
                bindAdapter(castList)
                CoroutineScope(Dispatchers.IO).launch {
                    mDatabase.userProfileDao().insertAllCast(castList)
                }
            })
            error.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
            })
        }
    }

    private fun bindAdapter(list: ArrayList<CastListModel.CastModel>) {
        adapter.addAll(list)
        lifecycleScope.launch {
            val caste = castViewModel.getPrefCast.firstOrNull()
            if (!caste.isNullOrBlank()) {
                val type: Type =
                    object : TypeToken<List<CastListModel.CastModel?>?>() {}.type
                selectedCastList =
                    Gson().fromJson(caste, type) as ArrayList<CastListModel.CastModel>
                Log.d("TAG:", "casts: == $selectedCastList")
                selectedCastList.onEach {
                    updateListForSelectedLanguages(it)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        selectedCastList.clear()
        finish()
    }
}