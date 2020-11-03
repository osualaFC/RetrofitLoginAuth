package com.example.retrofitauth.data

import com.example.retrofitauth.data.request.LoginRequest
import com.example.retrofitauth.data.responses.LoginResponse
import com.example.retrofitauth.data.responses.PostsResponse
import com.example.retrofitauth.utils.Constants
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<PostsResponse>

}

