package com.jagmeet.android.gurbaani.ui.hukamnama

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.datasource.repository.hukamnama.HukamnamaRepository
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.ui.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HukamnamaViewModel @Inject constructor(private val hukamnamaRepository: HukamnamaRepository) :
    ViewModel() {

    var hukamNamaState by mutableStateOf(HukamnamaUiState())
        private set

    init {
        getHukamNama()
    }

    private fun getHukamNama() {
        Timber.d("getHukamNama requested from Ui layer")
        viewModelScope.launch {
            hukamnamaRepository.getHukamnama(false).collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        Timber.d("getHukamNama result success")
                        var result = it.data as HukamnamaDetail
                        hukamNamaState = hukamNamaState.copy(hukamnama = result);
                    }
                    Result.Status.ERROR -> {
                        Timber.e("getHukamNama result error ${it.message.orEmpty()}")
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