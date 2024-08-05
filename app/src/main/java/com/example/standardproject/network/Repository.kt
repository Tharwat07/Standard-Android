package com.example.standardproject.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.net.toUri
import com.example.standardproject.utilities.SharedPreferencesManager
import com.example.standardproject.utilities.UserLoginData
import com.example.standardproject.utilities.prepareFileParts
import com.example.standardproject.utilities.shouldNavigateToLogin
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val webServices: WebServices,
    @ApplicationContext private val context: Context,
) {
    suspend fun login(userData: UserLoginData): Response<Any> {
        return safeApiCall { webServices.login(userData) }
    }


    suspend fun updateGroupInfo(updateGroupInfo: Any): Response<Any> {
        val id = "updateGroupInfo.id".toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val name = "updateGroupInfo.name?".toRequestBody("text/plain".toMediaTypeOrNull())
        val is_archived =
            "updateGroupInfo.is_archived?".toString()
                ?.toRequestBody("text/plain".toMediaTypeOrNull())
        val usersParts = listOf(1, 2, 3, 4).mapIndexed { index, userId ->
            MultipartBody.Part.createFormData("users[$index]", userId.toString())
        } ?: emptyList()

        val deletedUsersParts = listOf(5, 6, 7, 8, 9).mapIndexed { index, userId ->
            MultipartBody.Part.createFormData("deletedUsers[$index]", userId.toString())
        } ?: emptyList()

        val fileParts = prepareFileParts(
            "icon",
            listOf("updateGroupInfo.fileRealPaths!!"),
            listOf("updateGroupInfo.files!!".toUri()),
            context
        )


        return safeApiCall {
            webServices.updateGroupInfo(
                name = name,
                id = id,
                is_archived = is_archived,
                list = usersParts,
                list2 = deletedUsersParts,
                file = if (fileParts.isNotEmpty()) fileParts.first() else null
            )
        }
    }


    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Response<T> {
        if (!NetworkUtils.isNetworkConnected(context)) {
            return Response.error(408, "".toResponseBody("text/plain".toMediaTypeOrNull()))
        }

        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                response
            } else {
                if (response.code() == 401) {
                    SharedPreferencesManager.getInstance(context).clear()
                    shouldNavigateToLogin.postValue(true)
                }
                Response.error(
                    response.code(),
                    response.errorBody() ?: "".toResponseBody("text/plain".toMediaTypeOrNull())
                )
            }
        } catch (e: IOException) {
            Response.error(
                500, (e.message ?: e.toString()).toResponseBody("text/plain".toMediaTypeOrNull())
            )
        }
    }
}

object NetworkUtils {
    @SuppressLint("MissingPermission")
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}