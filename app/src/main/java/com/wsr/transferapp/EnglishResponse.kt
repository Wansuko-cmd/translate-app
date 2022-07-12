package com.wsr.transferapp

import kotlinx.serialization.Serializable

@Serializable
data class EnglishResponse(val code: Int, val text: String)
