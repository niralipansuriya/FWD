package com.swipefwd.data.models

data class SwipingCustomModel(
    val connectorIds: List<Int?>? = listOf(),
    val id: Int = 0,
    val profileName: String = "",
    var userProfileModel: SwipingProfileModel.ProfileModel? = SwipingProfileModel.ProfileModel(),
    var connectorProfileModel: ArrayList<SwipingProfileModel.ProfileModel> = ArrayList(),
    var hasLiked: Boolean = false,
    var hasSuperLiked: Boolean = false

)
