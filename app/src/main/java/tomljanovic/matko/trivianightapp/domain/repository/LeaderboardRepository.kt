package tomljanovic.matko.trivianightapp.domain.repository

import kotlinx.coroutines.flow.Flow
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem
import tomljanovic.matko.trivianightapp.util.Resource

interface LeaderboardRepository {
    fun getLeaderboard(): Flow<Resource<List<LeaderboardItem>>>
    suspend fun addToLeaderboard(leaderboardItem: LeaderboardItem)
    suspend fun clearLeaderboard()
}
