import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import ui.App

fun main() = singleWindowApplication(
    state = WindowState(
        size = DpSize(
            width = 450.dp,  // 1080px의 1/3
            height = 800.dp  // 2340px의 1/3
        )
    ),
    title = "AMO",
    resizable = false  // 창 크기 조절 불가능하게 설정
) {
    App()
}