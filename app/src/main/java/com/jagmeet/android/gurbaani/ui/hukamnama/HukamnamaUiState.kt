package com.jagmeet.android.gurbaani.ui.hukamnama

import androidx.compose.runtime.MutableState
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.ui.Message

data class HukamnamaUiState(
    val hukamnama: HukamnamaDetail? = null,
    val isLoading: Boolean = false,
    val userMessages: List<Message> = emptyList()
   )