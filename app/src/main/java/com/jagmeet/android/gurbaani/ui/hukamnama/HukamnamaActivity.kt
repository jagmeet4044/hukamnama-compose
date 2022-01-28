package com.jagmeet.android.gurbaani.ui.hukamnama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.jagmeet.android.gurbaani.databinding.ActivityHukamnamaBinding
import com.jagmeet.android.gurbaani.ui.theme.GurbaaniTheme

class HukamnamaActivity : ComponentActivity() {
    private val viewModel: HukamnamaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hukamNama = viewModel.hukamNama
        setContent {
            GurbaaniTheme {
                Surface(color = MaterialTheme.colors.background) {
                    showHukamNama(hukamNama)
                }
            }
        }
        viewModel.onTriggerAction(HukamnamaViewModel.HukamnamaActions.GetHukamnama)
    }

    @Composable
    private fun showHukamNama(hukamNama: State<String>) {
        Text(text = hukamNama.value)
    }

}