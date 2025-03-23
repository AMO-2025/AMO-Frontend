package viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.ApiService
import network.LoginRequest
import util.TokenManager

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {
    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    
    fun login(userIdentifier: String, phoneNumber: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = apiService.login(LoginRequest(userIdentifier, phoneNumber))
                if (response.status == "success") {
                    tokenManager.saveToken(response.token)
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error(response.message)
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "로그인 실패")
            }
        }
    }
    
    fun validateSavedToken() {
        val token = tokenManager.getToken()
        if (token != null) {
            viewModelScope.launch {
                try {
                    val response = apiService.validateToken("Bearer $token")
                    if (response.status == "success") {
                        _loginState.value = LoginState.Success
                    } else {
                        tokenManager.clearToken()
                        _loginState.value = LoginState.Idle
                    }
                } catch (e: Exception) {
                    tokenManager.clearToken()
                    _loginState.value = LoginState.Idle
                }
            }
        }
    }
}