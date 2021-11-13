package com.jagmeet.android.gurbaani.ui.hukamnama

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.jagmeet.android.gurbaani.R
import com.jagmeet.android.gurbaani.databinding.ActivityHukamnamaBinding
import kotlinx.coroutines.channels.ActorScope

class HukamnamaActivity : AppCompatActivity() {
    private val viewModel: HukamnamaViewModel by viewModels()
    private lateinit var binding: ActivityHukamnamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHukamnamaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { viewModel.onTriggerAction(HukamnamaViewModel.HukamnamaActions.GetHukamnama) }

        viewModel.hukamNama.observe(this) { binding.txtHukamnama.setText(it) }
    }
}