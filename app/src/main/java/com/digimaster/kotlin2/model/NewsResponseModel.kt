package com.digimaster.kotlin2.model

data class NewsResponseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)