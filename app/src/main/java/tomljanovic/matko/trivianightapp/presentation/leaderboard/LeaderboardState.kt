package tomljanovic.matko.trivianightapp.presentation.leaderboard

import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem

data class LeaderboardState(
    val items: List<LeaderboardItem> = emptyList(),
    val isLoading: Boolean = false
)
