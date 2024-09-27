package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetAllFavUseCase {
    suspend fun execute(): Result<List<FavouriteAdditionResponse>>
}

class GetAllFavUseCaseImpl(val repo: DashboardRepository) : GetAllFavUseCase {
    override suspend fun execute(): Result<List<FavouriteAdditionResponse>> {
        val response = repo.fetchAllFav()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            throw Exception(response.message())
        }
    }
}