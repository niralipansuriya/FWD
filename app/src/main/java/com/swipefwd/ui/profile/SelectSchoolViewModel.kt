package com.swipefwd.ui.profile

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.EducationDetailModel
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.EducationLevelListModel
import com.swipefwd.data.models.InstitutesModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SelectSchoolViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)
    val getIsHeightFeet = fwdDataStore.getBoolean(PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET)
    val getStartHeight = fwdDataStore.getString(PreferenceKeys.PREF_PREF_START_HEIGHT)
    val getEndHeight = fwdDataStore.getString(PreferenceKeys.PREF_PREF_END_HEIGHT)
    val getStartHeightInches = fwdDataStore.getInt(PreferenceKeys.PREF_PREF_START_HEIGHT_INCHES)
    val getEndHeightInches = fwdDataStore.getInt(PreferenceKeys.PREF_PREF_END_HEIGHT_INCHES)
    val getEducation = fwdDataStore.getString(PreferenceKeys.PREF_EDUCATION)
    val getPrefEducation = fwdDataStore.getString(PreferenceKeys.PREF_PREF_EDUCATION)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<EducationLevelListModel.LevelModel>>()
    var data: LiveData<ArrayList<EducationLevelListModel.LevelModel>> = _data


  private var _educationData = MutableLiveData<ArrayList<EducationDetailModel.DataEducatiton.EducationData>>()
    var educationData: LiveData<ArrayList<EducationDetailModel.DataEducatiton.EducationData>> = _educationData

    private var _error = MutableLiveData<EducationLevelListModel>()
    var error: LiveData<EducationLevelListModel> = _error

    private var _instituteList = MutableLiveData<ArrayList<InstitutesModel.Result>>()
    var instituteList :LiveData<ArrayList<InstitutesModel.Result>> = _instituteList

    private val levelList = ArrayList<EducationLevelListModel.LevelModel>()
    private val educationList = ArrayList<EducationDetailModel.DataEducatiton.EducationData>()

    fun saveSchool(value: String) {
        viewModelScope.launch {
          //  fwdDataStore.savePreference(PreferenceKeys.PREF_EDUCATION, value)
            AppUtils.storeEducation(AppController.context!!,value)
        }
    }
    fun savePrefSchool(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_EDUCATION, value)
            AppUtils.storeEducationPref(AppController.context!!,value)
        }
    }

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }


    fun educationLevelList(token: String, page: Int = 1) {
        if (page == 1)
        {
            levelList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.educationLevelListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectSchoolViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectSchoolViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        levelList.addAll(response.body()?.items?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _data.postValue(levelList)
                        }
                        else {
                            educationLevelList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<EducationLevelListModel>(response))
                    }
                }
        }
    }


    fun getEducationDetails() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getEducationApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectSchoolViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectSchoolViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                      //  levelList.addAll(response.body()?.items?: arrayListOf())
                        educationList.addAll(response.body()?.data!!.education?: arrayListOf())

                        if (response.body()?.data!!.education!!.isNotEmpty()) {

                            _educationData.postValue(educationList)
                        }

                    } else {
                        _error.postValue(ErrorUtils.parseError<EducationLevelListModel>(response))
                    }
                }
        }
    }

    fun instituteList(query:String,key:String)
    {
        val url ="https://maps.googleapis.com/maps/api/place/textsearch/json?query=$query&type=university&key=$key"

        viewModelScope.launch {
            appRepository.instituteListApi(url)
                .catch { e ->
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectSchoolViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {
                        _instituteList.postValue(response.body()?.results?:ArrayList())
                    } else {
                        _error.postValue(ErrorUtils.parseError<EducationLevelListModel>(response))
                    }
                }
        }
    }
}