package com.hamscdev.roomexample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "model_table")
data class ModelRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val nameProduct: String,
    val costoProduct: Double,
    val descriptionProduct: String
)
