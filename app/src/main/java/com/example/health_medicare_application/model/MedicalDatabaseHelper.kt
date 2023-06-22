package com.example.health_medicare_application.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedicalDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"

        const val TABLE_NAME = "medical_table"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_BMI = "bmi"
        const val COLUMN_AGE = "age"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_BLOODGRP = "bloodgrp"
        const val COLUMN_BLOODPRES = "bloodpres"
        const val COLUMN_CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(dbn: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dbn?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(dbn)
    }

    fun insertnewUser(user: MedicalDb) {
        val dbn = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_EMAIL, user.email)
        values.put(COLUMN_LOCATION, user.location)
        values.put(COLUMN_BMI, user.bmi)
        values.put(COLUMN_AGE, user.age)
        values.put(COLUMN_GENDER, user.gender)
        values.put(COLUMN_BLOODGRP, user.bloodgrp)
        values.put(COLUMN_BLOODPRES, user.bloodpres)
        values.put(COLUMN_CATEGORY, user.category)
        dbn.insert(TABLE_NAME, null, values)
        dbn.close()
    }

    @SuppressLint("Range")
    fun medgetUserByUseremail(email: String): MedicalDb? {
        val dbn = readableDatabase
        val cursor: Cursor =
            dbn.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        var user: MedicalDb? = null
        if (cursor.moveToFirst()) {
            user = MedicalDb(
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)),
                bmi = cursor.getInt(cursor.getColumnIndex(COLUMN_BMI)),
                age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                bloodgrp = cursor.getString(cursor.getColumnIndex(COLUMN_BLOODGRP)),
                bloodpres = cursor.getString(cursor.getColumnIndex(COLUMN_BLOODPRES)),
                category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY))
            )
        }
        cursor.close()
        dbn.close()
        return user
    }
}
