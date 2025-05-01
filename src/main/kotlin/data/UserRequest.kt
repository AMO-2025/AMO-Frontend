package data

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val userIdentifier: String,
    val phoneNumber: String
)