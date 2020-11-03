package com.example.retrofitauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitauth.data.ApiClient
import com.example.retrofitauth.data.responses.PostsResponse
import com.example.retrofitauth.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiClient = ApiClient()

        sessionManager = SessionManager(this)

        fetchPosts()
    }

    /**
     * Function to fetch posts
     */
    private fun fetchPosts() {
        // Pass the token as parameter
        apiClient.getApiService(this).fetchPosts()
            .enqueue(object : Callback<PostsResponse> {
                override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                    // Error fetching posts
                }

                override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                    // Handle function to display posts
                }
            })
    }

    /**
     * when you login, the server returns a response containing ur authToken
     * You will need to save the authToken using sharedPefs
     * In your apiClient, create an Authenticator which will add to token to ur next requests
     * **/


}