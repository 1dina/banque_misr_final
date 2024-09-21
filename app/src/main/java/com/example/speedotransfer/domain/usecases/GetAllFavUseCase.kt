package com.example.speedotransfer.domain.usecases

import android.util.Log
import com.example.speedotransfer.data.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetAllFavUseCase {
    suspend fun execute () : Result<List<FavouriteAdditionResponse>>
}

class GetAllFavUseCaseImpl (val repo : DashboardRepository) : GetAllFavUseCase {
    override suspend fun execute(): Result<List<FavouriteAdditionResponse>> {
        return try {
            val response = repo.getAllFav()
            if(response.isSuccessful){
                Log.e("trace",response.body()!!.toString())
                Result.success(response.body()!!)
            }else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()
                Log.e("trace", "Unauthorized: Code $errorCode, Body: $errorBody")
                Log.e("trace",response.message())
                throw Exception(response.message())
            }
        }catch (e:Exception){
            throw e

        }
    }
}