package com.lelestacia.database.animeStuff.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.database.animeStuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.database.animeStuff.dao.AnimeDao
import com.lelestacia.database.animeStuff.dao.CharacterDao
import com.lelestacia.database.animeStuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.database.animeStuff.dao.EpisodeDao
import com.lelestacia.database.animeStuff.dao.VoiceActorDao
import com.lelestacia.database.animeStuff.entity.anime.AnimeCharacterReferenceEntity
import com.lelestacia.database.animeStuff.entity.anime.AnimeEntity
import com.lelestacia.database.animeStuff.entity.character.CharacterEntity
import com.lelestacia.database.animeStuff.entity.character.CharacterInformationEntity
import com.lelestacia.database.animeStuff.entity.character.CharacterVoiceActorCrossRefEntity
import com.lelestacia.database.animeStuff.entity.episode.EpisodeEntity
import com.lelestacia.database.animeStuff.entity.voiceActor.VoiceActorEntity
import com.lelestacia.database.animeStuff.typeConverter.DateConverter
import com.lelestacia.database.animeStuff.typeConverter.StringConverter

@Database(
    entities = [
        AnimeEntity::class,
        AnimeCharacterReferenceEntity::class,
        CharacterEntity::class,
        CharacterVoiceActorCrossRefEntity::class,
        CharacterInformationEntity::class,
        EpisodeEntity::class,
        VoiceActorEntity::class
    ],
    version = 1,
    exportSchema = true
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
