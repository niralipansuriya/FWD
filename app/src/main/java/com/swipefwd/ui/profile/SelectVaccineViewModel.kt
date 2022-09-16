package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.CovidVaccineListModel
import com.swipefwd.data.models.VaccinationModel
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

class SelectVaccineViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<CovidVaccineListModel.CovidModel>>()
    var data: LiveData<ArrayList<CovidVaccineListModel.CovidModel>> = _data


    private var _vaccinationData =
        MutableLiveData<ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>>()
    var vaccinationData: LiveData<ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>> =
        _vaccinationData

    private var _error = MutableLiveData<CovidVaccineListModel>()
    var error: LiveData<CovidVaccineListModel> = _error


    private var _errorVaccination = MutableLiveData<VaccinationModel>()
    var errorVaccination: LiveData<VaccinationModel> = _errorVaccination

    private val vaccineList = ArrayList<CovidVaccineListModel.CovidModel>()
    private val vaccineDataList = ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>()

    val getVaccine = fwdDataStore.getString(PreferenceKeys.PREF_COVID)

    fun saveCovidStatus(value: String) {
        viewModelScope.launch {
            //fwdDataStore.savePreference(PreferenceKeys.PREF_COVID, value)
            AppUtils.storeVaccination(AppController.context!!,value)
        }
    }

    fun vaccinationApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getVaccinationApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectVaccineViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectVaccineViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        vaccineDataList.addAll(response.body()?.vaccinationData!!.vaccination ?: arrayListOf())
                        if (response.body()?.vaccinationData!!.vaccination!!.isNotEmpty()) {

                            _vaccinationData.postValue(vaccineDataList)
                        } else {
                            vaccinationApi()
                        }
                    } else {
                        _errorVaccination.postValue(ErrorUtils.parseError<VaccinationModel>(response))
                    }
                }
        }
    }

    fun vaccineStatusList(token: String, page: Int = 1) {
        if (page == 1) {
            vaccineList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.covidVaccineListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectVaccineViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectVaccineViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        vaccineList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _data.postValue(vaccineList)
                        } else {
                            vaccineStatusList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<CovidVaccineListModel>(response))
                    }
                }
        }
    }
}