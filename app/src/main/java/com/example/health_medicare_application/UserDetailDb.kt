package com.example.health_medicare_application

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetailDb(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "mobile") val mobile: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "bmi") val bmi:Float?,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "bloodgrp") val bloodgrp: String?,
    @ColumnInfo(name = "bloodpres") val bloodpres: String?,
    )
