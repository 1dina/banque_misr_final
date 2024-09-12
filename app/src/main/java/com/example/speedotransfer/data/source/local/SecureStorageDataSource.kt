package com.example.speedotransfer.data.source.local

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureStorageDataSource(context: Context) {


    val appContext  = context.applicationContext

    private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

    private val masterKeyAlias =
        MasterKey.Builder(appContext).setKeyGenParameterSpec(keyGenParameterSpec).build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        appContext,
        "secure_prefs",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(accessToken: String,refreshToken:String) {
        sharedPreferences.edit().putString("access_token", accessToken)
            .putString("refresh_token",refreshToken).apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }
    fun getRefreshToken():String?{
        return sharedPreferences.getString("refresh_token", null)

    }
    fun clearAccessToken() {
        sharedPreferences.edit().remove("access_token").apply()
    }
}