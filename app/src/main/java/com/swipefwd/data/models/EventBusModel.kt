package com.swipefwd.data.models

data class EventBusModel(
    var event: String = "",
    var activityName: String = "",
    var swipeDirection: Int = 1,
    var profileModel: SwipingProfileModel.ProfileModel
)
