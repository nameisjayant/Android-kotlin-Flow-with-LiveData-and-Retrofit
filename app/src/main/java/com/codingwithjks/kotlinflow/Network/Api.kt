package com.codingwithjks.kotlinflow.Network

import com.codingwithjks.kotlinflow.Model.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getPost() : List<Post>
}