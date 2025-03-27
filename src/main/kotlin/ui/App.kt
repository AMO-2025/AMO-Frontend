package ui

import androidx.compose.runtime.*
import ui.screens.LoginScreen
import ui.screens.UserInfoScreen
import ui.screens.NicknameScreen
import ui.screens.AgreementScreen
import ui.screens.MapSelectionScreen
import ui.screens.HomeScreen
import ui.screens.ParkScreen
import ui.screens.RestaurantScreen
import ui.screens.SchoolScreen
import util.TokenManager

@Composable
fun App() {
    // 앱 시작 시 저장된 토큰 삭제 (개발 중에만 사용)
    val tokenManager = remember { TokenManager() }
    LaunchedEffect(Unit) {
        tokenManager.clearToken()
    }
    
    
    var currentScreen by remember { mutableStateOf("login") }
    
    when (currentScreen) {
        "login" -> LoginScreen(
            onNavigateToUserInfo = { currentScreen = "userInfo" }
        )
        "userInfo" -> UserInfoScreen(
            onNavigateToLogin = { currentScreen = "login" },
            onNavigateToNickname = { currentScreen = "nickname" }
        )
        "nickname" -> NicknameScreen(
            onNavigateToUserInfo = { currentScreen = "userInfo" },
            onNavigateToAgreement = { currentScreen = "agreement" }
        )
        "agreement" -> AgreementScreen(
            onNavigateToNickname = { currentScreen = "nickname" },
            onNavigateToMapSelection = { currentScreen = "mapSelection" }
        )
        "mapSelection" -> MapSelectionScreen(
            onNavigateToAgreement = { currentScreen = "agreement" },
            onNavigateToHome = { currentScreen = "home" },
            onNavigateToPark = { currentScreen = "park" },
            onNavigateToRestaurant = { currentScreen = "restaurant" },
            onNavigateToSchool = { currentScreen = "school" },
            onNavigateBack = { currentScreen = "agreement" }
        )
        "home" -> HomeScreen(
            onNavigateBack = { currentScreen = "mapSelection" }
        )
        "park" -> ParkScreen(
            onNavigateBack = { currentScreen = "mapSelection" }
        )
        "restaurant" -> RestaurantScreen(
            onNavigateBack = { currentScreen = "mapSelection" }
        )
        "school" -> SchoolScreen(
            onNavigateBack = { currentScreen = "mapSelection" }
        )
    }
} 