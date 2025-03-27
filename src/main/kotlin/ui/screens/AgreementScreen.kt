package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun AgreementScreen(
    onNavigateToNickname: () -> Unit,
    onNavigateToMapSelection: () -> Unit
) {
    var allAgreed by remember { mutableStateOf(false) }
    var serviceAgreed1 by remember { mutableStateOf(false) }
    var serviceAgreed2 by remember { mutableStateOf(false) }
    var marketingAgreed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(
            onClick = onNavigateToNickname,
            modifier = Modifier.padding(start = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "뒤로가기",
                tint = Color.Black
            )
        }
        
        // 제목과 설명
        Text(
            "AMO에 오신 것을 환영해요!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )
        
        Text(
            "서비스 시작을 위해\n번지 가입 및 정보 제공에 동의해주세요.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        // 전체 동의 체크박스
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = allAgreed,
                onCheckedChange = { checked ->
                    allAgreed = checked
                    serviceAgreed1 = checked
                    serviceAgreed2 = checked
                    marketingAgreed = checked
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4169E1)
                )
            )
            Text(
                "전체동의",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Divider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = Color.LightGray
        )
        
        // 개별 동의 항목들
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = serviceAgreed1,
                    onCheckedChange = { 
                        serviceAgreed1 = it
                        allAgreed = serviceAgreed1 && serviceAgreed2 && marketingAgreed
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4169E1)
                    )
                )
                Text("(필수) 서비스이용약관 동의")
            }
            TextButton(onClick = { /* TODO: 약관 상세보기 */ }) {
                Text(
                    "더보기",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = serviceAgreed2,
                    onCheckedChange = { 
                        serviceAgreed2 = it
                        allAgreed = serviceAgreed1 && serviceAgreed2 && marketingAgreed
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4169E1)
                    )
                )
                Text("(필수) 서비스이용약관 동의")
            }
            TextButton(onClick = { /* TODO: 약관 상세보기 */ }) {
                Text(
                    "더보기",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = marketingAgreed,
                    onCheckedChange = { 
                        marketingAgreed = it
                        allAgreed = serviceAgreed1 && serviceAgreed2 && marketingAgreed
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4169E1)
                    )
                )
                Text("(선택) 마케팅 관련 동의")
            }
            TextButton(onClick = { /* TODO: 약관 상세보기 */ }) {
                Text(
                    "더보기",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // 시작하기 버튼
        Button(
            onClick = { onNavigateToMapSelection() },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4169E1)
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = serviceAgreed1 && serviceAgreed2
        ) {
            Text("시작하기", color = Color.White)
        }
    }
} 