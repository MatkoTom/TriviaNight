package tomljanovic.matko.trivianightapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import tomljanovic.matko.trivianightapp.util.Converters

@Entity
@TypeConverters(Converters::class)
data class QuestionEntity(
    val question: String,
    val correctAnswer: String,
    val answers: List<String>,

    @PrimaryKey
    val id: Int? = null
)
