package com.example.projectcats.data.resource

import com.example.projectcats.data.dtos.CatDTO
import retrofit2.http.GET

interface RestDataSource {

    @GET
    suspend fun getCats(): List<CatDTO>
}