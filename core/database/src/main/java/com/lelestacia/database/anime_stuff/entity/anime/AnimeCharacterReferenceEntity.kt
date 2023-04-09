package com.lelestacia.database.anime_stuff.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.ANIME_ID
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.CHARACTER_ID

@Entity(
    tableName = "anime_characters_reference_table",
    primaryKeys = [ANIME_ID, CHARACTER_ID],
)
data class AnimeCharacterReferenceEntity(
    @ColumnInfo(name = ANIME_ID)
    val animeID: Int,

    @ColumnInfo(name = CHARACTER_ID)
    val characterID: Int
)
