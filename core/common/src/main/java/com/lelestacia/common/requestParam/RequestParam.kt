package com.lelestacia.common.requestParam

data class RequestParam(
    val rating: AnimeRating? = null,
    val status: AnimeStatus? = null,
    val type: AnimeType? = null
)
