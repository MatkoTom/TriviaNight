package tomljanovic.matko.trivianightapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuestionEntity::class, LeaderboardEntity::class],
    version = 1
)
abstract class TriviaDatabase : RoomDatabase() {
    abstract val dao: QuestionDao
    abstract val leaderboardDao: LeaderboardDao
}
