package com.jnu.data.repo

import androidx.core.net.toUri
import com.jnu.network.datasource.UserSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userSource: com.jnu.network.datasource.UserSource,
    private val tokenRepository: TokenRepository
) {
    suspend fun getUserData(): com.jnu.model.UserData {
        val userDTO = userSource.getUserResponseDTO(tokenRepository.getTokenOrThrow())
        return if (userDTO != null) {
            com.jnu.model.UserData(
                userDTO.userName,
                userDTO.petName,
                userDTO.petImageURL.toUri(),
                userDTO.petBirthMonth
            )
        } else com.jnu.model.UserData()
    }
}