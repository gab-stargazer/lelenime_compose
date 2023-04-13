package com.lelestacia.network.model

import com.google.gson.annotations.SerializedName

data class GenericResponse<T>(
    @SerializedName("data")
    val data: T
)
