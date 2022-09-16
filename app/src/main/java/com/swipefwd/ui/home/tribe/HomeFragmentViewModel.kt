package com.swipefwd.ui.home.tribe

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.telephony.PhoneNumberUtils
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar.getInstance


class HomeFragmentViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }
    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    val getRemainingUser = fwdDataStore.getInt(PreferenceKeys.PREF_REMAINING_INVITATION)
    val isNewUser = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_NEW_USER)

    val userFirstName = fwdDataStore.getString(PreferenceKeys.PREF_FIRST_NAME)
    val userLastName = fwdDataStore.getString(PreferenceKeys.PREF_LAST_NAME)
    val userEmail = fwdDataStore.getString(PreferenceKeys.PREF_RECOVERY_EMAIL)

    val getRemainingConnector =
        fwdDataStore.getInt(PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION)

    val getIsAccountTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP)
    val getConnectorTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_CONNECTOR_TOOL_TIP)
    val isInviteExpired = fwdDataStore.getBoolean(PreferenceKeys.PREF_INVITE_EXPIRED)
    val isSmsSent = fwdDataStore.getBoolean(PreferenceKeys.PREF_SMS_SENT)

    val getProfileImage = fwdDataStore.getString(PreferenceKeys.PREF_PROFILE_IMAGE)

    val isDisableAgreement = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_AGREE)

    val userPhoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)

    val isShowDelete = fwdDataStore.getBoolean(PreferenceKeys.PREF_DO_NOT_SHOW_DELETE_DATER)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _contactData = MutableLiveData<FwdContactListResponseModel>()
    var contactData: LiveData<FwdContactListResponseModel> = _contactData

   private var _daterContactData = MutableLiveData<ContactListResponseModel>()
    var daterContactData: LiveData<ContactListResponseModel> = _daterContactData

    private var _contactError = MutableLiveData<FwdContactListResponseModel>()
    var contactError: LiveData<FwdContactListResponseModel> = _contactError

    private var _inviteData = MutableLiveData<CommonResponseModel>()
    var inviteData: LiveData<CommonResponseModel> = _inviteData

    private var _suggestionModel = MutableLiveData<InviteModel>()
    var suggestionModel: LiveData<InviteModel> = _suggestionModel


    private var _error = MutableLiveData<CommonResponseModel>()
    var error: LiveData<CommonResponseModel> = _error

    private var _userTypeData = MutableLiveData<CommonResponseModel>()
    var userTypeData: LiveData<CommonResponseModel> = _userTypeData

    private var _userTypeError = MutableLiveData<CommonResponseModel>()
    var userTypeError: LiveData<CommonResponseModel> = _userTypeError

    private var _removeTribeData = MutableLiveData<CommonResponseModel>()
    var removeTribeData: LiveData<CommonResponseModel> = _removeTribeData

    private var _popupData = MutableLiveData<CommonResponseModel>()
    var popupData: LiveData<CommonResponseModel> = _popupData

    private var _activePendingData = MutableLiveData<ActiveExpireRequestListModel>()
    var activePendingData: LiveData<ActiveExpireRequestListModel> = _activePendingData

    private var _activePendingError = MutableLiveData<ActiveExpireRequestListModel>()
    var activePendingError: LiveData<ActiveExpireRequestListModel> = _activePendingError

    private var _tribeData =
        MutableLiveData<DaterConnectionResponseModel>()
    var tribeData: LiveData<DaterConnectionResponseModel> =
        _tribeData

   private var _tribeConnectionsData =
        MutableLiveData<TribeDaterConnectionsResponseModel>()
    var tribeConnectionsData: LiveData<TribeDaterConnectionsResponseModel> =
        _tribeConnectionsData


    private var _matchesData =
        MutableLiveData<MatchesResponseModel>()
    var matchesData: LiveData<MatchesResponseModel> =
        _matchesData


    private var _matchesError =
        MutableLiveData<MatchesResponseModel>()
    var matchesError: LiveData<MatchesResponseModel> =
        _matchesError

    private var _tribeError =
        MutableLiveData<DaterConnectionResponseModel>()
    var tribeError: LiveData<DaterConnectionResponseModel> =
        _tribeError

    private var _tribeAddedData = MutableLiveData<CommonResponseModel>()
    var tribeAddedData: LiveData<CommonResponseModel> = _tribeAddedData

    private var _tribeInvitedData = MutableLiveData<InviteConnectorResponseModel>()
    var tribeInvitedData: LiveData<InviteConnectorResponseModel> = _tribeInvitedData

    fun <T> removePreference(key: Preferences.Key<T>) {
        viewModelScope.launch {
            fwdDataStore.removePreference(key)
        }
    }

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    fun sendContactApi(token: String, jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendContactApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _contactData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _contactError.postValue(
                                ErrorUtils.parseError<FwdContactListResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }


   fun sendContactApi(jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            val headers = java.util.HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
            appRepository.sendContactApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _daterContactData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _contactError.postValue(
                                ErrorUtils.parseError<FwdContactListResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }


    private val _contactsLiveData = MutableLiveData<ArrayList<UserContactDetails>>()
    val contactsLiveData: LiveData<ArrayList<UserContactDetails>> = _contactsLiveData
    fun fetchContacts(context: Context, countryCode: String) {
        viewModelScope.launch {
            val contacts = withContext(Dispatchers.IO) {
                val contactsListAsync = async { getPhoneContacts(context) }
                val contactNumbersAsync = async { getContactNumbers(context, countryCode) }

                val contacts = contactsListAsync.await()
                val contactNumbers = contactNumbersAsync.await()

                contacts.onEach { userModel ->
                    contactNumbers[userModel.id]?.let { numbers ->
                        userModel.phonebookNumber = numbers[0].phonebookNumber
                        userModel.simpleCountryCodeNumber = numbers[0].simpleCountryCodeNumber
                        userModel.formattedNumber = numbers[0].formattedNumber
                        userModel.numberType = numbers[0].numberType
                        userModel.simpleNumber = numbers[0].simpleNumber
                    }
                }
            }
            _contactsLiveData.postValue(contacts)
        }
    }
    private fun getPhoneContacts(context: Context): ArrayList<UserContactDetails> {
        _showLoading.postValue(true)
        val contactsList = ArrayList<UserContactDetails>()
        val contactsCursor = context.contentResolver?.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        if (contactsCursor != null && contactsCursor.count > 0) {
            val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            while (contactsCursor.moveToNext()) {
                val id = contactsCursor.getString(idIndex)
                val name = contactsCursor.getString(nameIndex)
                if (name != null) {
                    contactsList.add(UserContactDetails(id = id, name = name))
                }
            }
            contactsCursor.close()
        }
        return contactsList
    }

    private fun getContactNumbers(
        context: Context,
        countryCode: String
    ): HashMap<String, ArrayList<UserContactDetails>> {
        val contactsNumber = HashMap<String, ArrayList<UserContactDetails>>()
        val phoneCursor: Cursor? = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (phoneCursor != null && phoneCursor.count > 0) {
            val contactIdIndex =
                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val numberIndex =
                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val typeIndex =
                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)
            while (phoneCursor.moveToNext()) {
                val contactId = phoneCursor.getString(contactIdIndex)
                var number: String = phoneCursor.getString(numberIndex)
                val type: Int = phoneCursor.getInt(typeIndex)
                //check if the map contains key or not, if not then create a new array list with number
                val phone = number
                var simplePhone = phone
                if (!number.startsWith("+")) {
                    number = countryCode + number
                }
                if (simplePhone.contains(countryCode)) {
                   simplePhone = simplePhone.apply {
                       replace(countryCode,"")}
                }

                if (contactsNumber.containsKey(contactId)) {
                    contactsNumber[contactId]?.add(
                        UserContactDetails(
                            id = contactId,
                            numberType = type.toString(),
                            phonebookNumber = phone,
                            simpleCountryCodeNumber = number.replace(
                                "[(,)]".toRegex(),
                                ""
                            ).replace("\\s".toRegex(), "")
                                .replace("-".toRegex(), ""),
                            formattedNumber = number.replace(
                                "\\s".toRegex(),
                                "-"
                            ).replace("[(,)]".toRegex(), ""),
                        simpleNumber = simplePhone
                        )
                    )
                } else {
                    contactsNumber[contactId] = arrayListOf(
                        UserContactDetails(
                            id = contactId,
                            numberType = type.toString(),
                            phonebookNumber = phone,
                            simpleCountryCodeNumber = number.replace(
                                "[(,)]".toRegex(),
                                ""
                            ).replace("\\s".toRegex(), "")
                                .replace("-".toRegex(), ""),
                            formattedNumber = number.replace(
                                "\\s".toRegex(),
                                "-"
                            ).replace("[(,)]".toRegex(), ""),
                            simpleNumber = simplePhone
                        )
                    )
                }
            }
            //contact contains all the number of a particular contact
            phoneCursor.close()
        }
        return contactsNumber
    }




    fun inviteContactApi(token: String, jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.inviteContactApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _inviteData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun suggestionProfileApi(token: String, jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.suggestionProfileApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _suggestionModel.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun tribeConnectionListApi(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.tribeConnectionListApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tribeData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _tribeError.postValue(
                                ErrorUtils.parseError<DaterConnectionResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

   fun getTribeConnectionListApi() {
        _showLoading.postValue(true)
        viewModelScope.launch {
            val headers = java.util.HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
            appRepository.tribeConnectionListApi(headers)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tribeConnectionsData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _tribeError.postValue(
                                ErrorUtils.parseError<DaterConnectionResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun changeUserTypeApi(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.changeUserTypeApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _userTypeData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _userTypeError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun removeTribeDater(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.removeTribeDaterApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@HomeFragmentViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _removeTribeData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun popupShow(token: String, id: Int) {
        viewModelScope.launch {
            appRepository.popupShowApi(token, id)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _popupData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun tribeConnectionApi(token: String, jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.tribeConnectionApi(token, jsonObject)
                .catch { e ->
                    //_showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    //_showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tribeAddedData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun inviteConnectorApi(jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            val headers = java.util.HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
            appRepository.inviteConnector(headers, jsonObject)
                .catch { e ->
                    //_showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    //_showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tribeInvitedData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getActiveExpiredRequestList(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getActivePendingRequestApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _activePendingData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _activePendingError.postValue(
                                ErrorUtils.parseError<ActiveExpireRequestListModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun addActiveExpiredRequestList(token: String, jsonObject: JsonObject) {
        //_showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.addActivePendingRequestApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _inviteData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@HomeFragmentViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }
}