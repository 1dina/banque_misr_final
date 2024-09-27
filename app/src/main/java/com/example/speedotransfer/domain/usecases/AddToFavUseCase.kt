package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface AddToFavUseCase {
    suspend fun execute (favouriteAdditionResponse: FavouriteAdditionResponse) : Result<String>
}

class AddToFavUseCaseImpl (val repo : DashboardRepository) : AddToFavUseCase {
    override suspend fun execute(favouriteAdditionResponse: FavouriteAdditionResponse): Result<String> {
        val response = repo.addToFav(favouriteAdditionResponse)
        return if (response.isSuccessful) {
            Result.success("Item has been Added to Favourites")
        } else {
            Result.failure(Exception("There is no account with that Id"))
        }
    }
}