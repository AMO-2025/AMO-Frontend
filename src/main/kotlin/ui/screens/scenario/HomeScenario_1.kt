package ui.screens.scenario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    delayMillis: Long = 50L,
    onComplete: () -> Unit = {}
) {
    var currentText by remember { mutableStateOf("") }
    var currentIndex by remember { mutableStateOf(0) }
    val lines = text.split("\n")
    var currentLineIndex by remember { mutableStateOf(0) }
    
    LaunchedEffect(text) {
        currentText = ""
        currentIndex = 0
        currentLineIndex = 0
        
        for (lineIndex in lines.indices) {
            val line = lines[lineIndex]
            currentLineIndex = lineIndex
            var lineText = ""
            for (charIndex in line.indices) {
                delay(delayMillis)
                lineText += line[charIndex]
                currentText = (0 until currentLineIndex).map { lines[it] }.joinToString("\n") + 
                    (if (currentLineIndex > 0) "\n" else "") + lineText
            }
            if (lineIndex < lines.size - 1) {
                delay(delayMillis * 2) // 줄 사이 추가 딜레이
            }
        }
        onComplete()
    }
    
    Text(
        text = currentText,
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        lineHeight = 36.sp,
        modifier = modifier
    )
}

@Composable
fun DialogueText(
    text1: String,
    text2: String,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {}
) {
    var isFirstTextComplete by remember { mutableStateOf(false) }
    var isSecondTextComplete by remember { mutableStateOf(false) }
    
    Column(modifier = modifier) {
        TypewriterText(
            text = text1,
            delayMillis = 50L,
            modifier = Modifier.fillMaxWidth(),
            onComplete = { isFirstTextComplete = true }
        )
        
        if (isFirstTextComplete) {
            TypewriterText(
                text = text2,
                delayMillis = 50L,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                onComplete = {
                    isSecondTextComplete = true
                    onComplete()
                }
            )
        }
    }
}

@Composable
fun HomeScenario1Screen(onNavigateBack: () -> Unit) {
    var scenarioState by remember { mutableStateOf(ScenarioState.INITIAL) }
    var showEmotionSelection by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var isTypingComplete by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFFF))
    ) {
        when {
            showEmotionSelection -> {
                EmotionSelectionScreen(
                    onEmotionSelected = {
                        showEmotionSelection = false
                        scenarioState = ScenarioState.EMOTION_RESPONSE
                        isTypingComplete = false
                    }
                )
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (scenarioState == ScenarioState.INITIAL) {
                            // 상단 MAP 1 텍스트
                            Text(
                                text = "MAP 1",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4A6FFF),
                                modifier = Modifier.padding(top = 80.dp)
                            )
                            
                            // 메인 텍스트
                            Text(
                                text = "나랑 놀아줘!",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 24.dp)
                            )
                        }
                        
                        // 캐릭터 이미지
                        Image(
                            painter = painterResource("Scenario_1.png"),
                            contentDescription = "Character",
                            modifier = Modifier
                                .size(400.dp)
                                .padding(top = if (scenarioState == ScenarioState.INITIAL) 40.dp else 80.dp)
                                .zIndex(1f)
                        )
                    }

                    if (scenarioState != ScenarioState.INITIAL) {
                        // 하단 회색 배경과 대화
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .fillMaxHeight(0.33f)
                                .background(
                                    Color(0xFF4A4A4A),
                                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                val (text1, text2) = when (scenarioState) {
                                    ScenarioState.DIALOGUE1 -> "으아아아 ㅠㅠ" to "너우 아파.."
                                    ScenarioState.DIALOGUE2 -> "어!" to "은우야 안녕?"
                                    ScenarioState.DIALOGUE3 -> "나 오늘" to "돌뿌리에 걸려서\n넘어졌어 ㅠㅠ"
                                    ScenarioState.DIALOGUE4 -> "이때 내가" to "어떤 기분이었을까?"
                                    ScenarioState.EMOTION_RESPONSE -> "맞아 ㅠㅠ" to "나 너무 슬펐어."
                                    ScenarioState.FINAL -> "그럼 내 표정을" to "따라해볼래?"
                                    else -> "" to ""
                                }

                                DialogueText(
                                    text1 = text1,
                                    text2 = text2,
                                    modifier = Modifier.weight(1f),
                                    onComplete = { isTypingComplete = true }
                                )

                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        text = "다음",
                                        color = Color.White.copy(alpha = if (isTypingComplete) 1f else 0.5f),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .clickable(enabled = isTypingComplete) {
                                                scope.launch {
                                                    when (scenarioState) {
                                                        ScenarioState.DIALOGUE1 -> scenarioState = ScenarioState.DIALOGUE2
                                                        ScenarioState.DIALOGUE2 -> scenarioState = ScenarioState.DIALOGUE3
                                                        ScenarioState.DIALOGUE3 -> scenarioState = ScenarioState.DIALOGUE4
                                                        ScenarioState.DIALOGUE4 -> showEmotionSelection = true
                                                        ScenarioState.EMOTION_RESPONSE -> scenarioState = ScenarioState.FINAL
                                                        ScenarioState.FINAL -> {} // 시나리오 종료
                                                        else -> {}
                                                    }
                                                }
                                            }
                                    )
                                }
                            }
                        }
                    }

                    // 시작하기 버튼 (초기 상태에서만 표시)
                    if (scenarioState == ScenarioState.INITIAL) {
                        Button(
                            onClick = { scenarioState = ScenarioState.DIALOGUE1 },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                                .padding(bottom = 32.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4A6FFF)
                            )
                        ) {
                            Text(
                                text = "시작하기",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
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

@Composable
fun EmotionSelectionScreen(onEmotionSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 표정 선택 이미지들
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource("Scenario_1.png"),
                contentDescription = "Sad Emotion",
                modifier = Modifier
                    .size(160.dp)
                    .padding(8.dp)
            )
        }
        
        // 다음 버튼
        Button(
            onClick = onEmotionSelected,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A6FFF)
            )
        ) {
            Text(
                text = "다음",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

enum class ScenarioState {
    INITIAL,
    DIALOGUE1,
    DIALOGUE2,
    DIALOGUE3,
    DIALOGUE4,
    EMOTION_RESPONSE,
    FINAL
}
