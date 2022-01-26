package com.jagmeet.android.gurbaani.ui.hukamnama

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jagmeet.android.gurbaani.databinding.ActivityHukamnamaBinding

class HukamnamaActivity : AppCompatActivity() {
    private val viewModel: HukamnamaViewModel by viewModels()
    private lateinit var binding: ActivityHukamnamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHukamnamaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { viewModel.onTriggerAction(HukamnamaViewModel.HukamnamaActions.GetHukamnama) }

        viewModel.hukamNama.observe(this) { binding.txtHukamnama.text = it }
    }
}