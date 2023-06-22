package com.example.health_medicare_application.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_table")
data class MedicalDb(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "bmi") val bmi:Int?,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "bloodgrp") val bloodgrp: String?,
    @ColumnInfo(name = "bloodpres") val bloodpres: String?,
    @ColumnInfo(name = "category") val category: String?,
)
