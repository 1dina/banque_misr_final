package com.example.speedotransfer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.AccountCreationRequest
import com.example.speedotransfer.data.models.userAccResponse.UserAccountsResponseItem
import com.example.speedotransfer.domain.usecases.CreateAccountUseCase
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyCardsViewModel(val createAccountUseCase: CreateAccountUseCase,
    val getUserAccountsUseCase: GetUserAccountsUseCase) : ViewModel() {
    private val _myCardsList = MutableStateFlow<List<UserAccountsResponseItem>>(
        emptyList()
    )
    val myCardsList = _myCardsList.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)
    val responseStatus = _responseStatus.asStateFlow()


    init {
        getUserAccounts()
    }
    private fun getUserAccounts() {
        viewModelScope.launch {
            try {
                val result = getUserAccountsUseCase.execute()
                if (result.isSuccess) {
                    _myCardsList.value = result.getOrNull()!!
                } else {
                    result.exceptionOrNull()?.let {
                        _toastMessage.value = it.message
                    }
                }
            } catch (e: Exception) {
                _toastMessage.value = e.message
            }

        }
    }
    fun createAccount () {
        viewModelScope.launch {
            try {
                val result = createAccountUseCase.execute(AccountCreationRequest("visa",(1000..10000).random()))
                if (result.isSuccess) {
                    _responseStatus.value =true
                    _toastMessage.value ="You've successfully added your card"
                }
                else {
                    result.exceptionOrNull()?.let {   _toastMessage.value = it.message }
                }
            }catch (e : Exception) {
                _toastMessage.value=e.message
            }
            getUserAccounts()
        }
    }
    fun resetResponseStatus() {
        _responseStatus.value = null
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }

}