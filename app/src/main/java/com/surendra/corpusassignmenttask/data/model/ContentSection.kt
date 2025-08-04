package com.surendra.corpusassignmenttask.data.model

import com.google.gson.annotations.SerializedName

data class ContentSection(
    @SerializedName("title")
    val title: String,
    @SerializedName("command")
    val command: String,
    @SerializedName("content")
    val content: List<ContentItem>,
    @SerializedName("contentType")
    val contentType: String,
    @SerializedName("bigBannerAdValue")
    val bigBannerAdValue: String?,
    @SerializedName("isBigBannerAdEnabled")
    val isBigBannerAdEnabled: Boolean,
    @SerializedName("iconType")
    val iconType: String?,
    @SerializedName("isSubscriberSpecific")
    val isSubscriberSpecific: Boolean,
    @SerializedName("displayNumberSeries")
    val displayNumberSeries: Boolean,
    @SerializedName("adImageType")
    val adImageType: String?,
    @SerializedName("displayOrder")
    val displayOrder: Int
)

