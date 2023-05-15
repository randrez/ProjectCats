package com.example.projectcats.domain.repository

import com.example.projectcats.data.dtos.CatDTO

interface CatRepository {

    suspend fun getCats():List<CatDTO>
}