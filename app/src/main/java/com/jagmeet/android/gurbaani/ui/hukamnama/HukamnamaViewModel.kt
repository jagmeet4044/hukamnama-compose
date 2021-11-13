package com.jagmeet.android.gurbaani.ui.hukamnama

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HukamnamaViewModel : ViewModel() {

    var _hukamNama: MutableLiveData<String> = MutableLiveData()
    val hukamNama: LiveData<String> = _hukamNama

    init {
        Log.d("jagmeetLog", " it is a new viewmodel")
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onTriggerAction(hukamnamaActions: HukamnamaActions) {
        when (hukamnamaActions) {
            is HukamnamaActions.GetHukamnama -> {
                Log.d("jagmeetLog", " get hukamnama called")
                _hukamNama.value = "here it is"
            }
        }

    }

    sealed class HukamnamaActions {
        object GetHukamnama : HukamnamaActions()
    }
}