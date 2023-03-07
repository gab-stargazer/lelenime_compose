package com.lelestacia.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.database.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.dao.AnimeDao
import com.lelestacia.database.dao.CharacterDao
import com.lelestacia.database.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.dao.EpisodeDao
import com.lelestacia.database.dao.VoiceActorDao
import com.lelestacia.database.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.database.entity.anime.AnimeEntity
import com.lelestacia.database.entity.character.CharacterEntity
import com.lelestacia.database.entity.character.CharacterInformationEntity
import com.lelestacia.database.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.database.entity.episode.EpisodeEntity
import com.lelestacia.database.entity.voice_actor.VoiceActorEntity
import com.lelestacia.database.type_converter.DateConverter
import com.lelestacia.database.type_converter.StringConverter

@Database(
    entities = [
        AnimeEntity::class,
        AnimeCharacterReferenceEntity::class,
        CharacterEntity::class,
        CharacterVoiceActorCrossRefEntity::class,
        CharacterInformationEntity::class,
        EpisodeEntity::class,
        VoiceActorEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [StringConverter::class, DateConverter::class])
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun animeCharacterCrossRefDao(): AnimeCharacterCrossRefDao
    abstract fun characterDao(): CharacterDao
    abstract fun characterVoiceActorCrossRefDao(): CharacterVoiceActorCrossRefDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun voiceActorDao(): VoiceActorDao
}
