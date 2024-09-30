package com.example.speedotransfer.data.source.remote

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.screens.auth.AuthActivity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(
    private val secureStorage: SecureStorageDataSource,
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = secureStorage.getAccessToken()
        token?.let {
            Log.e("trace", "Access token is $token")
            request.addHeader("Authorization", "Bearer $it")
        }
        val response = chain.proceed(request.build())

        if (response.code == 403) {
            secureStorage.clearAccessToken()
            AppRoutes.isFirstTime = false
            val intent = Intent(context, AuthActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("showDialog", true)
            }
            context.startActivity(intent)
        }

        return response
    }
}
