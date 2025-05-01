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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

// 맵 요소들의 위치를 정의하는 상수
private object MapPositions {
    // MapLine 크기 조정
    val MAP_LINE_HORIZONTAL_PADDING = 80.dp
    val MAP_LINE_VERTICAL_PADDING = 80.dp
    
    // Restaurant 위치
    val RESTAURANT_OFFSET_X = 15.dp
    val RESTAURANT_OFFSET_Y = 120.dp
    
    // School 위치
    val SCHOOL_OFFSET_X = (-42).dp
    val SCHOOL_OFFSET_Y = 0.dp  // 위로 이동
    
    // Park 위치
    val PARK_OFFSET_X = (-42).dp
    val PARK_OFFSET_Y = (-40).dp
    
    // Home 위치
    val HOME_OFFSET_X = 15.dp
    val HOME_OFFSET_Y = (-210).dp
    
    // 은우 캐릭터 위치
    val ENU_OFFSET_X = (-40).dp
    val ENU_OFFSET_Y = (-30).dp
    
    // 요소들의 크기
    val LOCATION_SIZE = 170.dp
    val ENU_SIZE = 150.dp
}

@Composable
fun MapSelectionScreen(
    onNavigateToAgreement: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToPark: () -> Unit,
    onNavigateToRestaurant: () -> Unit,
    onNavigateToSchool: () -> Unit,
    onNavigateBack: () -> Unit
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
        // MapLine 크기 및 위치 조정
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MapPositions.MAP_LINE_HORIZONTAL_PADDING,
                    vertical = MapPositions.MAP_LINE_VERTICAL_PADDING
                )
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
        
        // 맵 요소들을 정확한 경로 위치에 배치
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            // Restaurant (좌측 상단 - 첫 번째 커브)
            Image(
                painter = painterResource("Restaurant.png"),
                contentDescription = "Restaurant",
                modifier = Modifier
                    .size(MapPositions.LOCATION_SIZE)
                    .align(Alignment.TopStart)
                    .offset(x = MapPositions.RESTAURANT_OFFSET_X, y = MapPositions.RESTAURANT_OFFSET_Y)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        dialogTitle = "식당"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 3"
                        currentNavigation = onNavigateToRestaurant
                        isButtonEnabled = false
                        showDialog = true
                    }
            )
            
            // School (우측 상단 - 첫 번째 커브 끝)
            Image(
                painter = painterResource("School.png"),
                contentDescription = "School",
                modifier = Modifier
                    .size(MapPositions.LOCATION_SIZE)
                    .align(Alignment.TopEnd)
                    .offset(x = MapPositions.SCHOOL_OFFSET_X, y = MapPositions.SCHOOL_OFFSET_Y)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        dialogTitle = "학교"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 4"
                        currentNavigation = onNavigateToSchool
                        isButtonEnabled = false
                        showDialog = true
                    }
            )
            
            // Park (우측 중앙 - 두 번째 커브 끝)
            Image(
                painter = painterResource("Park.png"),
                contentDescription = "Park",
                modifier = Modifier
                    .size(MapPositions.LOCATION_SIZE)
                    .align(Alignment.CenterEnd)
                    .offset(x = MapPositions.PARK_OFFSET_X, y = MapPositions.PARK_OFFSET_Y)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        dialogTitle = "공원"
                        dialogMessage = "게임을 시작하시겠어요?\n(아직 해금되지 않았습니다)"
                        dialogMapNumber = "MAP 2"
                        currentNavigation = onNavigateToPark
                        isButtonEnabled = false
                        showDialog = true
                    }
            )
            
            // Home (좌측 하단 - 세 번째 커브)
            Image(
                painter = painterResource("Home.png"),
                contentDescription = "Home",
                modifier = Modifier
                    .size(MapPositions.LOCATION_SIZE)
                    .align(Alignment.BottomStart)
                    .offset(x = MapPositions.HOME_OFFSET_X, y = MapPositions.HOME_OFFSET_Y)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        dialogTitle = "집"
                        dialogMessage = "게임을 시작하시겠어요?"
                        dialogMapNumber = "MAP 1"
                        currentNavigation = onNavigateToHome
                        isButtonEnabled = true
                        showDialog = true
                    }
            )
            
            // 은우 캐릭터 위치 (오른쪽 하단 코너)
            Image(
                painter = painterResource("EnuBack.png"),
                contentDescription = "Enu Back",
                modifier = Modifier
                    .size(MapPositions.ENU_SIZE)
                    .align(Alignment.BottomEnd)
                    .offset(x = MapPositions.ENU_OFFSET_X, y = MapPositions.ENU_OFFSET_Y)
                    .zIndex(1f)
            )
        }
        
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
                                enabled = isButtonEnabled,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3366FF),
                                    disabledContainerColor = Color(0xFFAAAAAA)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    "예",
                                    color = if (isButtonEnabled) Color.White else Color(0xFFDDDDDD)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 