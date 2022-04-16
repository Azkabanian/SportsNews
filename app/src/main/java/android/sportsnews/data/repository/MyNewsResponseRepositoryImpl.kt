package android.sportsnews.data.repository

import android.sportsnews.data.api.NewsApi
import android.sportsnews.data.model.TopHeadlinesResponse
import android.sportsnews.domain.repository.MyNewsRepository
import javax.inject.Inject

class MyNewsResponseRepositoryImpl @Inject constructor(
    private val api: NewsApi
): MyNewsRepository {

    override suspend fun getAllNews(): TopHeadlinesResponse {
        return api.getAllNews()
    }
}