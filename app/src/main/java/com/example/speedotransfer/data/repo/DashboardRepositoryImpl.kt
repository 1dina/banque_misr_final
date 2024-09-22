package com.example.speedotransfer.data.repo

import android.util.Log
import com.auth0.jwt.JWT
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
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.repository.DashboardRepository
import retrofit2.Response

class DashboardRepositoryImpl(
    val apiService: BankingAPICallable,
    val encryptedSharedPreferences: SecureStorageDataSource
) : DashboardRepository {
    var accountId = 0
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
                      //  accountId = accountsList[0].id
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

    override suspend fun getUserAccountById(accountId: String): Response<AccountByIdResponse> {
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
                Log.e(
                    "trace",
                    "Error fetching account by ID: ${
                        response.errorBody()?.string()
                    } (Code: ${response.code()})"
                )
            }

            response
        } catch (e: Exception) {
            Log.e("trace", "Error fetching account by ID: ${e.message}")
            throw e
        }

    }

    override suspend fun doTransferProcess(transactionRequest: TransactionRequest): Response<TransactionResponse> {
        val accessToken = encryptedSharedPreferences.getAccessToken()
        return apiService.doTransferProcess("Bearer $accessToken", transactionRequest)
    }

    override suspend fun getTransactionHistory(transactionHistoryRequest: TransactionHistoryRequest): Response<TransactionHistoryResponse> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            transactionHistoryRequest.accountId = accountId
            Log.e("trace", "id in Transaction history fetched: ${accountId}")

            val response =
                apiService.getTransactionHistory("Bearer $accessToken", transactionHistoryRequest)
            if (response.isSuccessful) {
                Log.e("trace", "Transaction history fetched: ${response.body()?.size}")
            } else {
                Log.e(
                    "trace",
                    "Error fetching transaction history: ${response.errorBody()?.string()}"
                )
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error occurred while fetching transaction history: ${e.message}")
            throw e
        }
    }

    override suspend fun addToFav(favouriteAdditionResponse: FavouriteAdditionResponse): Response<FavouriteAdditionResponse> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            val decodedJWT = JWT.decode(accessToken)
            val userId = decodedJWT.getClaim("id").asInt()
            favouriteAdditionResponse.userId = userId
            val response =
                apiService.addToFav("Bearer $accessToken", favouriteAdditionResponse)
            if (response.isSuccessful) {
                Log.e("trace", "Faved Addition fetched: ${response.body()?.name}")
            } else {
                Log.e(
                    "trace",
                    "Error fetching Fav Addition: ${response.errorBody()?.string()}"
                )
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error occurred while fetching add to fav: ${e.message}")
            throw e
        }
    }

    override suspend fun getAllFav(): Response<List<FavouriteAdditionResponse>> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            Log.e("trace", "Access Token: $accessToken")
            val decodedJWT = JWT.decode(accessToken)
            val userId = decodedJWT.getClaim("id").asInt()
            Log.e("trace", "User ID from JWT: $userId")

            val response = apiService.getAllFav("Bearer $accessToken", userId)

            if (response.isSuccessful) {
                Log.e("trace", "Faved Items fetched: ${response.body()?.toString()}")
            } else {
                val errorCode = response.code()
                val errorBody = response.message()
                Log.e("trace", "Error fetching FavItems: Code $errorCode, Body: $errorBody")
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error occurred while fetching favItems: ${e.message}")
            throw e
        }
    }

    override suspend fun deleteFromFav(accountId: Int): Response<String> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            Log.e("trace", "Access Token: $accessToken")

            val response = apiService.deleteFromFav("Bearer $accessToken", accountId)

            if (response.isSuccessful) {
                Log.e("trace", "Faved Item deleted: ${response.body()}")
            } else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()
                Log.e("trace", "Error deleting FavItem: Code $errorCode, Body: $errorBody")
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error occurred while deleting favItems: ${e.message}")
            throw e
        }
    }

    override suspend fun getInfo(): Response <UserInfoResponse> {

        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
            val response = apiService.getInfo("Bearer $accessToken")
            if (response.isSuccessful) {
                accountId = response.body()!!.accounts.get(0).id
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

    override suspend fun updatePassword(passwords: Passwords): Response<String> {
        return try {
            val accessToken = encryptedSharedPreferences.getAccessToken()
                ?: throw Exception("Access Token is null")
            val response = apiService.updatePassword("Bearer $accessToken",passwords)
            if (response.isSuccessful) {
                Log.e("trace", "password being updated: ${response.body()!!}")
            } else {
                Log.e(
                    "trace",
                    "Error updating user pass : ${response.code()}"
                )
            }
            response
        } catch (e: Exception) {
            Log.e("trace", "Error updating password: ${e.message}")
            throw e
        }
    }

}