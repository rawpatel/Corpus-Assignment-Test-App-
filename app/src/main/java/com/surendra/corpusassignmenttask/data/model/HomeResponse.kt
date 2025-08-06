package com.surendra.corpusassignmenttask.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("responseStatus")
    val responseStatus: ResponseStatus,
    @SerializedName("content")
    val content: List<HomeContent>
)

data class ResponseStatus(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("statusMessage")
    val statusMessage: String
)

data class HomeContent(
    @SerializedName("title")
    val title: String,
    @SerializedName("command")
    val command: String,
    @SerializedName("content")
    val content: List<ContentItem>,
    @SerializedName("contentType")
    val contentType: String,
    @SerializedName("bigBannerAdValue")
    val bigBannerAdValue: String,
    @SerializedName("isBigBannerAdEnabled")
    val isBigBannerAdEnabled: Boolean,
    @SerializedName("iconType")
    val iconType: String? = null,
    @SerializedName("isSubscriberSpecific")
    val isSubscriberSpecific: Boolean,
    @SerializedName("displayNumberSeries")
    val displayNumberSeries: Boolean,
    @SerializedName("adImageType")
    val adImageType: String? = null,
    @SerializedName("displayOrder")
    val displayOrder: Int
)

data class ContentItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("stbCarouselImage")
    val stbCarouselImage: String? = null,
    @SerializedName("otherDeviceCarouselImage")
    val otherDeviceCarouselImage: String? = null,
    @SerializedName("mobileCarouselImage")
    val mobileCarouselImage: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("shareUrl")
    val shareUrl: String? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("contentType")
    val contentType: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("onScreenAction")
    val onScreenAction: String? = null,
    @SerializedName("externalLink")
    val externalLink: String? = null,
    @SerializedName("franchiseId")
    val franchiseId: Int? = null
)
