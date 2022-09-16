package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinkedInProfileModel(
    @SerializedName("firstName")
    val firstName: FirstName = FirstName(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("lastName")
    val lastName: LastName = LastName(),
    @SerializedName("profilePicture")
    val profilePicture: ProfilePicture = ProfilePicture()
):Serializable {
    data class FirstName(
        @SerializedName("localized")
        val localized: Localized = Localized(),
        @SerializedName("preferredLocale")
        val preferredLocale: PreferredLocale = PreferredLocale()
    ):Serializable

    data class LastName(
        @SerializedName("localized")
        val localized: Localized = Localized(),
        @SerializedName("preferredLocale")
        val preferredLocale: PreferredLocale = PreferredLocale()
    ):Serializable

    data class Localized(
        @SerializedName("en_US")
        val enUS: String = ""
    ):Serializable

    data class PreferredLocale(
        @SerializedName("country")
        val country: String = "",
        @SerializedName("language")
        val language: String = ""
    ):Serializable

    data class ProfilePicture(
        @SerializedName("displayImage")
        val displayImageStr: String = "",
        @SerializedName("displayImage~")
        val displayImage: DisplayImage = DisplayImage()
    ):Serializable

    data class DisplayImage(
        @SerializedName("elements")
        val elements: ArrayList<Element> = arrayListOf(),
        @SerializedName("paging")
        val paging: Paging = Paging()
    ):Serializable

    data class Element(
        @SerializedName("artifact")
        val artifact: String = "",
        @SerializedName("authorizationMethod")
        val authorizationMethod: String = "",
        @SerializedName("data")
        val `data`: Data = Data(),
        @SerializedName("identifiers")
        val identifiers: ArrayList<Identifier> = arrayListOf()
    ):Serializable

    data class Data(
        @SerializedName("com.linkedin.digitalmedia.mediaartifact.StillImage")
        val stillImage: StillImage = StillImage()
    ):Serializable

    data class StillImage(
        @SerializedName("displayAspectRatio")
        val displayAspectRatio: DisplayAspectRatio = DisplayAspectRatio(),
        @SerializedName("displaySize")
        val displaySize: DisplaySize = DisplaySize(),
        @SerializedName("mediaType")
        val mediaType: String = "",
        @SerializedName("rawCodecSpec")
        val rawCodecSpec: RawCodecSpec = RawCodecSpec(),
        @SerializedName("storageAspectRatio")
        val storageAspectRatio: StorageAspectRatio = StorageAspectRatio(),
        @SerializedName("storageSize")
        val storageSize: StorageSize = StorageSize()
    ):Serializable

    data class DisplayAspectRatio(
        @SerializedName("formatted")
        val formatted: String = "",
        @SerializedName("heightAspect")
        val heightAspect: Double = 0.0,
        @SerializedName("widthAspect")
        val widthAspect: Double = 0.0
    ):Serializable

    data class DisplaySize(
        @SerializedName("height")
        val height: Double = 0.0,
        @SerializedName("uom")
        val uom: String = "",
        @SerializedName("width")
        val width: Double = 0.0
    ):Serializable

    data class RawCodecSpec(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("type")
        val type: String = ""
    ):Serializable

    data class StorageAspectRatio(
        @SerializedName("formatted")
        val formatted: String = "",
        @SerializedName("heightAspect")
        val heightAspect: Double = 0.0,
        @SerializedName("widthAspect")
        val widthAspect: Double = 0.0
    ):Serializable

    data class StorageSize(
        @SerializedName("height")
        val height: Int = 0,
        @SerializedName("width")
        val width: Int = 0
    ):Serializable

    data class Identifier(
        @SerializedName("file")
        val `file`: String = "",
        @SerializedName("identifier")
        val identifier: String = "",
        @SerializedName("identifierExpiresInSeconds")
        val identifierExpiresInSeconds: Int = 0,
        @SerializedName("identifierType")
        val identifierType: String = "",
        @SerializedName("index")
        val index: Int = 0,
        @SerializedName("mediaType")
        val mediaType: String = ""
    ):Serializable


    data class Paging(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("links")
        val links: ArrayList<Any> = arrayListOf(),
        @SerializedName("start")
        val start: Int = 0
    ):Serializable
}