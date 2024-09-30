package com.example.speedotransfer.ui.viewmodels.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.models.transaction.history.Passwords
import com.example.speedotransfer.data.source.remote.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.domain.usecases.AddToFavUseCase
import com.example.speedotransfer.domain.usecases.DoTransferUseCase
import com.example.speedotransfer.domain.usecases.GetInfoUseCase
import com.example.speedotransfer.domain.usecases.GetTransactionsUseCase
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCase
import com.example.speedotransfer.domain.usecases.UpdatePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(
    private val getUserAccountsUseCase: GetUserAccountsUseCase,
    private val doTransferUseCase: DoTransferUseCase,
    private val getInfoUseCase: GetInfoUseCase,
    private val passwordUseCase: UpdatePasswordUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val addToFavUseCase: AddToFavUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _accountId = MutableStateFlow(0)
    val accountId = _accountId.asStateFlow()

    var succtrans = false
    private var _userData: MutableStateFlow<UserInfoResponse> =
        MutableStateFlow( UserInfoResponse(emptyList(), "", "", "", -1, ""))
    var userData = _userData.asStateFlow()


    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeUiState.Loading
            try {
                val result = getInfoUseCase.execute()
                if (result.isSuccess) {
                    val userInfo =
                        result.getOrNull() ?: UserInfoResponse(emptyList(), "", "", "", 0, "")
                    val balance = userInfo.accounts.firstOrNull()?.balance ?: "Unknown Balance"
                    _accountId.value = userInfo.accounts.firstOrNull()?.id ?: -1
                    _userData.value = userInfo
                    _uiState.value = HomeUiState.Success(
                        accountBalance = "$balance EGP",
                        transactionHistory = emptyList(),
                        userInfo = userInfo
                    )
                    fetchUserAccounts()
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun fetchUserAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getUserAccountsUseCase.execute()
                if (result.isSuccess) {
                    val balance = result.getOrNull()?.firstOrNull()?.balance ?: "Unknown Balance"
                    _uiState.value = (_uiState.value as? HomeUiState.Success)?.copy(
                        accountBalance = "$balance EGP"
                    ) ?: HomeUiState.Success(
                        accountBalance = "$balance EGP",
                        transactionHistory = emptyList(),
                        userInfo = UserInfoResponse(emptyList(), "", "", "", 0, "")
                    )

                    fetchTransactionHistory()
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun fetchTransactionHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getTransactionsUseCase.execute(TransactionHistoryRequest())
                if (result.isSuccess) {
                    val history = result.getOrNull()?.content ?: emptyList()
                    _uiState.value = (_uiState.value as? HomeUiState.Success)?.copy(
                        transactionHistory = history
                    ) ?: HomeUiState.Success(
                        accountBalance = "",
                        transactionHistory = history,
                        userInfo = UserInfoResponse(emptyList(), "", "", "", 0, "")
                    )
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun transferProcess(transactionRequest: TransactionRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeUiState.Loading
            try {
                transactionRequest.senderAccountNum = _accountId.value
                val result = doTransferUseCase.execute(transactionRequest)
                if (result.isSuccess) {
                    fetchUserAccounts()
                    _uiState.value = (_uiState.value as? HomeUiState.Success)?.copy(
                        toastMessage = "Transfer successful"
                    ) ?: HomeUiState.Success(
                        accountBalance = "",
                        transactionHistory = emptyList(),
                        userInfo = _userData.value,
                        toastMessage = "Transfer successful"
                    )
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun updatePassword(passwords: Passwords) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = passwordUseCase.execute(passwords)
                if (result.isSuccess) {
                    _uiState.value = (_uiState.value as? HomeUiState.Success)?.copy(
                        toastMessage = "Password updated successfully"
                    ) ?: HomeUiState.Success(
                        accountBalance = "",
                        transactionHistory = emptyList(),
                        userInfo = UserInfoResponse(emptyList(), "", "", "", 0, ""),
                        toastMessage = "Password updated successfully"
                    )
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun addToFav(favouriteAdditionResponse: FavouriteAdditionResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = addToFavUseCase.execute(favouriteAdditionResponse)
                if (result.isSuccess) {
                    _uiState.value = (_uiState.value as? HomeUiState.Success)?.copy(
                        toastMessage = "Added to favorites"
                    ) ?: HomeUiState.Success(
                        accountBalance = "",
                        transactionHistory = emptyList(),
                        userInfo = UserInfoResponse(emptyList(), "", "", "", 0, ""),
                        toastMessage = "Added to favorites"
                    )
                } else {
                    _uiState.value = HomeUiState.Error("")
                    handleError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(exception: Throwable?) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."
            else -> exception?.message ?: "An unknown error occurred."
        }
        _uiState.value = HomeUiState.Error(errorMessage)
    }

}