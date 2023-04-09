package com.lelestacia.database.anime_stuff.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.database.anime_stuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.anime_stuff.dao.AnimeDao
import com.lelestacia.database.anime_stuff.dao.CharacterDao
import com.lelestacia.database.anime_stuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.anime_stuff.dao.EpisodeDao
import com.lelestacia.database.anime_stuff.dao.VoiceActorDao
import com.lelestacia.database.anime_stuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.database.anime_stuff.entity.anime.AnimeEntity
import com.lelestacia.database.anime_stuff.entity.character.CharacterEntity
import com.lelestacia.database.anime_stuff.entity.character.CharacterInformationEntity
import com.lelestacia.database.anime_stuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.database.anime_stuff.entity.episode.EpisodeEntity
import com.lelestacia.database.anime_stuff.entity.voice_actor.VoiceActorEntity
import com.lelestacia.database.anime_stuff.type_converter.DateConverter
import com.lelestacia.database.anime_stuff.type_converter.StringConverter

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
