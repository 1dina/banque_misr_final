package com.example.speedotransfer.data.source.remote.retrofit

import com.example.speedotransfer.data.constants.Constants
import com.example.speedotransfer.data.source.remote.models.account.AccountByIdResponse
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationRequest
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationResponse
import com.example.speedotransfer.data.source.remote.models.transaction.history.Passwords
import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryResponse
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionResponse
import com.example.speedotransfer.data.source.remote.models.account.UserAccountsResponseItem
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterResponse
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.data.source.remote.models.user.UserLoginRequest
import com.example.speedotransfer.data.source.remote.models.user.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BankingAPICallable {
    @POST(Constants.SIGNUP_ENDPOINT)
    suspend fun createUser(@Body userAuthRegisterRequest: UserAuthRegisterRequest): Response<UserAuthRegisterResponse>

    @POST(Constants.SIGNIN_ENDPOINT)
    suspend fun logInUser(@Body loginRequest: UserLoginRequest): Response<UserLoginResponse>

    @POST(Constants.SIGNOUT_ENDPOINT)
    suspend fun signOutUser(@Body user: UserLoginResponse): Response<String>

    @POST(Constants.ACCOUNT_CREATION_ENDPOINT)
    suspend fun createAccount(
        @Header("Authorization") accessToken: String,
        @Body account: AccountCreationRequest
    ): Response<AccountCreationResponse>

    @GET(Constants.USER_ACCOUNTS_ENDPOINT)
    suspend fun fetchUserAccounts(
        @Header("Authorization")
        accessToken: String
    ): Response<ArrayList<UserAccountsResponseItem>>

    @GET(Constants.GET_USER_ACCOUNT_ID_ENDPOINT)
    suspend fun fetchUserAccountById(
        @Header("Authorization") accessToken: String,
        @Path("accountID") accountId: String
    )
            : Response<AccountByIdResponse>

    @POST(Constants.TRANSFER_PROCESS_ENDPOINT)
    suspend fun createTransferProcess(
        @Header("Authorization") accessToken: String,
        @Body transactionRequest: TransactionRequest
    ): Response<TransactionResponse>

    @POST(Constants.TRANSACTION_HISTORY_ENDPOINT)
    suspend fun fetchTransactionHistory(
        @Header("Authorization") accessToken: String,
        @Body transactionHistoryRequest: TransactionHistoryRequest
    ) : Response<TransactionHistoryResponse>
    @GET(Constants.INFO_ENDPOINT)
    suspend fun fetchInfo(@Header("Authorization") accessToken: String): Response<UserInfoResponse>

    @PUT(Constants.UPDATE_PASSWORD)
    suspend fun updatePassword(
        @Header("Authorization") accessToken: String,
        @Body passwords: Passwords
    ): Response<String>
    @POST(Constants.FAVOURITE_ADDITION_ENDPOINT)
    suspend fun addToFav(
        @Header("Authorization") accessToken: String,
        @Body favouriteAdditionResponse: FavouriteAdditionResponse
    ): Response<FavouriteAdditionResponse>

    @GET (Constants.ALL_FAVOURITES_ENDPOINT)
    suspend fun  fetchAllFav(
        @Header("Authorization")accessToken: String,
        @Path("userId") userId: Int
    ) : Response<List<FavouriteAdditionResponse>>

    @DELETE (Constants.DELETE_FAVOURITE_ENDPOINT)
    suspend fun deleteFromFav(
        @Header("Authorization")accessToken: String,
        @Path("accountId") accountId: Int
    ) : Response<String>
}