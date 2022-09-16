package com.swipefwd.data.models

data class UserContactDetails(
    val id: String = "",
    var numberType: String = "",
    val name: String? = "",
    var formattedNumber: String? = "", //+91-987-689-86
    var phonebookNumber: String? = "", //which is saved in phonebook
    var simpleCountryCodeNumber: String? = "", //+919834567890
    var simpleNumber: String? = "", //+919834567890
    var isSelected: Boolean = false,
    var imageProfile: String? = "",
    var friendsOnFwd: Int? = 0,
    var isInvited: Boolean = false,
    var reinvited: Boolean = false,
    var user_id: Int? = 0,
    var invitedStage: String? = "",
)