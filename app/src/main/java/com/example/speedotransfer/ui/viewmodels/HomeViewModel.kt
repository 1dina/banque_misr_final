package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.Content
import com.example.speedotransfer.data.models.Passwords
import com.example.speedotransfer.data.models.TransactionHistoryRequest
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.data.models.UserInfoResponse
import com.example.speedotransfer.domain.usecases.CreateAccountUseCase
import com.example.speedotransfer.domain.usecases.DoTransferUseCase
import com.example.speedotransfer.domain.usecases.GetAccountByIdUseCase
import com.example.speedotransfer.domain.usecases.GetInfoUseCase
import com.example.speedotransfer.domain.usecases.GetTransactionsUseCase
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCase
import com.example.speedotransfer.domain.usecases.UpdatePasswordUseCase
import com.example.speedotransfer.ui.screens.TransactionsDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(
    val createAccountUseCase: CreateAccountUseCase,
    val getUserAccountsUseCase: GetUserAccountsUseCase,
    val getAccountByIdUseCase: GetAccountByIdUseCase,
    val doTransferUseCase: DoTransferUseCase,
    val getTransactionsUseCase: GetTransactionsUseCase,
    val getInfoUseCase: GetInfoUseCase,
    val passwordUseCase: UpdatePasswordUseCase
) : ViewModel() {
    private val _userAccountData = MutableStateFlow("Add your Account first")
    val userAccountData = _userAccountData.asStateFlow()
    private val _userFound = MutableStateFlow<Boolean?>(null)
    val userFound = _userFound.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)
    private val accountId = MutableStateFlow(0)
    val responseStatus = _responseStatus.asStateFlow()
    private val _transactionHistoryList = MutableStateFlow<List<Content>>(
        emptyList()
    )
    val transactionHistoryList = _transactionHistoryList.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()
    private val _userInfoData = MutableStateFlow<UserInfoResponse?>(null)
    val userInfoData = _userInfoData.asStateFlow()
    private val _password = MutableStateFlow<Passwords?>(null)
    val password = _password.asStateFlow()
    var succtrans = false


    init {
        // createAccount(AccountCreationRequest("miza",10230))
        getUserAccounts()
    }

    private fun getUserAccounts() {
        viewModelScope.launch {
            try {
                val result = getUserAccountsUseCase.execute()
                if (result.isSuccess) {
                    val balance = result.getOrNull()?.get(0)?.balance ?: "Unknown Balance"
                    _userAccountData.value = "$balance EGP"
                    _responseStatus.value = true
                    accountId.value = result.getOrNull()?.get(0)?.id ?: -1
                    Log.e("trace Id", accountId.value.toString())
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
                val result = doTransferUseCase.execute(transactionRequest)
                if (result.isSuccess) {
                    _responseStatus.value = true
                    _toastMessage.value = ""
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
        }

    }

    fun getHistory(transactionHistoryRequest: TransactionHistoryRequest) {
        viewModelScope.launch {
            try {
                val result = getTransactionsUseCase.execute(transactionHistoryRequest)
                if (result.isSuccess) {
                    _responseStatus.value = true
                    _userFound.value = true
                    _transactionHistoryList.value = result.getOrNull()?.content!!
                    Log.e("trace", "history is here")
                } else {
                    _toastMessage.value = result.exceptionOrNull()?.message
                    _responseStatus.value = false
                    _userFound.value = false
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
                if (result.isSuccess){
                    _userInfoData.value = result.getOrNull()?: UserInfoResponse(emptyList(),"","","",0,"")
                    }


            } catch (e: Exception) {
                 handleError(e)
             }
        }
    }

    fun updatePassword(passwords: Passwords) {
        viewModelScope.launch {
            try {
                val result = passwordUseCase.execute(passwords)
                if (result.isSuccess){
                    _toastMessage.value= "Password is updated"
                }


            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


    //    fun createAccount (account : AccountCreationRequest) {
//       viewModelScope.launch {
//          try {
//               val result = createAccountUseCase.execute(account)
//               if (result.isSuccess) {
//                   val balance = result.getOrNull()?.balance ?: "Unknown Balance"
//                   _userAccountData.value = balance.toString()
//                   _responseStatus.value =true
//                   _toastMessage.value ="You've successfully added your card"
//               }
//               else {
//                   result.exceptionOrNull()?.let { handleError(it) }
//               }
//           }catch (e : Exception) {
//             handleError(e)
//          }
//       }
//    }
    fun resetResponseStatus() {
        _responseStatus.value = null
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }

    private fun handleError(exception: Throwable) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."

            else -> {
                val shownMessage = "server take long time to respond"
                "Error: $shownMessage"
            }
        }
        _responseStatus.value = false
        _toastMessage.value = errorMessage
    }

}
