package com.example.speedotransfer.data.models


data class TransactionHistoryRequest(
    var accountId: Int = 0,
    var page: Int =0,
    val size: Int=10
)


data class TransactionHistoryResponse(
    val content: List<Content>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)
