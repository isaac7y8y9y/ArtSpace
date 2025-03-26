package com.example.artspace
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

@VisibleForTesting
internal fun nextBtn(state: MutableState<Int>) {
    state.value = if (state.value >= 5) 0 else state.value + 1
}

@VisibleForTesting
internal fun prevBtn(state: MutableState<Int>) {
    state.value = if (state.value <= 0) 5 else state.value - 1
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    // Create the state once here and pass it down
    var state by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtworkImage(state = state)
        Spacer(modifier = Modifier.height(24.dp))
        TitleLayout(state = state)
        ButtonsLayout(state = state, onStateChange = { newState -> state = newState})
    }
}

@Composable
fun ArtworkImage(state: Int, modifier: Modifier = Modifier) {
    Log.d("MyTag", state.toString())
    val imageResource = when(state) {
        0 -> R.drawable.aliceandrinkimono
        1 -> R.drawable.aliceandrin
        2 -> R.drawable.blueknight
        3 -> R.drawable.swordwomanv3
        4 -> R.drawable.gmcommandoposter
        else -> R.drawable.waterprincesssquare
    }

    val myContentDescription = when(state) {
        0 -> stringResource(R.string.alice_n_rin_kimono_description)
        1 -> stringResource(R.string.alice_n_rin_description)
        2 -> stringResource(R.string.blue_knight_description)
        3 -> stringResource(R.string.sword_woman_description)
        4 -> stringResource(R.string.gm_commander_description)
        else -> stringResource(R.string.water_princess_description)
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = myContentDescription,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Color(0xFFB6DDF9))
    )
}

@Composable
fun TitleLayout(state: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFE5F3FD))
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val titleName = when(state) {
            0 -> stringResource(R.string.alice_n_rin_kimono)
            1 -> stringResource(R.string.alice_n_rin)
            2 -> stringResource(R.string.blue_knight)
            3 -> stringResource(R.string.sword_woman)
            4 -> stringResource(R.string.gm_commander)
            else -> stringResource(R.string.water_princess)
        }
        Text(
            text = titleName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        val titleInfo = when(state) {
            0 -> stringResource(R.string.alice_n_rin_kimono_info)
            1 -> stringResource(R.string.alice_n_rin_info)
            2 -> stringResource(R.string.blue_knight_info)
            3 -> stringResource(R.string.sword_woman_info)
            4 -> stringResource(R.string.gm_commander_info)
            else -> stringResource(R.string.water_princess_info)
        }
        Text(
            text = titleInfo,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ButtonsLayout(state: Int, onStateChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    val mutableState = remember { mutableIntStateOf(state) }

    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                prevBtn(mutableState)
                onStateChange(mutableState.intValue)
                Log.d("MyTag", "Previous: ${mutableState.intValue}")
            },
            modifier = Modifier
                .width(120.dp)
                .height(48.dp)
        ) {
            Text("Previous")
        }

        Button(
            onClick = {
                nextBtn(mutableState)
                onStateChange(mutableState.intValue)
                Log.d("MyTag", "Next: ${mutableState.intValue}")
            },
            modifier = Modifier
                .width(120.dp)
                .height(48.dp)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}