package network

import okhttp3.Interceptor
import okhttp3.Response
import util.TokenManager

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // 이미 Authorization 헤더가 있으면 그대로 진행
        if (originalRequest.header("Authorization") != null) {
            return chain.proceed(originalRequest)
        }
        
        // 토큰이 있으면 헤더에 추가
        val token = tokenManager.getToken()
        return if (token != null) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}