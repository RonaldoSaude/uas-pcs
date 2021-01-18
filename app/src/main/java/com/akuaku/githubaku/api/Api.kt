package com.akuaku.githubaku.api

import com.akuaku.githubaku.data.InuUserRespponeses
import com.akuaku.githubaku.data.User
import com.akuaku.githubaku.data.UserResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 640c635b26bb82e44f180d0cebba3edd8e3b8509")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponses>

    @GET("users/{username}")
    @Headers("Authorization: token 640c635b26bb82e44f180d0cebba3edd8e3b8509")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<InuUserRespponeses>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 640c635b26bb82e44f180d0cebba3edd8e3b8509")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 640c635b26bb82e44f180d0cebba3edd8e3b8509 ")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}