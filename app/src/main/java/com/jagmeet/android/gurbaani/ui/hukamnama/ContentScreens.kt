package com.jagmeet.android.gurbaani.ui.hukamnama

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import com.jagmeet.android.gurbaani.model.Hukamnama


@Composable
fun showHukamNamaScreen(hukamnama: Hukamnama) {
    val scrollState = rememberScrollState()
    var size: MutableState<Float> = remember { mutableStateOf(20f) }
    val fontSize = remember { mutableStateOf(size.value.sp) }
    val isExpanded = remember { mutableStateOf(false) }
    Surface(contentColor = MaterialTheme.colors.onSurface) {
        Column() {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 50.dp))
                showHukamNamaInfo(hukamnama = hukamnama, fontSize)
                Spacer(modifier = Modifier.padding(top = 20.dp))
                showHukamNama(hukamnama = hukamnama, fontSize, isExpanded)
                //  Spacer(modifier = Modifier.padding(top = 40.dp))
                //  showExplanation(hukamnama, fontSize)
                Spacer(modifier = Modifier.padding(top = 20.dp))
            }
            var switched by remember { mutableStateOf(isExpanded.value) }
            Row(horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Explanation", modifier = Modifier.padding(8.dp))
                Checkbox(
                    checked = switched,
                    //colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.secondary),
                    modifier = Modifier.padding(8.dp),
                    onCheckedChange = {
                        switched = it
                        isExpanded.value = it }
                )
            }
            showFontSizeSlider(size = size, fontSize = fontSize)
        }

    }
}

@Composable
fun showExplanation(hukamnama: Hukamnama, fontSize: MutableState<TextUnit>) {
    Column() {
        Text(
            text = "ਵਿਆਖਿਆ", modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            fontSize = fontSize.value
        )
        for (hukam in hukamnama.explanation) {
            Text(
                text = hukam, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = fontSize.value
            )

        }
    }
}

@Composable
fun showHukamNamaInfo(hukamnama: Hukamnama, fontSize: MutableState<TextUnit>) {
    Column() {
        Text(
            text = hukamnama.gregorianDate, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = fontSize.value
        )
        Text(
            text = hukamnama.nanakShahiDate, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = fontSize.value
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = hukamnama.info, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = fontSize.value
        )
    }

}

@Composable
fun showHukamNama(
    hukamnama: Hukamnama,
    fontSize: MutableState<TextUnit>,
    isExpanded: MutableState<Boolean>
) {
    Column() {
        hukamnama.lines.forEachIndexed(action = { index: Int, hukam: String ->
            Text(
                text = hukam, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = fontSize.value
            )
            if (isExpanded.value)
                Text(
                    text = hukamnama.explanation.get(index = index), modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .align(Alignment.CenterHorizontally), color = Color.Cyan,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    fontSize = fontSize.value
                )
        })
    }
}

@Composable
fun showFontSizeSlider(size: MutableState<Float>, fontSize: MutableState<TextUnit>) {
    var sliderState by remember { mutableStateOf(size.value) }
    ConstraintLayout(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
        val (button, text1, text2) = createRefs()
        Slider(
            value = sliderState, modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
            },
            onValueChange = { newValue ->
                sliderState = newValue
                size.value = newValue
                fontSize.value = size.value.sp
            }, valueRange = 10f..50f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary
            )
        )
        Text("A", Modifier.constrainAs(text1) {
            top.linkTo(parent.top, margin = 25.dp)
            start.linkTo(parent.start, margin = 10.dp)
        })
        Text("A", Modifier.constrainAs(text2) {
            top.linkTo(parent.top, margin = 19.dp)
            end.linkTo(parent.end, margin = 10.dp)
        }, fontSize = 30.sp)
    }
}

