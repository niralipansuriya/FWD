package com.swipefwd.ui.splash

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.InactivityModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject

class SplashViewModel(
    context: Context,
    private val appRepository: AppRepository,
    private val internalAppDataStore: InternalAppDataStore,
    private val packageManager: PackageManager,
    private val packageName: String
) : BaseViewModel(context) {
    ////////////////////////////////////////////////
    private val fwdDataStore by lazy { InternalAppDataStore(context) }
    private var recoveryEmailId : String?=null
    fun setRecoverEmailId(id:String?){
        println("setRecoverEmailId->>>${id}")
        if(id.isNullOrBlank()) return
        this.recoveryEmailId  =  id
    }
    fun isForRecoverAccount():Boolean{
      return  recoveryEmailId != null
    }
    fun getRecoveryEmail() = requireNotNull(recoveryEmailId)
    ////////////////////////////////////

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)
    val getLoginFlag = internalAppDataStore.getBoolean(PreferenceKeys.PREF_LOGIN_FLAG)
    val getCurrentScreen = internalAppDataStore.getString(PreferenceKeys.PREF_CURRENT_SCREEN)
    val isOnBoardTutorialCompletedStatus =
        internalAppDataStore.getBoolean(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED)



    suspend fun isOnBoardTutorialCompleted():Boolean{
      return  isOnBoardTutorialCompletedStatus.firstOrNull() ?: false
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //check for update
    private val _isUpdateRequired = MutableLiveData<ResultState<Boolean>>()
    fun isUpdateRequiredResult(): LiveData<ResultState<Boolean>> = _isUpdateRequired

    private var _inactivityResponseData = MutableLiveData<InactivityModel>()
    fun inactivityResponseData(): LiveData<InactivityModel> = _inactivityResponseData

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    fun checkForUpdates() {
        //TODO for now update required is false
        _isUpdateRequired.value = ResultState.Success(false)
        //TODO update for this app is checked by firebase for now it's commented
//        val remoteConfig = FirebaseRemoteConfig.getInstance()
//        val configSettings = FirebaseRemoteConfigSettings.Builder()
//            .setMinimumFetchIntervalInSeconds(0)
//            .build()
//        remoteConfig.setConfigSettingsAsync(configSettings)
//        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
//        remoteConfig.fetchAndActivate().addOnCompleteListener {
//            if (it.isSuccessful) {
//                try {
//                    val androidVersion = remoteConfig.getString("android_version")
//                    val forceUpdate = remoteConfig.getString("android_force_update")
//                    //  val updateMessage = remoteConfig.getString("android_update_message")
//                    //  val otherMessage = remoteConfig.getString("android_other_message")
//                    val packageInfo =
//                        packageManager.getPackageInfo(packageName, 0)
//                    val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                        packageInfo.longVersionCode.toInt()
//                    } else {
//                        packageInfo.versionCode
//                    }
//                    if (forceUpdate.toInt() == 1) {
//                        if (androidVersion.toInt() > versionCode) {
//                            _isUpdateRequired.value = ResultState.Success(true)
//                            return@addOnCompleteListener
//                        }
//                    }
//                    _isUpdateRequired.value = ResultState.Success(false)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    _isUpdateRequired.value = ResultState.error(R.string.error_something_went_wrong)
//                }
//            } else {
//                _isUpdateRequired.value = ResultState.error(R.string.error_something_went_wrong)
//            }
//        }

    }

    fun inactivityStatus(token: String) {
        viewModelScope.launch {
            appRepository.getInactivity(token)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SplashViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {
                        _inactivityResponseData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                          //  AppUtils.callLogout(this@OtpViewModel)
                        } else {
                            /*_inactivityResponseData.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )*/
                            var errorResponse = JSONObject(response.errorBody()?.string())
                            if (errorResponse.has("message"))
                                _errorMessage.postValue(errorResponse.getString("message"))
                           /* else
                                _uploadError.postValue(ErrorUtils.parseError<CommonResponseModel>(response))*/
                        }
                    }
                }
        }
    }
}