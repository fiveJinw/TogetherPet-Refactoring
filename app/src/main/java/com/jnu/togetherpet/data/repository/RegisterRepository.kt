package com.jnu.togetherpet.data.repository

import android.util.Log
import com.jnu.network.datasource.RegisterSource
import com.jnu.network.model.PetRegisterDTO
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val registerSource: com.jnu.network.datasource.RegisterSource,
    private val tokenRepository: TokenRepository
) {
    suspend fun registerUserAndPet(
        petRegisterDTO: com.jnu.network.model.PetRegisterDTO,
        petImage: File,
        userName: String
    ) {
        try{
            Log.d("testt", " repo : ${petImage}, ${userName}")
            Log.d("testt", "token : ${tokenRepository.getTokenOrThrow()}")
            registerSource.registerUserAndPet(
                tokenRepository.getTokenOrThrow(),
                petRegisterDTO,
                petImage,
                userName
            )
        } catch (e : com.jnu.model.APIException){
            if (e.errorResponse.code == -20401) {
                registerSource.registerUserAndPet(
                    tokenRepository.getTokenOrThrow(),
                    com.jnu.network.model.PetRegisterDTO(
                        petRegisterDTO.petNAme,
                        petRegisterDTO.petBirthMonth,
                        "말티즈",
                        petRegisterDTO.isNeutering,
                        petRegisterDTO.petFeature
                    ),
                    petImage,
                    userName
                )
                Log.d("testt", "보내기 :${
                    com.jnu.network.model.PetRegisterDTO(
                        petRegisterDTO.petNAme,
                        petRegisterDTO.petBirthMonth,
                        "말티즈",
                        petRegisterDTO.isNeutering,
                        petRegisterDTO.petFeature
                    )
                }")
                Log.d("testt", "종없음 : ${e.hashCode()}, ${e.errorResponse.hashCode()}, ${e.message}")
            }
            else{
                Log.d("testt", "등록 에러 발생 :  ${e.hashCode()}, ${e.errorResponse.hashCode()}, ${e.message}")
            }
        }
    }
}