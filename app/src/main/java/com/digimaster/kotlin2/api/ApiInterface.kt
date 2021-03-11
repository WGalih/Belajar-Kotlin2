package com.digimaster.kotlin2.api

import com.digimaster.kotlin2.model.LoginResponseModel
import com.digimaster.kotlin2.model.NewsResponseModel
import com.digimaster.kotlin2.util.Constans.Companion.API_KEY
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("user/login")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Single<LoginResponseModel>

    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Query("email") email: String, @Query("name") name: String,
        @Query("address") address: String, @Query("dob") dob: String,
        @Query("password") password: String
    ): Single<LoginResponseModel>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponseModel>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponseModel>
}