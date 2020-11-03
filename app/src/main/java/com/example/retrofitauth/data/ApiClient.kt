package com.example.retrofitauth.data

import android.content.Context
import com.example.retrofitauth.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    private lateinit var apiService: ApiService

    fun getApiService(context: Context): ApiService {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

}

//class ApiClient {
//
//    companion object{
//
//        private val retrofit by lazy{
//            /**enables you to log responses of retrofit--check dependency**/
//            val logging = HttpLoggingInterceptor()
//            /**enables u to see the actual response**/
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//            /**setUp client*/
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build()
//
//            /**Retrofit builder*/
//            Retrofit.Builder()
//                .baseUrl("http://api.larntech.net/")
//                /**determine how the response should be interpreted--use Gson to convert json to kotlin object**/
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//        /**get api instance--this will be use to make request calls**/
//        val api by lazy{
//            /**pass in ur api interface**/
//            retrofit.create(AuthService::class.java)
//        }
//    }
//}