package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.UserRequest

@Composable
fun SecondScreen(
    onNavigateToFirst: () -> Unit,
    onNavigateToThird: () -> Unit
) {
    var userIdentifier by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    
    // 임시 데이터
    val tempIdentifier = "JINO338"
    val tempPhoneNumber = "01042271182"
    
    // 입력 필드 유효성 검사 (버튼 활성화 조건)
    val isPhoneNumberValid = phoneNumber.length == 10 || phoneNumber.length == 11
    val isButtonEnabled = userIdentifier.isNotEmpty() && isPhoneNumberValid

    val focusManager = LocalFocusManager.current
    val phoneNumberFocusRequester = remember { FocusRequester() }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = onNavigateToFirst,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                "←",
                fontSize = 24.sp,
                color = Color.Black
            )
        }
        
        Text(
            "식별번호와",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            "전화번호를 입력해주세요",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            "식별 번호는 사전에 안내드린\n문자 메시지에서 확인할 수 있어요.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        OutlinedTextField(
            value = userIdentifier,
            onValueChange = { newValue ->
                // 영문과 숫자만 허용
                val filteredValue = newValue
                    .filter { char -> 
                        char.isLetterOrDigit() && // 영문과 숫자만
                        (!char.isLetter() || char.isUpperCase() || char.isLowerCase()) // 한글 제외
                    }
                    .uppercase()  // 소문자를 대문자로 변환
                    .take(10)  // 최대 길이 제한
                userIdentifier = filteredValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            placeholder = { Text("BE015") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    phoneNumberFocusRequester.requestFocus()
                }
            ),
            singleLine = true
        )
        
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                val filteredValue = newValue.filter { it.isDigit() }.take(11)
                phoneNumber = filteredValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusRequester(phoneNumberFocusRequester),
            placeholder = { Text("01012345678") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        // 에러 메시지
        if (showError) {
            Text(
                text = "확인되지 않은 식별번호와 전화번호입니다.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        
        Button(
            onClick = { 
                // 데이터 일치 여부 확인
                if (userIdentifier == tempIdentifier && phoneNumber == tempPhoneNumber) {
                    showError = false
                    val request = UserRequest(
                        userIdentifier = userIdentifier,
                        phoneNumber = phoneNumber
                    )
                    onNavigateToThird()
                } else {
                    // 불일치시 입력값 초기화 및 에러 메시지 표시
                    userIdentifier = ""
                    phoneNumber = ""
                    showError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4169E1)
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = isButtonEnabled  // 입력 필드 조건만으로 활성화 여부 결정
        ) {
            Text("다음으로", color = Color.White)
        }
    }
} 