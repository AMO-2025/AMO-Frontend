package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.clickable
import androidx.compose.ui.zIndex

@Composable
fun MapSelectionScreen(
    onNavigateToAgreement: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToPark: () -> Unit,
    onNavigateToRestaurant: () -> Unit,
    onNavigateToSchool: () -> Unit
) {
    // 다이얼로그 표시 상태 관리
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    var dialogMapNumber by remember { mutableStateOf("") }
    var currentNavigation by remember { mutableStateOf<() -> Unit>({}) }
    var isButtonEnabled by remember { mutableStateOf(false) } // 버튼 활성화 상태
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFFF))
    ) {
        // MapLine을 가장 뒤에 배치 (크기 조정)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp) // 여백 추가
                .zIndex(-1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource("MapLine.png"),
                contentDescription = "Map Line",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        // 뒤로가기 버튼
        Button(
            onClick = onNavigateToAgreement,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Text(
                "<",
                fontSize = 24.sp,
                color = Color.Black
            )
        }
        
        // 맵 요소들을 지그재그로 배치
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 180.dp) // EnuBack을 위한 공간 확보
                .zIndex(1f)
        ) {
            // School (최상단 오른쪽)
            Image(
                painter = painterResource("School.png"),
                contentDescription = "School",
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-40).dp, y = 120.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // 호버 효과 제거
                    ) {
                        dialogTitle = "학교"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 4"
                        currentNavigation = onNavigateToSchool
                        isButtonEnabled = false // 버튼 비활성화
                        showDialog = true
                    }
            )
            
            // Restaurant (중간 왼쪽)
            Image(
                painter = painterResource("Restaurant.png"),
                contentDescription = "Restaurant",
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = 40.dp, y = (-60).dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // 호버 효과 제거
                    ) {
                        dialogTitle = "식당"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 3"
                        currentNavigation = onNavigateToRestaurant
                        isButtonEnabled = false // 버튼 비활성화
                        showDialog = true
                    }
            )
            
            // Park (중간 오른쪽)
            Image(
                painter = painterResource("Park.png"),
                contentDescription = "Park",
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = (-40).dp, y = 60.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // 호버 효과 제거
                    ) {
                        dialogTitle = "공원"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 2"
                        currentNavigation = onNavigateToPark
                        isButtonEnabled = false // 버튼 비활성화
                        showDialog = true
                    }
            )
            
            // Home (하단 왼쪽)
            Image(
                painter = painterResource("Home.png"),
                contentDescription = "Home",
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = 40.dp, y = (-60).dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // 호버 효과 제거
                    ) {
                        dialogTitle = "집"
                        dialogMessage = "게임을 시작하시겠어요?"
                        dialogMapNumber = "MAP 1"
                        currentNavigation = onNavigateToHome
                        isButtonEnabled = true // 버튼 활성화 (집만 활성화)
                        showDialog = true
                    }
            )
        }
        
        // EnuBack (하단 중앙)
        Image(
            painter = painterResource("EnuBack.png"),
            contentDescription = "Enu Back",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
                .zIndex(1f)
        )
        
        // 다이얼로그 표시
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            dialogMapNumber,
                            color = Color(0xFF3366FF),
                            fontSize = 14.sp
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            dialogTitle,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            dialogMessage,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { showDialog = false },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF2F2F2)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                            ) {
                                Text(
                                    "취소",
                                    color = Color.Black
                                )
                            }
                            
                            Button(
                                onClick = { 
                                    showDialog = false
                                    currentNavigation()
                                },
                                enabled = isButtonEnabled, // 버튼 활성화 상태 적용
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3366FF),
                                    disabledContainerColor = Color(0xFFAAAAAA) // 비활성화 색상
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    "예",
                                    color = if (isButtonEnabled) Color.White else Color(0xFFDDDDDD) // 비활성화 텍스트 색상
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 