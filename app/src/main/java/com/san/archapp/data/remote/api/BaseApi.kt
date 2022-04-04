package com.san.archapp.data.remote.api

import com.san.archapp.data.entity.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface BaseApi {

    @GET("public/v1/users")
    suspend fun getUsers(): UserData
}