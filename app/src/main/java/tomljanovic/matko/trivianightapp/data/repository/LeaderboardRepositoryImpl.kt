package tomljanovic.matko.trivianightapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tomljanovic.matko.trivianightapp.data.local.TriviaDatabase
import tomljanovic.matko.trivianightapp.data.mappers.toLeaderboardEntity
import tomljanovic.matko.trivianightapp.data.mappers.toLeaderboardItem
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem
import tomljanovic.matko.trivianightapp.domain.repository.LeaderboardRepository
import tomljanovic.matko.trivianightapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardRepositoryImpl @Inject constructor(
    db: TriviaDatabase
) : LeaderboardRepository {

    private val dao = db.leaderboardDao

    override fun getLeaderboard(): Flow<Resource<List<LeaderboardItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localLeaderboard = dao.getLocalLeaderboard()

            // Use this error if needed
//           if(localLeaderboard.isEmpty()) {
//               emit(Resource.Error(message = "Leaderboard is empty"))
//           }

            emit(Resource.Success(data = localLeaderboard.map { it.toLeaderboardItem() }))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun addToLeaderboard(leaderboardItem: LeaderboardItem) {
        dao.insertLeaderboardItem(leaderboardEntity = leaderboardItem.toLeaderboardEntity())
    }

    override suspend fun clearLeaderboard() {
        dao.clearLeaderboard()
    }
}
