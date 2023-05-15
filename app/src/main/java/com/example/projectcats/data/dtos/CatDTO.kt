package com.example.projectcats.data.dtos

import com.example.projectcats.domain.models.Cat
import com.google.gson.annotations.SerializedName

data class CatDTO(
    val name: String = "",
    val origin: String = "",
    @SerializedName("affection_level")
    val affectionLevel: Int = 0,
    val intelligence: Int = 0,
    @SerializedName("wikipedia_url")
    val imageUrl: String = ""
)

fun List<CatDTO>.toListCat(): List<Cat> {
    val list: MutableList<Cat> = mutableListOf()
    this.forEach { catDTO ->
        list.add(catDTO.toCat())
    }
    return list
}

fun CatDTO.toCat(): Cat {
    return Cat(
        breedName = this.name,
        origin = this.origin,
        affectionLevel = this.affectionLevel,
        intelligence = this.intelligence,
        imageUrl = this.imageUrl
    )
}