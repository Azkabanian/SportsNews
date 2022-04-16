package android.sportsnews.domain.repository

import android.sportsnews.data.model.TopHeadlinesResponse

interface MyNewsRepository {

    suspend fun getAllNews() : TopHeadlinesResponse

}