package com.example.projectcats.domain.useCase

import com.example.projectcats.data.dtos.toListCat
import com.example.projectcats.data.resource.Resource
import com.example.projectcats.domain.models.Cat
import com.example.projectcats.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatsUseCase @Inject constructor(private val catRepository: CatRepository) {

    operator fun invoke(): Flow<Resource<List<Cat>>> = flow {
        emit(Resource.Loading())
        val catsDTO = catRepository.getCats()
        if(catsDTO.isEmpty()){
            emit(Resource.Error("No return cats"))
        }else{
            emit(Resource.Success(catsDTO.toListCat()))
        }
    }.catch {
        emit(Resource.Error("Oops ocurred exception"))
    }
}