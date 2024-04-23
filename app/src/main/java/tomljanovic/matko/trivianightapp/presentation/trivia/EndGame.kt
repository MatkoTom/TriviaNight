package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import tomljanovic.matko.trivianightapp.R

@Composable
@Destination()
fun EndGame(){
    ScreenBackground(backgroundId = R.drawable.img_background)
}