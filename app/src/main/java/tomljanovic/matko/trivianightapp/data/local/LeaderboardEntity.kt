package tomljanovic.matko.trivianightapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LeaderboardEntity(
    val score: Int,
    val name: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
