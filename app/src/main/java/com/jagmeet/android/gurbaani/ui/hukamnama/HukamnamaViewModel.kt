package com.jagmeet.android.gurbaani.ui.hukamnama

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagmeet.android.gurbaani.business.datasource.HukamnamaRepository
import com.jagmeet.android.gurbaani.business.datasource.remote.HukamnamaNetworkSource
import com.jagmeet.android.gurbaani.business.datasource.remote.api.HukamnamaEndPoint
import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.model.Hukamnama
import com.jagmeet.android.gurbaani.business.model.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HukamnamaViewModel : ViewModel() {

    private var _hukamNama: MutableLiveData<String> = MutableLiveData()
    val hukamNama: LiveData<String> = _hukamNama
    var hukamnamaRepository: HukamnamaRepository = HukamnamaRepository();

    init {
        Log.d(TAG, " it is a new viewmodel")

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onTriggerAction(hukamnamaActions: HukamnamaActions) {
        when (hukamnamaActions) {
            is HukamnamaActions.GetHukamnama -> {
                Log.d(TAG, " get hukamnama called")
                viewModelScope.launch {
                    hukamnamaRepository.getHukamnama().collect {
                        when (it.status) {
                            Result.Status.SUCCESS -> {
                                var hukamnama = it.data as Hukamnama
                                _hukamNama.value = hukamnama.hukamnama
                            }
                            Result.Status.ERROR ->
                                Log.d(TAG, " get hukamnama called")
                        }
                    }
                }
            }
        }
    }

    sealed class HukamnamaActions {
        object GetHukamnama : HukamnamaActions()
    }

    companion object {
        private const val TAG: String = "HukamnamaViewModel"
    }
}