package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.AccountByIdResponse
import com.example.speedotransfer.data.models.AccountCreationRequest
import com.example.speedotransfer.data.models.AccountCreationResponse
import com.example.speedotransfer.data.models.TransactionHistoryRequest
import com.example.speedotransfer.data.models.TransactionHistoryResponse
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.data.models.TransactionResponse
import com.example.speedotransfer.data.models.UserAccountsResponseItem
import retrofit2.Response

interface DashboardRepository {
    suspend fun createAccount(account : AccountCreationRequest) : Response<AccountCreationResponse>
    suspend fun getUserAccounts() : Response<ArrayList<UserAccountsResponseItem>>
    suspend fun getUserAccountById(accountId : String) : Response<AccountByIdResponse>
    suspend fun doTransferProcess(transactionRequest: TransactionRequest) : Response<TransactionResponse>
    suspend fun getTransactionHistory (transactionHistoryRequest: TransactionHistoryRequest)
    : Response<TransactionHistoryResponse>

}