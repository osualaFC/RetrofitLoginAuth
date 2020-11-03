package com.example.retrofitauth.data

import com.example.retrofitauth.data.responses.PostsResponse
import com.example.retrofitauth.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(): Call<PostsResponse>

}

