package com.lelestacia.database.anime_stuff.entity.character

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.lelestacia.database.anime_stuff.entity.voice_actor.VoiceActorEntity
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.CHARACTER_ID
import com.lelestacia.database.anime_stuff.util.DatabaseConstant.VOICE_ACTOR_ID

data class CharacterProfileAndVoiceActors(
    @Embedded val characterEntity: CharacterEntity,

    @Relation(
        parentColumn = CHARACTER_ID,
        entityColumn = CHARACTER_ID
    )
    val characterInformationEntity: CharacterInformationEntity,

    @Relation(
        entity = VoiceActorEntity::class,
        parentColumn = CHARACTER_ID,
        entityColumn = VOICE_ACTOR_ID,
        associateBy = Junction(
            value = CharacterVoiceActorCrossRefEntity::class,
            parentColumn = CHARACTER_ID,
            entityColumn = VOICE_ACTOR_ID
        )
    )
    val voiceActorEntities: List<VoiceActorEntity>
)