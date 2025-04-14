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
import androidx.compose.ui.draw.alpha
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun HomeScreen(
    onNavigateBack: () -> Unit,
    onNavigateToScenario1: () -> Unit = {}  // 기본값 설정
) {
    // 캐릭터의 x 위치를 상태로 관리
    var characterOffsetX by remember { mutableStateOf(0f) }
    
    // NPC의 y 위치를 상태로 관리
    var npcOffsetY by remember { mutableStateOf(-200f) }
    
    // NPC의 x 위치를 상태로 관리
    var npcOffsetX by remember { mutableStateOf((-150..150).random().toFloat()) }
    
    // NPC 표시 여부
    var isNpcVisible by remember { mutableStateOf(false) }
    
    // NPC 페이드 인 효과를 위한 alpha 값
    var npcAlpha by remember { mutableStateOf(0f) }
    
    // NPC 움직임 시작 여부
    var isNpcMoving by remember { mutableStateOf(false) }
    
    // 폭탄의 y 위치를 상태로 관리
    var bombOffsetY by remember { mutableStateOf(-200f) }
    
    // 폭탄의 x 위치를 상태로 관리
    var bombOffsetX by remember { mutableStateOf((-150..150).random().toFloat()) }
    
    // 폭탄 표시 여부
    var isBombVisible by remember { mutableStateOf(false) }
    
    // 폭탄 페이드 인 효과를 위한 alpha 값
    var bombAlpha by remember { mutableStateOf(0f) }
    
    // 폭탄 움직임 시작 여부
    var isBombMoving by remember { mutableStateOf(false) }
    
    // 충돌 감지를 위한 상태
    var isCollision by remember { mutableStateOf(false) }
    
    // NPC가 지나갔는지 확인하는 상태
    var hasPassedCharacter by remember { mutableStateOf(false) }
    
    // 폭탄이 지나갔는지 확인하는 상태
    var hasPassedBomb by remember { mutableStateOf(false) }
    
    // 전체 화면 페이드아웃 효과를 위한 alpha 값
    var screenAlpha by remember { mutableStateOf(1f) }
    
    // 페이드아웃 완료 여부
    var isFadeOutComplete by remember { mutableStateOf(false) }
    
    // x축 이동 제한 값 (dp 단위)
    val maxOffsetX = 150f  // 오른쪽 최대 이동 거리
    val minOffsetX = -150f // 왼쪽 최대 이동 거리

    // NPC 크기 설정
    val npcWidth = 160f
    val npcCollisionRange = npcWidth / 2  // 충돌 감지 범위 (-80 ~ +80)

    // 폭탄 크기 설정
    val bombWidth = 100f
    val bombCollisionRange = bombWidth / 2  // 충돌 감지 범위 (-50 ~ +50)

    // 은우 캐릭터의 y 좌표 (화면 하단에서 50dp 위)
    val characterY = 170f  // 화면 하단에서 50dp 위

    // 코루틴 스코프 선언
    val coroutineScope = rememberCoroutineScope()

    // NPC 페이드 인 및 움직임 로직
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // NPC 페이드 인 효과
            isNpcVisible = true
            for (i in 0..10) {
                npcAlpha = i / 10f
                delay(50) // 0.5초 동안 페이드 인
            }
            isNpcMoving = true // 페이드 인 완료 후 움직임 시작
            
            while (!isCollision) {
                delay(16) // 약 60fps
                if (isNpcMoving) {
                    npcOffsetY += 2f // 하강 속도 조절

                    // NPC가 은우 캐릭터의 y좌표에 도달했는지 확인
                    if (npcOffsetY >= characterY && !hasPassedCharacter) {
                        // 은우 캐릭터의 x좌표가 NPC의 충돌 범위 안에 있는지 확인
                        if (characterOffsetX >= npcOffsetX - npcCollisionRange && 
                            characterOffsetX <= npcOffsetX + npcCollisionRange) {
                            isCollision = true
                            
                            // 전체 화면 페이드아웃 효과 적용
                            for (i in 10 downTo 0) {
                                screenAlpha = i / 10f
                                delay(50) // 0.5초 동안 페이드아웃
                            }
                            
                            isFadeOutComplete = true
                            onNavigateToScenario1()
                            break
                        }
                    }
                    
                    // NPC가 은우 캐릭터의 y좌표를 지나갔는지 확인
                    if (npcOffsetY > characterY + 100f) {  // NPC가 은우 캐릭터 위치를 충분히 지나갔을 때
                        hasPassedCharacter = true
                    }
                    
                    // NPC가 화면 밖으로 나가면 다시 위로
                    if (npcOffsetY > 600f) {
                        npcOffsetY = -200f  // 화면 상단 1/3 지점으로 리셋
                        hasPassedCharacter = false  // NPC가 다시 위로 올라갈 때는 충돌 체크 초기화
                        // 새로운 랜덤 x좌표 설정
                        npcOffsetX = (-150..150).random().toFloat()
                        
                        // NPC 페이드 인 효과 다시 적용
                        npcAlpha = 0f
                        for (i in 0..10) {
                            npcAlpha = i / 10f
                            delay(50) // 0.5초 동안 페이드 인
                        }
                    }
                }
            }
        }
    }

    // 폭탄 페이드 인 및 움직임 로직
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(3000) // 3초 후에 폭탄 생성
            
            // 폭탄 페이드 인 효과
            isBombVisible = true
            for (i in 0..10) {
                bombAlpha = i / 10f
                delay(50) // 0.5초 동안 페이드 인
            }
            isBombMoving = true // 페이드 인 완료 후 움직임 시작
            
            while (!isCollision) {
                delay(16) // 약 60fps
                if (isBombVisible && isBombMoving) {
                    bombOffsetY += 2f // 하강 속도 조절

                    // 폭탄이 은우 캐릭터의 y좌표에 도달했는지 확인
                    if (bombOffsetY >= characterY && !hasPassedBomb) {
                        // 은우 캐릭터의 x좌표가 폭탄의 충돌 범위 안에 있는지 확인
                        if (characterOffsetX >= bombOffsetX - bombCollisionRange && 
                            characterOffsetX <= bombOffsetX + bombCollisionRange) {
                            // 폭탄 소멸
                            isBombVisible = false
                            hasPassedBomb = true
                            
                            // 3초 후에 폭탄 다시 생성
                            delay(3000)
                            bombOffsetY = -200f
                            bombOffsetX = (-150..150).random().toFloat()
                            
                            // 폭탄 페이드 인 효과 다시 적용
                            isBombVisible = true
                            bombAlpha = 0f
                            for (i in 0..10) {
                                bombAlpha = i / 10f
                                delay(50) // 0.5초 동안 페이드 인
                            }
                            isBombMoving = true
                            hasPassedBomb = false
                        }
                    }
                    
                    // 폭탄이 은우 캐릭터의 y좌표를 지나갔는지 확인
                    if (bombOffsetY > characterY + 100f) {  // 폭탄이 은우 캐릭터 위치를 충분히 지나갔을 때
                        hasPassedBomb = true
                    }
                    
                    // 폭탄이 화면 밖으로 나가면 다시 위로
                    if (bombOffsetY > 600f) {
                        bombOffsetY = -200f  // 화면 상단 1/3 지점으로 리셋
                        hasPassedBomb = false  // 폭탄이 다시 위로 올라갈 때는 충돌 체크 초기화
                        // 새로운 랜덤 x좌표 설정
                        bombOffsetX = (-150..150).random().toFloat()
                        
                        // 폭탄 페이드 인 효과 다시 적용
                        bombAlpha = 0f
                        for (i in 0..10) {
                            bombAlpha = i / 10f
                            delay(50) // 0.5초 동안 페이드 인
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFFF))
            .alpha(screenAlpha)
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
        if (isNpcVisible) {
            Image(
                painter = painterResource("Character_ch1.png"),
                contentDescription = "NPC Character",
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.Center)
                    .offset(
                        x = npcOffsetX.dp,
                        y = npcOffsetY.dp
                    )
                    .alpha(npcAlpha)
            )
        }

        // 폭탄
        if (isBombVisible) {
            Image(
                painter = painterResource("Bomb.png"),
                contentDescription = "Bomb",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
                    .offset(
                        x = bombOffsetX.dp,
                        y = bombOffsetY.dp
                    )
                    .alpha(bombAlpha)
            )
        }

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