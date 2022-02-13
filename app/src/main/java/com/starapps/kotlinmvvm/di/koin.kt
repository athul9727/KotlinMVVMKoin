package com.starapps.kotlinmvvm


import android.app.Application
import android.content.Context
import androidx.databinding.library.BuildConfig.DEBUG
import androidx.room.Room
import com.starapps.kotlinmvvm.repository.*
import com.starapps.kotlinmvvm.viewmodel.ViewModel
import com.starapps.mvvmkotlin2.repository.db.ImagesDao
import com.starapps.mvvmkotlin2.repository.db.ImagesDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
    single { provideApi(retrofit = get()) }

}



val networkModule = module {

    val connectTimeout : Long = 40
    val readTimeout : Long  = 40

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        provideRetrofit(client = get())
    }
}

val databaseModule = module {

    fun provideDatabase(application: Application): ImagesDatabase {
        return Room.databaseBuilder(application, ImagesDatabase::class.java, "imagesdb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: ImagesDatabase): ImagesDao {
        return  database.imgDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}


val repositoryModule = module {

    fun provideimagedataRepository(api: Api, context: Context,dao:ImagesDao): Repository {
        return RepositoryImpl(api, context,dao)
    }
    single { provideimagedataRepository(api = get(), context = androidContext(),dao = get()) }

}


val viewModelModule = module {

    viewModel {
        ViewModel(repository = get(),application = androidApplication())
    }

}