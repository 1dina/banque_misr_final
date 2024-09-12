package com.example.speedotransfer.repo

import android.util.Log
import com.example.speedotransfer.data.models.AccountByIdResponse
import com.example.speedotransfer.data.models.AccountCreationRequest
import com.example.speedotransfer.data.models.AccountCreationResponse
import com.example.speedotransfer.data.models.TransactionHistoryRequest
import com.example.speedotransfer.data.models.TransactionHistoryResponse
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.data.models.TransactionResponse
import com.example.speedotransfer.data.models.UserAccountsResponseItem
import com.example.speedotransfer.data.models.UserInfoResponse
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.repository.DashboardRepository
import retrofit2.Response
import kotlin.properties.Delegates

class DashboardRepositoryImpl(val apiService: BankingAPICallable,
                              val encryptedSharedPreferences: SecureStorageDataSource
): DashboardRepository {
    var accountId  = 0
    override suspend fun createAccount(
        account: AccountCreationRequest
    ): Response<AccountCreationResponse> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
                ?: throw Exception("Access Token is null")
            apiService.createAccount("Bearer $accessToken", account)
        } catch (e: Exception) {
            Log.e("trace", "Error creating account: ${e.message}")
            throw e
        }
    }

    override suspend fun getUserAccounts(): Response<ArrayList<UserAccountsResponseItem>> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
                ?: throw Exception("Access Token is null")

            val response = apiService.getUserAccounts("Bearer $accessToken")

            if (response.isSuccessful) {
                response.body()?.let { accountsList ->
                    if (accountsList.isNotEmpty()) {
                        accountId = accountsList[0].id
                        Log.d("trace", "Account ID assigned: $accountId")
                    } else {
                        Log.e("trace", "No accounts found")
                    }
                }
            } else {
                Log.e("trace", "Error fetching accounts: ${response.errorBody()?.string()}")
            }

            response
        } catch (e: Exception) {
            Log.e("trace", "Error fetching accounts: ${e.message}")
            throw e
        }
    }

    override suspend fun getUserAccountById(accountId : String): Response<AccountByIdResponse> {
       return try {

           val accessToken = encryptedSharedPreferences.getAccessToken()
           if (accessToken == null) {
               Log.e("trace", "Access Token is null")
               throw Exception("Access Token is null")
           }

           Log.d("trace", "Access Token for fetching account by ID: $accessToken")
           val response = apiService.getUserAccountById("Bearer $accessToken", accountId)

           if (response.isSuccessful) {
               Log.d("trace", "Successfully fetched account by ID: ${response.body()}")
           } else {
               Log.e("trace", "Error fetching account by ID: ${response.errorBody()?.string()} (Code: ${response.code()})")
           }

           response
       } catch (e: Exception) {
           Log.e("trace", "Error fetching account by ID: ${e.message}")
           throw e
       }

    }

    override suspend fun doTransferProcess(transactionRequest: TransactionRequest): Response<TransactionResponse> {
        val accessToken = encryptedSharedPreferences.getAccessToken()
        transactionRequest.senderAccountNum = accountId
        return apiService.doTransferProcess("Bearer $accessToken",transactionRequest)
    }

    override suspend fun getTransactionHistory(transactionHistoryRequest: TransactionHistoryRequest): Response<TransactionHistoryResponse> {
       return try
       {val accessToken = encryptedSharedPreferences.getAccessToken()
            transactionHistoryRequest.accountId = accountId
           val response = apiService.getTransactionHistory("Bearer $accessToken", transactionHistoryRequest)
        if (response.isSuccessful) {
            Log.e("trace", "Transaction history fetched: ${response.body()?.size}")
        } else {
            Log.e("trace", "Error fetching transaction history: ${response.errorBody()?.string()}")
        }
        response
    } catch (e: Exception) {
        Log.e("trace", "Error occurred while fetching transaction history: ${e.message}")
        throw e
    }
    }

    override suspend fun getInfo(): Response <UserInfoResponse> {
        // val accessToken = encryptedSharedPreferences.getAccessToken()
        //  return apiService.getInfo(accessToken!!)
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            val response = apiService.getInfo("Bearer $accessToken")
            if (response.isSuccessful) {
                Log.e("trace", "user info fetched: ${response.body()?.toString()}")
            } else {
                Log.e(
                    "trace",
                    "Error fetching user data : ${response}"
                )
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error occurred while fetching user data: ${e.message}")
            throw e

        }
    }

}