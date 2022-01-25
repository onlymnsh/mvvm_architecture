package com.example.architecture.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class User(
    @PrimaryKey val uid: Int = Random.nextInt(),
    @ColumnInfo(name = "first_name") val firstName: String?="",
    @ColumnInfo(name = "last_name") val lastName: String?=""
)