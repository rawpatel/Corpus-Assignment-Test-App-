package com.surendra.corpusassignmenttask.data.model

import com.google.gson.annotations.SerializedName

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
