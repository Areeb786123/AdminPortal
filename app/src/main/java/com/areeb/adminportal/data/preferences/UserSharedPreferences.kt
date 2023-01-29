package com.areeb.adminportal.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.areeb.adminportal.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.M)
@Singleton
class UserSharedPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    val sharedPreferences: SharedPreferences

    companion object {
        private const val USER_TOKEN = " userToken"
    }

    init {
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            Constants.SharedPreferences.BRANCH_PREFERENCES,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(token: String?) {
        sharedPreferences.edit()
            .putString(Constants.AuthDetails.USER_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(
            Constants.AuthDetails.USER_TOKEN,
            null
        )
    }

    fun clear(key: String) {
        if (sharedPreferences.all.containsKey(key)) {
            val editor = sharedPreferences.edit()
            editor.remove(key)
            editor.apply()
        }
    }
}
