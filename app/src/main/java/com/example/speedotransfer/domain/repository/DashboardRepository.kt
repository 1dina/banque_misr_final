package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.AccountByIdResponse
import com.example.speedotransfer.data.models.AccountCreationRequest
import com.example.speedotransfer.data.models.AccountCreationResponse
import com.example.speedotransfer.data.models.historyResponse.Passwords
import com.example.speedotransfer.data.models.FavouriteAdditionResponse
import com.example.speedotransfer.data.models.historyResponse.TransactionHistoryRequest
import com.example.speedotransfer.data.models.historyResponse.TransactionHistoryResponse
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.data.models.TransactionResponse
import com.example.speedotransfer.data.models.userAccResponse.UserAccountsResponseItem
import com.example.speedotransfer.data.models.userInfoResponse.UserInfoResponse
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

    suspend fun addToFav(favouriteAdditionResponse: FavouriteAdditionResponse) : Response<FavouriteAdditionResponse>
    suspend fun getAllFav() : Response<List<FavouriteAdditionResponse>>
    suspend fun deleteFromFav(accountId: Int) : Response<String>

}