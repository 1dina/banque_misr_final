package com.example.speedotransfer.repo

import android.util.Log
import com.auth0.jwt.JWT
import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.repository.AuthRepository
import retrofit2.Response

class Repository(
    val apiService: BankingAPICallable,
    val encryptedSharedPreferences: SecureStorageDataSource
) : AuthRepository {
    override suspend fun createUserAuth(user: UserAuthRegisterRequest): Response<UserAuthRegisterResponse> {
        return apiService.createUser(user)
    }

    override suspend fun loginUserAuth(user: UserLoginRequest): AuthData {
        return try {
            val response = apiService.logInUser(user)
            if (response.isSuccessful) {
                val accessToken = response.body()?.accessToken
                val refreshToken = response.body()?.refreshToken
                if (accessToken != null && refreshToken != null)
                    saveToken(accessToken, refreshToken)
                Log.e("trace","accessToken $accessToken")
                Log.e("trace","refreshToken $refreshToken")

                val decodedJWT = JWT.decode(accessToken)
                val userId = decodedJWT.getClaim("id").asInt()
                AuthData(userId)
            } else {
                    Log.e("trace API ", "Error Body: ${response.errorBody()?.string()}")
                    throw Exception("Invalid Email or Password")

            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String) {
        encryptedSharedPreferences.saveToken(accessToken, refreshToken)
    }

    override suspend fun getAccessToken(): String? {
        return encryptedSharedPreferences.getAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return encryptedSharedPreferences.getRefreshToken()
    }

    override suspend fun signOutUser(): Response<String> {
        val accessToken = encryptedSharedPreferences.getAccessToken()
        val refreshToken  = encryptedSharedPreferences.getRefreshToken()
        Log.e("trace","accessToken $accessToken")
        Log.e("trace","refreshToken $refreshToken")
        saveToken("","")
        return apiService.signOutUser(UserLoginResponse(refreshToken!!,accessToken!!))

    }

}