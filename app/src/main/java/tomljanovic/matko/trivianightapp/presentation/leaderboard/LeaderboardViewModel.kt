package tomljanovic.matko.trivianightapp.presentation.leaderboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem
import tomljanovic.matko.trivianightapp.domain.repository.LeaderboardRepository
import tomljanovic.matko.trivianightapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repo: LeaderboardRepository
) : ViewModel() {
    var leaderboardState by mutableStateOf(LeaderboardState())

    init {
        getLeaderboard()
    }

    private fun getLeaderboard() = viewModelScope.launch {
        repo.getLeaderboard().collect { result ->
            when (result) {
                is Resource.Error -> {
                    // ignore
                }

                is Resource.Loading -> leaderboardState = leaderboardState.copy(
                    isLoading = result.isLoading
                )

                is Resource.Success -> {
                    result.data?.let {
                        leaderboardState = leaderboardState.copy(
                            items = it
                        )
                    }
                }
            }
        }
    }

    fun addPlayer(player: LeaderboardItem) = viewModelScope.launch {
        repo.addToLeaderboard(leaderboardItem = player)
    }

    fun clearLeaderboard() = viewModelScope.launch {
        repo.clearLeaderboard()
    }
}
