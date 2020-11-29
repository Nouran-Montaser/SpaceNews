package com.nouranmontaser.spacenews.ui.news.list.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nouranmontaser.spacenews.data.DataSource
import com.nouranmontaser.spacenews.utils.Resource
import com.nouranmontaser.spacenews.data.model.News
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository: DataSource
) : ViewModel() {

    private val _news = MutableLiveData<Resource<List<News?>>>()
    var news: LiveData<Resource<List<News?>>> = _news

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            repository.getNews().collect {
                _news.value =it
            }
        }
    }
}
