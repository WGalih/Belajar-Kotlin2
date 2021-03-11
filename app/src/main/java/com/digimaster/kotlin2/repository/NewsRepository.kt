package com.digimaster.kotlin2.repository

import com.digimaster.kotlin2.api.RetrofitInstance
import com.digimaster.kotlin2.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getTopHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.apiInterfaceNews.getTopHeadlines(countryCode, pageNumber)
}