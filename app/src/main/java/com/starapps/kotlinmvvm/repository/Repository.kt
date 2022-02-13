package com.starapps.kotlinmvvm.repository

import com.starapps.kotlinmvvm.util.NetworkManager.isOnline
import com.starapps.kotlinmvvm.util.Utils.handleApiError
import com.starapps.kotlinmvvm.util.Utils.handleSuccess
import com.starapps.kotlinmvvm.util.noNetworkConnectivityError
import android.content.Context
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem

import com.starapps.kotlinmvvm.util.AppResult
import com.starapps.mvvmkotlin2.repository.db.ImagesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  Repository {

    suspend fun getAlldata(str:String) : AppResult<List<ImageListResponseItem>>
}

class RepositoryImpl(
    private val api: Api,
    private val context: Context,
    private val imgdao: ImagesDao
    ) :

    Repository {

    override suspend fun getAlldata(str:String): AppResult<List<ImageListResponseItem>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAlldata(str)
                if (response.isSuccessful) {

                    //online case...add data to db

                    response.body()?.let {
                        withContext(Dispatchers.IO) { imgdao.add(it) }
                    }

                    //return data from api
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {

            // return context.noNetworkConnectivityError()

            //offline case...get data from db

            val data = getImageListResponseItemFromCache()
            return if (data.isNotEmpty()) {
                //data not empty..so return data
                AppResult.Success(data)
            } else {
                //no network
                return context.noNetworkConnectivityError()
            }

        }
    }

    private suspend fun getImageListResponseItemFromCache(): List<ImageListResponseItem> {
        return withContext(Dispatchers.IO) {
            imgdao.findAll()
        }
    }

}




//Network
interface Api {

    @GET("/v2/{id}")
    suspend fun getAlldata(@Path("id") str:String): Response<List<ImageListResponseItem>>
}