package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetTransactionsUseCase {
    suspend fun execute(transactionHistoryRequest: TransactionHistoryRequest): Result<TransactionHistoryResponse>

}

class GetTransactionsUseCaseImpl(val repo: DashboardRepository) : GetTransactionsUseCase {
    override suspend fun execute(transactionHistoryRequest: TransactionHistoryRequest): Result<TransactionHistoryResponse> {
        val response = repo.fetchTransactionHistory(transactionHistoryRequest)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            throw Exception(response.message())
        }
    }
}