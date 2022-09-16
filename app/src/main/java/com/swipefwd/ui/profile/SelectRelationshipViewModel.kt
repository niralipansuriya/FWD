package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.RelationshipListModel
import com.swipefwd.data.models.RelationshipModel
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

class SelectRelationshipViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<RelationshipListModel.RelationshipModel>>()
    var data: LiveData<ArrayList<RelationshipListModel.RelationshipModel>> = _data


    private var _relationshipData = MutableLiveData<ArrayList<RelationshipModel.RelationShipData.RelationshipLevel>>()
    var relationshipData: LiveData<ArrayList<RelationshipModel.RelationShipData.RelationshipLevel>> = _relationshipData

    private var _error = MutableLiveData<RelationshipListModel>()
    var error: LiveData<RelationshipListModel> = _error

    private var _errorRelationShip = MutableLiveData<RelationshipModel>()
    var errorRelationShip: LiveData<RelationshipModel> = _errorRelationShip

    private val relationShipList = ArrayList<RelationshipListModel.RelationshipModel>()
    private val relationShipDataList =
        ArrayList<RelationshipModel.RelationShipData.RelationshipLevel>()

    val getRelationShip = fwdDataStore.getString(PreferenceKeys.PREF_RELATIONSHIP)
    val getPrefRelationShip = fwdDataStore.getString(PreferenceKeys.PREF_PREF_RELATIONSHIP)

    fun saveRelationship(value: String) {
        viewModelScope.launch {
            AppUtils.storeRelationship(AppController.context!!,value)

           // fwdDataStore.savePreference(PreferenceKeys.PREF_RELATIONSHIP, value)
        }
    }

    fun savePrefRelationship(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_RELATIONSHIP, value)
            AppUtils.storeRelationshipPref(AppController.context!!,value)
        }
    }


    fun relationshipDataListApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getRelationshipApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectRelationshipViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectRelationshipViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {

                        relationShipDataList.addAll(
                            response.body()?.relationshipData?.relationship_level ?: arrayListOf()
                        )
                        if (response.body()?.relationshipData!!.relationship_level!!.isNotEmpty()) {
                            _relationshipData.postValue(relationShipDataList)
                        } else {
                            relationshipDataListApi()
                        }
                    } else {
                        _errorRelationShip.postValue(ErrorUtils.parseError<RelationshipModel>(response))
                    }
                }
        }
    }

    fun relationshipList(token: String, page: Int = 1) {
        if (page == 1) {
            relationShipList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.relationshipListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectRelationshipViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectRelationshipViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        relationShipList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {
                            _data.postValue(relationShipList)
                        } else {
                            relationshipList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<RelationshipListModel>(response))
                    }
                }
        }
    }

    fun preferenceRelationshipList(token: String, page: Int = 1) {
        if (page == 1) {
            relationShipList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.preferenceRelationshipListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectRelationshipViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectRelationshipViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        relationShipList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {
                            _data.postValue(relationShipList)
                        } else {
                            preferenceRelationshipList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<RelationshipListModel>(response))
                    }
                }
        }
    }
}