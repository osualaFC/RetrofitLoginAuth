package com.example.retrofitauth.data.responses

import com.example.retrofitauth.data.model.Post
import com.google.gson.annotations.SerializedName

data class PostsResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("posts")
    var posts: List<Post>
)