package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.UserInfoResponse
import retrofit2.Response

interface ProfileRepository {

    suspend fun getInfo(): Response<UserInfoResponse>
}