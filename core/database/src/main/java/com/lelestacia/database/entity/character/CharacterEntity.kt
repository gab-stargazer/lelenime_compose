package com.lelestacia.database.entity.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lelestacia.database.util.DatabaseConstant.CHARACTER_ID
import com.lelestacia.database.util.DatabaseConstant.CREATED_AT
import com.lelestacia.database.util.DatabaseConstant.IMAGE
import com.lelestacia.database.util.DatabaseConstant.UPDATED_AT
import java.util.Date

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = CHARACTER_ID)
    val characterID: Int,

    @ColumnInfo(name = IMAGE)
    val image: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "favorite")
    val favorite: Int,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Date,

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Date?,
)
