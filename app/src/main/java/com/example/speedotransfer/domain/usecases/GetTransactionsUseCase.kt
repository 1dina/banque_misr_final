package com.example.speedotransfer.domain.usecases

import android.util.Log
import com.example.speedotransfer.data.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.models.transaction.history.TransactionHistoryResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetTransactionsUseCase {
    suspend fun execute(transactionHistoryRequest: TransactionHistoryRequest) : Result<TransactionHistoryResponse>

}
class GetTransactionsUseCaseImpl (val repo : DashboardRepository) :GetTransactionsUseCase {
    override suspend fun execute(transactionHistoryRequest: TransactionHistoryRequest): Result<TransactionHistoryResponse> {
        return try {
            val response = repo.getTransactionHistory(transactionHistoryRequest)
            if(response.isSuccessful){
                Log.e("trace",response.body()!!.totalPages.toString())
                Result.success(response.body()!!)
            }else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()
                Log.e("trace", "Unauthorized: Code $errorCode, Body: $errorBody")
                Log.e("trace",response.message())
                throw Exception(response.message())
            }
        }catch (e:Exception){
            throw e

        }
    }
}