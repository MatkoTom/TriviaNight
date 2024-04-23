package tomljanovic.matko.trivianightapp.presentation.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import tomljanovic.matko.trivianightapp.R
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem
import tomljanovic.matko.trivianightapp.presentation.trivia.ScreenBackground
import tomljanovic.matko.trivianightapp.ui.theme.fontFamily


@Composable
@Preview
@Destination
fun LeaderboardScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val state = viewModel.leaderboardState

    ScreenBackground(backgroundId = R.drawable.img_background)

    // Testing data
    val items = listOf(
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        ),
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        ),
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        ),
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        ),
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        ),
        LeaderboardItem(
            score = 0,
            name = "Ime prezime"
        )
    )

    Column {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp)
                )
            }
        } else {
            LeaderboardTitle()
            LazyColumn {
                itemsIndexed(
                    items = items
                ) { index, item ->
                    if (index < 3) {
                        TopPlayers(
                            leaderBoardPlayer = item,
                            place = index + 1
                        )
                    } else {
                        OtherPlayers(
                            leaderBoardPlayer = item,
                            place = index + 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun LeaderboardTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(top = 32.dp),
            painter = painterResource(id = R.drawable.img_medal),
            contentDescription = "Medal image"
        )
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Leaderboard",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.displayMedium,
            color = Color(0xFFFFCB00),
            fontFamily = fontFamily,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
@Preview
fun TopPlayers(
    leaderBoardPlayer: LeaderboardItem = LeaderboardItem(score = 0, name = "Test"),
    place: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp),
            shape = CircleShape,
            color = when (place) {
                1 -> Color(0xFFFFCB00)
                2 -> Color(0xFFD4D4D4)
                3 -> Color(0xFFE18A3A)
                else -> Color.White
            }
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .wrapContentSize(align = Alignment.Center),
                text = place.toString(),
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            text = leaderBoardPlayer.name,
            color = Color.White,
            fontFamily = fontFamily,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = leaderBoardPlayer.score.toString(),
            color = Color(0xFFFFCB00),
            fontFamily = fontFamily,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
@Preview
fun OtherPlayers(
    leaderBoardPlayer: LeaderboardItem = LeaderboardItem(score = 0, name = "Test"),
    place: Int = 0
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFD4D4D4)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OtherPlayersText(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                text = place.toString()
            )

            OtherPlayersText(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                text = leaderBoardPlayer.name
            )

            OtherPlayersText(
                modifier = Modifier.padding(16.dp),
                text = leaderBoardPlayer.score.toString()
            )
        }
    }
}

@Composable
fun OtherPlayersText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        modifier = modifier,
        text = text,
        color = Color.Black,
        fontSize = 17.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold
    )
}