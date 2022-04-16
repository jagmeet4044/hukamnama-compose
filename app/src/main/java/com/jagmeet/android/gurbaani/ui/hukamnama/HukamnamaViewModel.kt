package com.jagmeet.android.gurbaani.ui.hukamnama

import com.jagmeet.android.gurbaani.datasource.repository.hukamnama.HukamnamaRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.ui.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HukamnamaViewModel @Inject constructor(private val hukamnamaRepository: HukamnamaRepository) :
    ViewModel() {

    var hukamNamaState by mutableStateOf(HukamnamaUiState())
        private set

    init {
        getHukamNama()
        Log.d(TAG, " it is a new viewmodel")
    }

    private fun getHukamNama() {
        Log.d(TAG, " get hukamnama called")
        viewModelScope.launch {
            hukamnamaRepository.getHukamnama(false).collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        Log.d(TAG, "SUCCESS")
                        var result = it.data as HukamnamaDetail
                        hukamNamaState = hukamNamaState.copy(hukamnama = result);
                    }
                    Result.Status.ERROR -> {
                        Log.d(TAG, "ERROR")
                        hukamNamaState = hukamNamaState.copy(
                            userMessages = hukamNamaState.userMessages + Message(
                                id = UUID.randomUUID().mostSignificantBits,
                                message = it.message.orEmpty()
                            )
                        )
                    }
                }
            }
        }
    }

    fun userMessageShown(messageId: Long) {
        val messages = hukamNamaState.userMessages.filterNot { it.id == messageId }
        hukamNamaState.copy(userMessages = messages)
    }

    sealed class HukamnamaActions {
        object GetHukamnama : HukamnamaActions()
    }

    companion object {
        private const val TAG: String = "HukamnamaViewModel"
    }
}