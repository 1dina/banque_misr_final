package com.example.speedotransfer

object Constants {
    const val BASE_URL = "https://moneyapi-abatdad9bbgvc3bs.francecentral-01.azurewebsites.net"
    const val SIGNUP_ENDPOINT = "/api/v1/auth/create"
    const val SIGNIN_ENDPOINT = "/api/v1/auth/login"
    const val SIGNOUT_ENDPOINT = "/api/v1/auth/logout"
    const val ACCOUNT_CREATION_ENDPOINT = "/api/v1/accounts/create"
    const val USER_ACCOUNTS_ENDPOINT = "/api/v1/accounts/ViewUserAccount"
    const val GET_USER_ACCOUNT_ID_ENDPOINT = "/api/v1/accounts/GetAccountByID/{accountID}"
    const val TRANSFER_PROCESS_ENDPOINT = "/api/v1/accounts/transaction"
    const val TRANSACTION_HISTORY_ENDPOINT = "/api/v1/accounts/transaction/all"
    const val FAVOURITE_ADDITION_ENDPOINT = "/api/v1/user/Favourite/AddFavourite"
    const val ALL_FAVOURITES_ENDPOINT = "/api/v1/user/Favourite/GetFavourite/{userId}"
    const val DELETE_FAVOURITE_ENDPOINT = "/api/v1/user/Favourite/{accountId}"
}