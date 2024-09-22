package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface DoTransferUseCase {
    suspend fun execute (transactionRequest: TransactionRequest) : Result<TransactionResponse>

}

class DoTransferUseCaseImpl(val repo :DashboardRepository):DoTransferUseCase{
    override suspend fun execute(transactionRequest: TransactionRequest): Result<TransactionResponse> {
        return try {
            val response = repo.createTransferProcess(transactionRequest)
            if(response.isSuccessful)
                Result.success(response.body()!!)
            else
                Result.failure(Exception(response.errorBody()?.string()))
        }catch (e:Exception){
        Result.failure(e)
    }}
}