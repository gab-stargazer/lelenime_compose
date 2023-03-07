package com.lelestacia.database.entity.character

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.database.util.DatabaseConstant.CHARACTER_ID

data class CharacterProfile(
    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = CHARACTER_ID,
        entityColumn = CHARACTER_ID
    )
    val additionalInformation: CharacterInformationEntity
)
