package com.example.speedotransfer.domain.usecases

import android.util.Log
import com.example.speedotransfer.data.source.remote.models.account.AccountByIdResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetAccountByIdUseCase {
    suspend fun execute (accountId : String) : Result<AccountByIdResponse>

}

class GetAccountByIdUseCaseImpl(val dashboardRepository: DashboardRepository) :GetAccountByIdUseCase{
    override suspend fun execute(accountId: String): Result<AccountByIdResponse> {
        return try {
            val response = dashboardRepository.getUserAccountById(accountId)
            if(response.isSuccessful){
                Log.e("trace",response.body()!!.type)
                Result.success(response.body()!!)
            }else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()
                Log.e("trace", "Unauthorized: Code $errorCode, Body: $errorBody")
                val message = "there is no account with this if"
                Log.e("trace",response.message())
                throw Exception(message)
            }
        }catch (e:Exception){
            throw e
        }
    }
}