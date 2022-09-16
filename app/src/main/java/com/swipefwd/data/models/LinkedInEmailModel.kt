package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinkedInEmailModel(
    @SerializedName("elements")
    val elements: List<Element> = listOf()
) : Serializable {

    data class Element(
        @SerializedName("handle~")
        val handle: Handle = Handle(),
        @SerializedName("handle")
        val handleStr: String = ""
    ) : Serializable

    data class Handle(
        @SerializedName("emailAddress")
        val emailAddress: String = ""
    ) : Serializable
}