package com.surendra.corpusassignmenttask.data.model

import com.google.gson.annotations.SerializedName

data class ResponseStatus(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("statusMessage")
    val statusMessage: String
)
