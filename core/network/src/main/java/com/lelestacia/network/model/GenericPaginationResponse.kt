package com.lelestacia.network.model

data class GenericPaginationResponse<T>(
    val data: List<T>,
    val pagination: PaginationResponse
)