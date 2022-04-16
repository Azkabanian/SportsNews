package android.sportsnews.ui.fragments

import android.sportsnews.data.model.TopHeadlinesResponse
import android.sportsnews.domain.repository.MyNewsRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    val repository: MyNewsRepository
): ViewModel() {

    val topHeadlines: MutableLiveData<TopHeadlinesResponse> = MutableLiveData()

    init {
        getTopHeadlines()
    }

    fun getLog(){
        viewModelScope.launch{
            Log.e("TestO", "${repository.getAllNews()}")
        }
    }

    fun getTopHeadlines() = viewModelScope.launch {

        val response = repository.getAllNews()
        topHeadlines.postValue(response)
    }

}