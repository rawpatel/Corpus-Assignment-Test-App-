package com.surendra.corpusassignmenttask.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("responseStatus")
    val responseStatus: ResponseStatus,
    @SerializedName("content")
    val content: List<ContentSection>
)
