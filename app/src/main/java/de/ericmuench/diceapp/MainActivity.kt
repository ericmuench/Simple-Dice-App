package de.ericmuench.diceapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.ericmuench.diceapp.ui.composables.*
import de.ericmuench.diceapp.ui.theme.DiceAppTheme
import de.ericmuench.diceapp.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import de.ericmuench.diceapp.util.MenuItem
import de.ericmuench.diceapp.util.MenuItemMode

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
    val context = LocalContext.current
    DiceAppTheme {
        Scaffold(
            topBar = {
                AppBar(
                    title = stringResource(id = R.string.app_name),
                    menuItems = listOf(
                        MenuItem(
                            title = stringResource(id = R.string.settings),
                            icon = Icons.Default.Settings,
                            displayTitleUnderIcon = false,
                            itemMode = MenuItemMode.NEVER,
                            onClick = {
                                //TODO: Navigate to settings
                                Toast.makeText(context,"test", Toast.LENGTH_SHORT).show()
                                println("TEST")
                            }
                        )
                    )
                )
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