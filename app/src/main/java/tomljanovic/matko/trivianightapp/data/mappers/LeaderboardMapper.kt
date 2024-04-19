package tomljanovic.matko.trivianightapp.data.mappers

import tomljanovic.matko.trivianightapp.data.local.LeaderboardEntity
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem

fun LeaderboardEntity.toLeaderboardItem(): LeaderboardItem {
    return LeaderboardItem(
        score = score,
        name = name
    )
}

fun LeaderboardItem.toLeaderboardEntity(): LeaderboardEntity {
    return LeaderboardEntity(
        score = score,
        name = name
    )
}
