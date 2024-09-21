package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.account.AccountByIdResponse
import com.example.speedotransfer.data.models.account.AccountCreationRequest
import com.example.speedotransfer.data.models.account.AccountCreationResponse
import com.example.speedotransfer.data.models.transaction.history.Passwords
import com.example.speedotransfer.data.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.models.transaction.history.TransactionHistoryResponse
import com.example.speedotransfer.data.models.transaction.TransactionRequest
import com.example.speedotransfer.data.models.transaction.TransactionResponse
import com.example.speedotransfer.data.models.account.UserAccountsResponseItem
import com.example.speedotransfer.data.models.user.info.UserInfoResponse
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