package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface DoTransferUseCase {
    suspend fun execute(transactionRequest: TransactionRequest): Result<TransactionResponse>

}

class DoTransferUseCaseImpl(val repo: DashboardRepository) : DoTransferUseCase {
    override suspend fun execute(transactionRequest: TransactionRequest): Result<TransactionResponse> {
        val response = repo.createTransferProcess(transactionRequest)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else
            Result.failure(Exception(response.errorBody()?.string()))
    }
}