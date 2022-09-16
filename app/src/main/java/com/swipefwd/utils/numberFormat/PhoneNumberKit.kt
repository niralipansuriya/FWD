package com.swipefwd.utils.numberFormat

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import com.swipefwd.R
import com.swipefwd.utils.numberFormat.api.Country
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import com.swipefwd.utils.numberFormat.api.Phone
import com.swipefwd.utils.numberFormat.internal.core.Proxy
import com.swipefwd.utils.numberFormat.internal.ext.*
import com.swipefwd.utils.numberFormat.internal.ext.clearSpaces
import com.swipefwd.utils.numberFormat.internal.ext.default
import com.swipefwd.utils.numberFormat.internal.ext.doIfAttached
import com.swipefwd.utils.numberFormat.internal.ext.io
import com.swipefwd.utils.numberFormat.internal.io.FileReader
import com.swipefwd.utils.numberFormat.internal.model.State
import com.swipefwd.utils.numberFormat.internal.pattern.CountryPattern
import java.lang.StringBuilder
import java.lang.ref.WeakReference
import java.util.*

class PhoneNumberKit private constructor(
    private val context: Context,
    private val isIconEnabled: Boolean,
    private val excludedCountries: List<String>,
    private val admittedCountries: List<String>,
) {

    private val supervisorJob = SupervisorJob()

    private val scope = CoroutineScope(supervisorJob + Dispatchers.Main)

    private val proxy: Proxy by lazy { Proxy(context) }

    private val state: MutableStateFlow<State> = MutableStateFlow(State.Ready)

    private var input: WeakReference<EditText> = WeakReference(null)

    private val countriesCache = mutableListOf<Country>()

    private var inputValue: CharSequence?
        get() = input.get()?.text
        set(value) {
            input.get()?.setText(value) // code entered like 1
        }

    val isValid: Boolean get() = validate(inputValue)

    init {
        scope.launch(Dispatchers.IO) {
            countriesCache.addAll(getCountries())
        }
    }

    private var textChangedListener: MaskedTextChangedListener? = null

    private fun setupListener(editText: EditText, pattern: String) {
        editText.removeTextChangedListener(textChangedListener)
        textChangedListener = installOn(
            editText,
            pattern,
            emptyList(),
            AffinityCalculationStrategy.WHOLE_STRING,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    Log.e(
                        "printPhone>>>",
                        "${maskFilled} and ${extractedValue} and ${formattedValue}"
                    )
                    val state = this@PhoneNumberKit.state.value
                    if (state is State.Attached) {
                        val parsedNumber = proxy.parsePhoneNumber(
                            extractedValue.clearSpaces(),
                            state.country.iso2
                        )

                        if (state.country.code != parsedNumber?.countryCode) {
                            val country = countriesCache.findCountry(parsedNumber?.countryCode)
                            if (country != null) {
                                setCountry(country)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun setCountry(countryIso2: String) = scope.launch {
        val country = default {
            getCountries().findCountry(
                countryIso2.trim().lowercase(Locale.ENGLISH)
            )
        } ?: return@launch
        setCountry(country)
    }

    private fun setCountry(country: Country) {
        val formattedNumber = proxy.formatPhoneNumber(
            proxy.getExampleNumber(country.iso2)
        )
        Log.e("printPhone", formattedNumber.toString())
        val pattern = CountryPattern.create(
            formattedNumber.orEmpty()
        )
        state.value = State.Attached(
            country = country,
            pattern = pattern
        )

    }


    fun getFormattedNumber(countryCode: String): String? {
        val formattedNumber = proxy.formatPhoneNumber(
            proxy.getExampleNumber(countryCode)
        )
        val pattern = CountryPattern.create(
            formattedNumber.orEmpty()
        )
        val defaultPattern = State.AttachedWithoutCountry(pattern = pattern)
        var patternWithoutCode : StringBuilder = StringBuilder("")
        var splittedNumber = defaultPattern.toString().split(" ")

      splittedNumber.forEachIndexed { index, s ->
            if (index != 0) {
                patternWithoutCode.append(s)
                if (splittedNumber.size >2 && splittedNumber.size-1 != index)
                 patternWithoutCode.append("-")
            }
        }

        var finalPattern = patternWithoutCode.toString()/*.replace("[","").replace("]","")*/.replace(")","").trim()
        return finalPattern
        /*var reqPattern = ""
        var finalFormat : StringBuilder = StringBuilder("")
        if (formattedNumber != null && formattedNumber.isNotEmpty()) {
            reqPattern = if (formattedNumber.toString().contains(" "))
                formattedNumber.replace(" ","-")
            else
                formattedNumber
            val splitedNumber =  reqPattern.split(" ")
            if (splitedNumber.isNotEmpty()) {
                splitedNumber.forEachIndexed { index, s ->
                    if (index != 0) {
                        finalFormat.append(s)
                        finalFormat.append(" ")
                    }

                }
               return finalFormat.trim().toString()
            }
        }*/
    }

    fun attachToInput(
        input: EditText,
        defaultCountry: Int,
    ) {
        this.input = WeakReference(input)
        scope.launch {
            val country = default {
                getCountries().findCountry(defaultCountry)
            }
            if (country != null) {
                attachToInput(input, country)
            }
        }
    }

    fun attachToInput(
        input: EditText,
        countryIso2: String,
    ) {
        this.input = WeakReference(input)
        scope.launch {
            val country = default {
                getCountries().findCountry(
                    countryIso2.trim().lowercase(Locale.ENGLISH)
                )
            }
            if (country != null) {
                attachToInput(input, country)
            }
        }
    }

    private fun collectState() = scope.launch {
        state.collect { state ->
            when (state) {
                is State.Ready -> {
                }
                is State.Attached -> {
                    /* if (isIconEnabled) {
                         getFlagIcon(state.country.iso2)?.let { icon ->
                           //  input.get()?.startIconDrawable = icon
                         }
                     }*/
                    input.get()?.let { editText ->
                        setupListener(editText, state.pattern)
                    }
                    if (inputValue.isNullOrEmpty()) {
                        inputValue = state.country.code.toString()
                    }
                }
            }
        }
    }

    private suspend fun getCountries() = io {
        if (countriesCache.isEmpty()) {
            FileReader.readAssetFile(context, ASSET_FILE_NAME).toCountryList()
        } else {
            countriesCache
        }
    }

    private fun clearInputValue() {
        inputValue = ""
    }

    private fun attachToInput(
        input: EditText,
        country: Country,
    ) {
        input.inputType = InputType.TYPE_CLASS_PHONE

// input.isStartIconVisible = isIconEnabled
//  input.setStartIconTintList(null)

        collectState()
        setCountry(country.iso2)
    }


    /**
     * Parses raw phone number into phone object
     */
    fun parsePhoneNumber(number: String?, defaultRegion: String?): Phone? {
        proxy.parsePhoneNumber(number, defaultRegion)?.let { phone ->
            return Phone(
                nationalNumber = phone.nationalNumber,
                countryCode = phone.countryCode,
                rawInput = phone.rawInput,
                numberOfLeadingZeros = phone.numberOfLeadingZeros
            )
        }
        return null
    }

    /**
     * Formats raw phone number into international phone
     */
    fun formatPhoneNumber(number: String?, defaultRegion: String?): String? {
        return proxy.formatPhoneNumber(proxy.parsePhoneNumber(number, defaultRegion))
    }

    /**
     * Provides an example phone number according to country iso2 code
     */
    fun getExampleNumber(iso2: String?): Phone? {
        proxy.getExampleNumber(iso2)?.let { phone ->
            return Phone(
                nationalNumber = phone.nationalNumber,
                countryCode = phone.countryCode,
                rawInput = phone.rawInput,
                numberOfLeadingZeros = phone.numberOfLeadingZeros
            )
        }
        return null
    }

    /**
     * Provides country flag icon for given country iso2 code
     */
    fun getFlagIcon(iso2: String?): Drawable? {
        return try {
            ContextCompat.getDrawable(
                context, context.resources.getIdentifier(
                    "country_flag_$iso2",
                    "drawable",
                    context.packageName
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun List<Country>.findCountry(
        countryCode: Int?
    ) = this.filter {
        admittedCountries.isEmpty() || admittedCountries.contains(it.iso2)
    }.filterNot {
        excludedCountries.contains(it.iso2)
    }.firstOrNull {
        it.code == countryCode
    }

    private fun List<Country>.findCountry(
        countryIso2: String?
    ) = this.filter {
        admittedCountries.isEmpty() || admittedCountries.contains(it.iso2)
    }.filterNot {
        excludedCountries.contains(it.iso2)
    }.firstOrNull {
        it.iso2 == countryIso2
    }

    private fun validate(number: CharSequence?): Boolean {
        if (number == null) return false
        return state.value.doIfAttached {
            proxy.validateNumber(number.toString(), country.iso2)
        } ?: false
    }

    fun validateNumber(number: String, countryCode: String): Boolean {
      //  return proxy.validateNumber(number, countryCode)
        if (number == null) return false
        return state.value.doIfAttached {
            proxy.validateNumber(number, countryCode)
        } ?: false
    }

    companion object {
        const val ASSET_FILE_NAME = "countries.json"
    }

    class Builder(private val context: Context) {

        private var isIconEnabled: Boolean = true

        private var excludedCountries: List<String>? = null

        private var admittedCountries: List<String>? = null

        fun setIconEnabled(isEnabled: Boolean): Builder {
            this.isIconEnabled = isEnabled
            return this
        }

        fun excludeCountries(countries: List<String>): Builder {
            this.excludedCountries = countries
            return this
        }

        fun admitCountries(countries: List<String>): Builder {
            this.admittedCountries = countries
            return this
        }

        fun build(): PhoneNumberKit {
            return PhoneNumberKit(
                context,
                isIconEnabled,
                excludedCountries.orEmpty(),
                admittedCountries.orEmpty()
            )
        }
    }
}
