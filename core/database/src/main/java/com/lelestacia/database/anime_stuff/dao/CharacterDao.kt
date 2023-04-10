package com.lelestacia.database.anime_stuff.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lelestacia.database.anime_stuff.entity.character.CharacterProfileAndVoiceActors
import com.lelestacia.database.anime_stuff.entity.character.CharacterEntity
import com.lelestacia.database.anime_stuff.entity.character.CharacterInformationEntity
import com.lelestacia.database.anime_stuff.entity.character.CharacterProfile

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table WHERE character_id IN (:characterID)")
    fun getCharactersByCharacterID(characterID: List<Int>): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAdditionalInformation(characterInformationEntity: CharacterInformationEntity)

    @Query("SELECT * FROM character_information_table WHERE character_id = :characterID")
    fun getCharacterAdditionalInformationById(characterID: Int): CharacterInformationEntity?

    @Transaction
    @Query("SELECT * FROM character_table WHERE character_id =:characterID")
    fun getCharacterFullProfile(characterID: Int): CharacterProfile

    @Transaction
    @Query("SELECT * FROM character_table WHERE character_id =:characterID")
    fun getCharacterProfileAndVoiceActors(characterID: Int): CharacterProfileAndVoiceActors

    @Update
    suspend fun updateCharacters(characters: List<CharacterEntity>)
}
