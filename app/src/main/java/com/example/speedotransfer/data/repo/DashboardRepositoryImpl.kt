package com.example.speedotransfer.data.repo

import com.auth0.jwt.JWT
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.data.source.remote.models.account.AccountByIdResponse
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationRequest
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationResponse
import com.example.speedotransfer.data.source.remote.models.account.UserAccountsResponseItem
import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionResponse
import com.example.speedotransfer.data.source.remote.models.transaction.history.Passwords
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryResponse
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.domain.repository.DashboardRepository
import retrofit2.Response

class DashboardRepositoryImpl(
    val apiService: BankingAPICallable,
    private val encryptedSharedPreferences: SecureStorageDataSource
) : DashboardRepository {
    var accountId = 0
    override suspend fun createAccount(
        account: AccountCreationRequest
    ): Response<AccountCreationResponse> {
        return try {
            apiService.createAccount( account)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchUserAccounts(): Response<ArrayList<UserAccountsResponseItem>> {
        return try {
            val response = apiService.fetchUserAccounts()
            response
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchUserAccountById(accountId: String): Response<AccountByIdResponse> {
        return try {
            apiService.fetchUserAccountById( accountId)
        } catch (e: Exception) {
            throw e
        }

    }

    override suspend fun createTransferProcess(transactionRequest: TransactionRequest): Response<TransactionResponse> {
        return apiService.createTransferProcess( transactionRequest)
    }

    override suspend fun fetchTransactionHistory(transactionHistoryRequest: TransactionHistoryRequest): Response<TransactionHistoryResponse> {
        return try {
            transactionHistoryRequest.accountId = accountId
            apiService.fetchTransactionHistory( transactionHistoryRequest)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun addToFav(favouriteAdditionResponse: FavouriteAdditionResponse): Response<FavouriteAdditionResponse> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            val decodedJWT = JWT.decode(accessToken)
            val userId = decodedJWT.getClaim("id").asInt()
            favouriteAdditionResponse.userId = userId
            apiService.addToFav(favouriteAdditionResponse)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchAllFav(): Response<List<FavouriteAdditionResponse>> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            val decodedJWT = JWT.decode(accessToken)
            val userId = decodedJWT.getClaim("id").asInt()
            apiService.fetchAllFav( userId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteFromFav(accountId: Int): Response<String> {
        return try {
            apiService.deleteFromFav(accountId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchInfo(): Response<UserInfoResponse> {

        return try {
            val response = apiService.fetchInfo()
            if (response.isSuccessful ) {
                accountId = response.body()!!.accounts[0].id
            }
            response
        } catch (e: Exception) {
            throw e

        }
    }

    override suspend fun updatePassword(passwords: Passwords): Response<String> {
        return try {
            apiService.updatePassword( passwords)
        } catch (e: Exception) {
            throw e
        }
    }

}