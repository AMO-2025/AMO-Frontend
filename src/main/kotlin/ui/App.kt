package ui

import androidx.compose.runtime.*
import ui.screens.FirstScreen
import ui.screens.SecondScreen
import ui.screens.ThirdScreen
import ui.screens.FourthScreen
import ui.screens.FifthScreen
import ui.screens.HomeScreen
import ui.screens.ParkScreen
import ui.screens.RestaurantScreen
import ui.screens.SchoolScreen

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("first") }
    
    when (currentScreen) {
        "first" -> FirstScreen(
            onNavigateToSecond = { currentScreen = "second" }
        )
        "second" -> SecondScreen(
            onNavigateToFirst = { currentScreen = "first" },
            onNavigateToThird = { currentScreen = "third" }
        )
        "third" -> ThirdScreen(
            onNavigateToSecond = { currentScreen = "second" },
            onNavigateToFourth = { currentScreen = "fourth" }
        )
        "fourth" -> FourthScreen(
            onNavigateToThird = { currentScreen = "third" },
            onNavigateToFifth = { currentScreen = "fifth" }
        )
        "fifth" -> FifthScreen(
            onNavigateToFourth = { currentScreen = "fourth" },
            onNavigateToHome = { currentScreen = "home" },
            onNavigateToPark = { currentScreen = "park" },
            onNavigateToRestaurant = { currentScreen = "restaurant" },
            onNavigateToSchool = { currentScreen = "school" }
        )
        "home" -> HomeScreen(
            onNavigateBack = { currentScreen = "fifth" }
        )
        "park" -> ParkScreen(
            onNavigateBack = { currentScreen = "fifth" }
        )
        "restaurant" -> RestaurantScreen(
            onNavigateBack = { currentScreen = "fifth" }
        )
        "school" -> SchoolScreen(
            onNavigateBack = { currentScreen = "fifth" }
        )
    }
} 