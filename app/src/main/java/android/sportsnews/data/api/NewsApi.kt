package android.sportsnews.data.api

import android.sportsnews.BuildConfig
import android.sportsnews.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getAllNews(
        @Query("country")
        searchQuery: String = "ru",
        @Query("category")
        myCategory: String = "sports",
        @Query("apiKey")
        myApiKey : String = BuildConfig.API_KEY
    ): TopHeadlinesResponse

}