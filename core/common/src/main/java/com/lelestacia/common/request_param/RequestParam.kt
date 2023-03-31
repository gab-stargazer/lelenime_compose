package com.lelestacia.common.request_param

data class RequestParam(
    val rating: AnimeRating? = null,
    val status: AnimeStatus? = null,
    val type: AnimeType? = null
)
