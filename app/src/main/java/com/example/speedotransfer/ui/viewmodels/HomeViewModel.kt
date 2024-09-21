package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.transaction.history.Content
import com.example.speedotransfer.data.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.models.transaction.history.Passwords
import com.example.speedotransfer.data.models.transaction.history.TransactionHistoryRequest
import com.example.speedotransfer.data.models.transaction.TransactionRequest
import com.example.speedotransfer.data.models.user.info.UserInfoResponse
import com.example.speedotransfer.domain.usecases.AddToFavUseCase
import com.example.speedotransfer.domain.usecases.DoTransferUseCase
import com.example.speedotransfer.domain.usecases.GetInfoUseCase
import com.example.speedotransfer.domain.usecases.GetTransactionsUseCase
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCase
import com.example.speedotransfer.domain.usecases.UpdatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(
    val getUserAccountsUseCase: GetUserAccountsUseCase,
    val doTransferUseCase: DoTransferUseCase,
    val getInfoUseCase: GetInfoUseCase,
    val passwordUseCase: UpdatePasswordUseCase,
    val getTransactionsUseCase: GetTransactionsUseCase,
    val addToFavUseCase: AddToFavUseCase
) : ViewModel() {
    private val _userAccountBalance = MutableStateFlow("Add your Account first")
    val userAccountData = _userAccountBalance.asStateFlow()
    private val _userFound = MutableStateFlow<Boolean?>(null)
    val userFound = _userFound.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)
    private val _accountId = MutableStateFlow(0)
    val accountId = _accountId.asStateFlow()
    val responseStatus = _responseStatus.asStateFlow()
    private val _transactionHistoryList = MutableStateFlow<List<Content>>(
        emptyList()
    )
    val transactionHistoryList = _transactionHistoryList.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()
    private val _userInfoData = MutableStateFlow<UserInfoResponse>(
        UserInfoResponse(emptyList(), "", "", "", 0, "")
    )
    val userInfoData = _userInfoData.asStateFlow()
    private val _password = MutableStateFlow<Passwords?>(null)
    val password = _password.asStateFlow()
    var succtrans = false

    init {
        getUserInfo()
    }

    private fun getUserAccounts() {
        viewModelScope.launch {
            try {
                val result = getUserAccountsUseCase.execute()
                if (result.isSuccess) {
                    val balance = result.getOrNull()?.get(0)?.balance ?: "Unknown Balance"
                    _userAccountBalance.value = "$balance EGP"
                    _responseStatus.value = true
                    Log.e("trace Id", _accountId.value.toString())
                } else {
                    result.exceptionOrNull()?.let { handleError(it) }
                }
            } catch (e: Exception) {
                handleError(e)
            }
            getHistory(TransactionHistoryRequest())


        }
    }

    fun transferProcess(transactionRequest: TransactionRequest) {
        viewModelScope.launch {
            try {
                transactionRequest.senderAccountNum = _accountId.value
                val result = doTransferUseCase.execute(transactionRequest)
                if (result.isSuccess) {
                    _responseStatus.value = true
                    _userFound.value = true
                    succtrans = true
                    Log.e("trace", "transfer is success")
                    getUserAccounts()
                } else {
                    _toastMessage.value = result.exceptionOrNull()?.message
                    _responseStatus.value = false
                    _userFound.value = false
                    Log.e("trace", "wrong Id !!")

                }
            } catch (e: Exception) {
                _userFound.value = false
                handleError(e)
            }
            getUserAccounts()
        }

    }

    private fun getHistory(transactionHistoryRequest: TransactionHistoryRequest) {
        viewModelScope.launch {
            try {
                val result = getTransactionsUseCase.execute(transactionHistoryRequest)
                if (result.isSuccess) {
                    _responseStatus.value = true
                    _transactionHistoryList.value = result.getOrNull()?.content!!
                    Log.e(
                        "trace", "history is here " +
                                "${_transactionHistoryList.value}"
                    )
                } else {
                    _toastMessage.value = result.exceptionOrNull()?.message
                    _responseStatus.value = false
                    Log.e("trace", "no history found !!")

                }
            } catch (e: Exception) {
                _userFound.value = false
                handleError(e)
            }
        }

    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val result = getInfoUseCase.execute()
                if (result.isSuccess) {
                    val balance = result.getOrNull()?.accounts?.get(0)?.balance ?: "Unknown Balance"
                    _userAccountBalance.value = "$balance EGP"
                    _accountId.value = result.getOrNull()?.accounts?.get(0)?.id ?: -1
                    _userInfoData.value =
                        result.getOrNull() ?: UserInfoResponse(emptyList(), "", "", "", 0, "")
                }
            } catch (e: Exception) {
                handleError(e)
            }
            getHistory(TransactionHistoryRequest())

        }
    }

    fun updatePassword(passwords: Passwords) {
        viewModelScope.launch {
            try {
                val result = passwordUseCase.execute(passwords)
                if (result.isSuccess) {
                    _toastMessage.value = "Password is updated"
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


    fun addToFav(favouriteAdditionResponse: FavouriteAdditionResponse) {
        viewModelScope.launch {
            try {
                val result = addToFavUseCase.execute(favouriteAdditionResponse)
                if (result.isSuccess) {
                    _responseStatus.value = true
                    _toastMessage.value = result.getOrNull()!!
                } else {
                    result.exceptionOrNull()?.let { handleError(it) }
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


    fun resetResponseStatus() {
        _responseStatus.value = null
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }

    fun resetUserFound() {
        _userFound.value = null
    }

    private fun handleError(exception: Throwable) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."
            else -> {
                val shownMessage = exception.message
                "Error: $shownMessage"
            }
        }
        _responseStatus.value = false
        _toastMessage.value = errorMessage
    }


}
