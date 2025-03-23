package network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import util.TokenManager
import java.util.concurrent.TimeUnit

object NetworkClient {
    // 실제 백엔드 URL로 변경해주세요
    private const val BASE_URL = "http://localhost:8080"
    
    private val json = Json { ignoreUnknownKeys = true }
    
    fun createApiService(tokenManager: TokenManager): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(HttpLoggingInterceptor().apply { 
                level = HttpLoggingInterceptor.Level.BODY 
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
            
        val contentType = "application/json".toMediaType()
        
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            
        return retrofit.create(ApiService::class.java)
    }
}