package com.example.speedotransfer.data.repo

import com.auth0.jwt.JWT
import com.example.speedotransfer.data.source.remote.models.AuthData
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterResponse
import com.example.speedotransfer.data.source.remote.models.user.UserLoginRequest
import com.example.speedotransfer.data.source.remote.models.user.UserLoginResponse
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    val apiService: BankingAPICallable,
    private val encryptedSharedPreferences: SecureStorageDataSource
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
                val decodedJWT = JWT.decode(accessToken)
                val userId = decodedJWT.getClaim("id").asInt()
                AuthData(userId)
            } else {
                    throw Exception("Invalid Email or Password")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String) {
        encryptedSharedPreferences.saveToken(accessToken, refreshToken)
    }

    override suspend fun fetchAccessToken(): String? {
        return encryptedSharedPreferences.getAccessToken()
    }

    override suspend fun fetchRefreshToken(): String? {
        return encryptedSharedPreferences.getRefreshToken()
    }

    override suspend fun signOutUser(): Response<String> {
        val accessToken = encryptedSharedPreferences.getAccessToken()
        val refreshToken  = encryptedSharedPreferences.getRefreshToken()
        saveToken("","")
        return apiService.signOutUser(UserLoginResponse(refreshToken!!,accessToken!!))
    }


}