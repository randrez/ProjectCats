package com.example.projectcats.domain.repository

import com.example.projectcats.data.dtos.CatDTO
import com.example.projectcats.data.resource.RestDataSource
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val restDataSource: RestDataSource):CatRepository {

    override suspend fun getCats(): List<CatDTO> {
        return restDataSource.getCats()
    }
}