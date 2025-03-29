package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onNavigateBack: () -> Unit,
    onNavigateToScenario1: () -> Unit = {}  // 기본값 설정
) {
    // 캐릭터의 x 위치를 상태로 관리
    var characterOffsetX by remember { mutableStateOf(0f) }
    
    // NPC의 y 위치를 상태로 관리
    var npcOffsetY by remember { mutableStateOf(0f) }  // 화면 중앙에서 시작
    
    // 충돌 감지를 위한 상태
    var isCollision by remember { mutableStateOf(false) }
    
    // x축 이동 제한 값 (dp 단위)
    val maxOffsetX = 150f  // 오른쪽 최대 이동 거리
    val minOffsetX = -150f // 왼쪽 최대 이동 거리

    // 이벤트 발생 지점 설정 (화면 상단에 더 가깝게)
    val eventTriggerPoint = 180f  // 이벤트 발생 지점을 위로 조정

    // NPC 움직임을 위한 코루틴
    val coroutineScope = rememberCoroutineScope()
    
    // NPC 움직임 로직
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (!isCollision) {
                delay(16) // 약 60fps
                npcOffsetY += 2f // 하강 속도 조절
                
                // 이벤트 발생 지점 도달 확인
                if (npcOffsetY >= eventTriggerPoint) {
                    isCollision = true
                    onNavigateToScenario1()
                    break
                }
                
                // NPC가 화면 밖으로 나가면 다시 위로
                if (npcOffsetY > 600f) {
                    npcOffsetY = 0f  // 화면 중앙으로 리셋
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFFF))
    ) {
        // 사다리꼴 배경
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource("Trapezoid.png"),
                contentDescription = "Trapezoid Background",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // NPC 캐릭터
        Image(
            painter = painterResource("Character_ch1.png"),
            contentDescription = "NPC Character",
            modifier = Modifier
                .size(160.dp)  // NPC 크기를 더 크게 증가
                .align(Alignment.Center)
                .offset(y = npcOffsetY.dp)
        )

        // 은우 캐릭터
        Image(
            painter = painterResource("EnuBack.png"),
            contentDescription = "Enu Character",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomCenter)
                .offset(
                    x = characterOffsetX.dp,
                    y = (-50).dp
                )
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        // x축으로만 이동하도록 dragAmount.y는 무시
                        characterOffsetX = (characterOffsetX + dragAmount.x)
                            .coerceIn(minOffsetX, maxOffsetX)
                    }
                }
        )

        // 뒤로가기 버튼
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.padding(start = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "뒤로가기",
                tint = Color.Black
            )
        }
    }
} 