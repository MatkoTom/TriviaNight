package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import tomljanovic.matko.trivianightapp.R

@Composable
@Destination
fun LeaderBoard(
    navigator: DestinationsNavigator
){
    ScreenBackground(backgroundId = R.drawable.img_background)
    Text(text = "Leaderboard")
}