package de.ericmuench.diceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.ericmuench.diceapp.ui.composables.SimpleDiceView
import de.ericmuench.diceapp.ui.theme.DiceAppTheme
import de.ericmuench.diceapp.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel;
import de.ericmuench.diceapp.ui.composables.ClassicDiceView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}



@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()){
    val displayNumber : Int by viewModel.number.collectAsState(initial = 1)
    MainScreenContent(displayNumber, viewModel::nextValue)
}

@Composable
fun MainScreenContent(displayNumber: Int, onDice: () -> Unit){
    DiceAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
                    Text("Dice App")
                }
            }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxSize()
            ) {
                ClassicDiceView(displayNumber, onClick = onDice)

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(displayNumber = 6) {}
}