package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.NicknameRequest

@Composable
fun ThirdScreen(onNavigateToSecond: () -> Unit, onNavigateToFourth: () -> Unit) {
    var nickname by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = onNavigateToSecond,
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
            "닉네임을 입력해주세요",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )
        
        Text(
            "한글 또는 영문\n2자~8자까지 입력할 수 있어요.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        OutlinedTextField(
            value = nickname,
            onValueChange = { newValue ->
                val filteredValue = newValue
                    .filter { char ->
                        char.isLetter() &&
                        (char.isLetterOrDigit() || char in '가'..'힣')
                    }
                    .take(8)
                nickname = filteredValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            placeholder = { Text("집구로미") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
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
        
        Button(
            onClick = { 
                val request = NicknameRequest(nickname = nickname)
                onNavigateToFourth()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4169E1)
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = nickname.length >= 2
        ) {
            Text("다음으로", color = Color.White)
        }
    }
} 