package com.lelestacia.database.anime_stuff.entity.character

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.CHARACTER_ID

data class CharacterProfile(
    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = CHARACTER_ID,
        entityColumn = CHARACTER_ID
    )
    val additionalInformation: CharacterInformationEntity
)
