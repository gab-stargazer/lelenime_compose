package com.lelestacia.database.anime_stuff.entity.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.CHARACTER_ID
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.CREATED_AT
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.UPDATED_AT
import java.util.Date

@Entity(
    tableName = "character_information_table",
)
data class CharacterInformationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = CHARACTER_ID)
    val characterID: Int,

    @ColumnInfo(name = "kanji_name")
    val characterKanjiName: String,

    @ColumnInfo(name = "nickname")
    val characterNickNames: List<String>,

    @ColumnInfo(name = "favorite")
    val characterFavorite: Int,

    @ColumnInfo(name = "story")
    val characterInformation: String,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Date,

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Date?,
)
