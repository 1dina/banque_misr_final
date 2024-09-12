package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.AccountByIdResponse
import com.example.speedotransfer.data.models.AccountCreationRequest
import com.example.speedotransfer.data.models.AccountCreationResponse
import com.example.speedotransfer.data.models.Passwords
import com.example.speedotransfer.data.models.FavouriteAddition
import com.example.speedotransfer.data.models.TransactionHistoryRequest
import com.example.speedotransfer.data.models.TransactionHistoryResponse
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.data.models.TransactionResponse
import com.example.speedotransfer.data.models.UserAccountsResponseItem
import com.example.speedotransfer.data.models.UserInfoResponse
import retrofit2.Response

interface DashboardRepository {
    suspend fun createAccount(account : AccountCreationRequest) : Response<AccountCreationResponse>
    suspend fun getUserAccounts() : Response<ArrayList<UserAccountsResponseItem>>
    suspend fun getUserAccountById(accountId : String) : Response<AccountByIdResponse>
    suspend fun doTransferProcess(transactionRequest: TransactionRequest) : Response<TransactionResponse>
    suspend fun getTransactionHistory (transactionHistoryRequest: TransactionHistoryRequest)
    : Response<TransactionHistoryResponse>
    suspend fun getInfo(): Response<UserInfoResponse>
    suspend fun updatePassword(passwords: Passwords): Response<String>

    suspend fun addToFav(favouriteAddition: FavouriteAddition) : Response<FavouriteAddition>
    suspend fun getAllFav() : Response<List<FavouriteAddition>>
    suspend fun deleteFromFav(accountId: Int) : Response<String>

}