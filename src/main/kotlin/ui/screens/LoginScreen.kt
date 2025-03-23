package ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.io.File
import javax.imageio.ImageIO
import network.NetworkClient
import util.TokenManager
import viewmodel.LoginState
import viewmodel.LoginViewModel

@Composable
fun LoginScreen(onNavigateToUserInfo: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2856F5))
    ) {
        // AMO 로고 (화면 너비의 5/7 지점)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource("AMO_Logo.png"),
                contentDescription = "AMO 로고",
                modifier = Modifier
                    .padding(start = 280.dp)
                    .size(120.dp)
            )
        }

        // Enu 캐릭터 (화면 하단에 맞춤)
        Image(
            painter = painterResource("FirstPage_Enu.png"),
            contentDescription = "Enu 캐릭터",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .wrapContentHeight()
        )

        // 로그인 버튼 (시작하기 버튼 대체)
        Button(
            onClick = { 
                onNavigateToUserInfo()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)  // 좌우 여백
                .padding(bottom = 32.dp)      // 하단 여백
                .height(64.dp),               // 버튼 높이 증가
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF082BA6)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("시작하기", color = Color.White)
        }
    }
}

// 이미지 로드 함수
fun loadImageBitmap(path: String): ImageBitmap? {
    return try {
        val file = File(path)
        val bufferedImage = ImageIO.read(file)
        bufferedImage.toComposeImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}