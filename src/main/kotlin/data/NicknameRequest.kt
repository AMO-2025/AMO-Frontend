package data

import kotlinx.serialization.Serializable

@Serializable
data class NicknameRequest(
    val nickname: String
) 