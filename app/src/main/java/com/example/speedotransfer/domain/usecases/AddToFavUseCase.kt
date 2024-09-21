package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.FavouriteAdditionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface AddToFavUseCase {
    suspend fun execute (favouriteAdditionResponse: FavouriteAdditionResponse) : Result<String>
}

class AddToFavUseCaseImpl (val repo : DashboardRepository) : AddToFavUseCase {
    override suspend fun execute(favouriteAdditionResponse: FavouriteAdditionResponse): Result<String> {
       return try{
            val response = repo.addToFav(favouriteAdditionResponse)
            if (response.isSuccessful)
                Result.success("Item has been Added to Favourits")
           else
               Result.failure(Exception("There is no account with that Id"))
        }catch (e:Exception) {
           Result.failure(e)
       }
    }
}