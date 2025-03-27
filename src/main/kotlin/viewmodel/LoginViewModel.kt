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
    data class Success(val token: String) : LoginState()
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
            try {
                _loginState.value = LoginState.Loading
                
                val request = LoginRequest(
                    userIdentifier = userIdentifier,
                    phoneNumber = phoneNumber
                )
                
                val response = apiService.login(request)
                
                if (response.status == "success" && response.token != null) {
                    tokenManager.saveToken(response.token)
                    _loginState.value = LoginState.Success(response.token)
                } else {
                    _loginState.value = LoginState.Error("인증에 실패했습니다")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("네트워크 오류: ${e.localizedMessage ?: "알 수 없는 오류가 발생했습니다"}")
            }
        }
    }
    
    fun validateSavedToken() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token != null) {
                    val response = apiService.validateToken("Bearer $token")
                    if (response.status == "success") {
                        _loginState.value = LoginState.Success(token)
                    } else {
                        tokenManager.clearToken()
                        _loginState.value = LoginState.Idle
                    }
                } else {
                    _loginState.value = LoginState.Idle
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Idle
                tokenManager.clearToken()
            }
        }
    }
}