package android.sportsnews.di

import android.sportsnews.BuildConfig
import android.sportsnews.data.api.NewsApi
import android.sportsnews.data.repository.MyNewsResponseRepositoryImpl
import android.sportsnews.domain.repository.MyNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): NewsApi{
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logger).build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyNewsRepository(api: NewsApi): MyNewsRepository{
        return MyNewsResponseRepositoryImpl(api)
    }

}