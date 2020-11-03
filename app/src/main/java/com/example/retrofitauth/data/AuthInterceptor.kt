package com.example.retrofitauth.data

import android.content.Context
import com.example.retrofitauth.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}

/***FOR WHEN TOKEN EXPIRES***/
//interface ApiRefreshToken {
//    companion object {
//        private const val REFRESH_TOKEN = "/refreshToken"
//    }
//
//    @FormUrlEncoded
//    @POST(REFRESH_TOKEN)
//    fun refreshToken(@Field("refreshToken") refreshToken: String?): Call<TokenModel>
//}


//private fun createClientAuth(): OkHttpClient {
//    //ADD DISPATCHER WITH MAX REQUEST TO 1
//    val dispatcher = Dispatcher()
//    dispatcher.maxRequests = 1
//    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
//    okHttpClientBuilder.dispatcher(dispatcher)
//    okHttpClientBuilder.addInterceptor(AuthenticationInterceptorRefreshToken(this, UserManager()))
//    return okHttpClientBuilder.build()
//}
//}
//
//class AuthenticationInterceptorRefreshToken @Inject constructor(private val retrofitBuilder: RetrofitBuilder, private val userManager: UserManager) : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response? {
//        //MAKE SYNCHRONIZED
//        synchronized(this) {
//            val originalRequest = chain.request()
//            val authenticationRequest = originalRequest.newBuilder()
//                .addHeader("Authorization", "Bearer ${userManager.load()?.accessToken}")
//                .addHeader("appVersion", BuildConfig.VERSION_NAME).build()
//            val initialResponse = chain.proceed(authenticationRequest)
//
//            when {
//                initialResponse.code() == 403 || initialResponse.code() == 401 -> {
////RUN BLOCKING!!
//val responseNewTokenLoginModel = runBlocking {
//    retrofitBuilder.retrofit().create(ApiRefreshToken::class.java).refreshToken(userManager.load()?.refreshToken).execute()
//}
//
//return when {
//    responseNewTokenLoginModel == null || responseNewTokenLoginModel.code() != 200 -> {
//        AuthManager().authExpiredAndGoLogin(AndroidApplication().getContext())
//        null
//    }
//    else -> {
//        responseNewTokenLoginModel.body()?.accessToken?.let {
//            userManager.updateToken(it)
//        }
//        val newAuthenticationRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer " + responseNewTokenLoginModel.body()?.accessToken).build()
//        chain.proceed(newAuthenticationRequest)
//    }
//}
//}
//else -> return initialResponse
//}
//}
//}
/////////initialResponse.close()
//}
/////////////https://dev.to/mohitrajput987/refresh-authentication-token-in-retrofit-without-modifying-any-call-1ogb


//private fun request(originalRequest: Request): Request {
//    return originalRequest.newBuilder()
//        .addHeader("Authorization", "Bearer ${userManager.load()?.accessToken}")
//        .addHeader("appVersion", BuildConfig.VERSION_NAME).build()
//
//}

//class RetrofitBuilder {
//
//    fun retrofitAuth(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .client(createClientAuth())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    private fun createClientAuth(): OkHttpClient {
//        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
//
//        okHttpClientBuilder.addInterceptor(AuthenticationInterceptorRefreshToken(this, UserManager()))
//        return okHttpClientBuilder.build()
//    }
//}