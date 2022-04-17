package com.jagmeet.android.gurbaani.ui.hukamnama

import androidx.compose.runtime.Composable

sealed class TabItem(var title: String) {
    object Punjabi : TabItem("ਪੰਜਾਬੀ")
    object English : TabItem("English")
    object Hindi : TabItem("Hindi")
}
