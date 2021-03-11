package com.digimaster.kotlin2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimaster.kotlin2.model.NewsResponseModel
import com.digimaster.kotlin2.repository.NewsRepository
import com.digimaster.kotlin2.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {

    val topHeadlines: MutableLiveData<Resource<NewsResponseModel>> = MutableLiveData()
    var topHeadlinesPage = 1

    init {
        getTopHeadlines("us")
    }

    fun getTopHeadlines(countryCode: String) = viewModelScope.launch {
        topHeadlines.postValue(Resource.Loading())
        val response = newsRepository.getTopHeadlines(countryCode, topHeadlinesPage)
        topHeadlines.postValue(handleTopHeadlinesResponse(response))
    }

    private fun handleTopHeadlinesResponse(response: Response<NewsResponseModel>) : Resource<NewsResponseModel>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}