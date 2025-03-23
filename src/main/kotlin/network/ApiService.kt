package network

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/identify")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
    
    @GET("/api/auth/validate")
    suspend fun validateToken(@Header("Authorization") token: String): TokenValidationResponse
}

@Serializable
data class LoginRequest(val userIdentifier: String, val phoneNumber: String)

@Serializable
data class LoginResponse(val status: String, val message: String, val token: String)

@Serializable
data class TokenValidationResponse(val status: String, val userIdentifier: String, val message: String? = null)