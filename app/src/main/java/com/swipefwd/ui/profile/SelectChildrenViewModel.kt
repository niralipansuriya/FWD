package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.ChildrenListModel
import com.swipefwd.data.models.ChildrenModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SelectChildrenViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<ChildrenListModel.ChildrenModel>>()
    var data: LiveData<ArrayList<ChildrenListModel.ChildrenModel>> = _data

    private var _dataChildren =
        MutableLiveData<ArrayList<ChildrenModel.ChildrenData.ChildrenLevel>>()
    var dataChildren: LiveData<ArrayList<ChildrenModel.ChildrenData.ChildrenLevel>> = _dataChildren

    private var _error = MutableLiveData<ChildrenListModel>()
    var error: LiveData<ChildrenListModel> = _error

    private var _errorChildren = MutableLiveData<ChildrenModel>()
    var errorChildren: LiveData<ChildrenModel> = _errorChildren

    private val childList = ArrayList<ChildrenListModel.ChildrenModel>()
    private val childDataList = ArrayList<ChildrenModel.ChildrenData.ChildrenLevel>()

    val getChildren = fwdDataStore.getString(PreferenceKeys.PREF_CHILDREN)
    val getPrefChildren = fwdDataStore.getString(PreferenceKeys.PREF_PREF_CHILDREN)

    fun saveChildren(value: String) {
        viewModelScope.launch {
           // fwdDataStore.savePreference(PreferenceKeys.PREF_CHILDREN, value)
          AppUtils.storeChildren(AppController.context!!,value)
        }
    }

    fun savePrefChildren(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_CHILDREN, value)
            AppUtils.storeChildrenPref(AppController.context!!,value)
        }
    }

    fun childrenList(token: String, page: Int = 1) {
        if (page == 1) {
            childList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.childrenListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectChildrenViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectChildrenViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        childList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {
                            _data.postValue(childList)
                        } else {
                            childrenList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<ChildrenListModel>(response))
                    }
                }
        }
    }

    fun childrenApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getChildrenApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectChildrenViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectChildrenViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        childDataList.addAll(response.body()?.childrenData!!.children ?: arrayListOf())
                        if (response.body()?.childrenData!!.children!!.isNotEmpty()) {
                            _dataChildren.postValue(childDataList)
                        } else {
                            childrenApi()
                        }
                    } else {
                        _errorChildren.postValue(ErrorUtils.parseError<ChildrenModel>(response))
                    }
                }
        }
    }
}