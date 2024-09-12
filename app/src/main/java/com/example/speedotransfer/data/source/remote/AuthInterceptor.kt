package com.example.speedotransfer.data.source.remote

import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(
    private val secureStorage: SecureStorageDataSource,
    private val onUnauthorized: () -> Unit
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = secureStorage.getAccessToken()
        token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }

        val response = chain.proceed(request.build())

        if (response.code == 403) {
            secureStorage.clearAccessToken()
            onUnauthorized()
        }

        return response
    }
}
