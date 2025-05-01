package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScenario1Screen(
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFFF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 MAP 1 텍스트
            Text(
                text = "MAP 1",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A6FFF),
                modifier = Modifier.padding(top = 32.dp)
            )
            
            // 메인 텍스트
            Text(
                text = "나랑 놀아줘!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            // 캐릭터 이미지
            Image(
                painter = painterResource("Character_ch1.png"),
                contentDescription = "Character",
                modifier = Modifier
                    .size(160.dp)
                    .padding(vertical = 32.dp)
            )
            
            // 시작하기 버튼
            Button(
                onClick = { /* TODO: 다음 화면으로 이동 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(top = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A6FFF)
                )
            ) {
                Text(
                    text = "시작하기",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        // 뒤로가기 버튼
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "뒤로가기",
                tint = Color.Black
            )
        }
    }
} 