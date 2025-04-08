package com.jnu.model

class APIException(
    val errorResponse: ErrorResponse
) : Exception(errorResponse.message)