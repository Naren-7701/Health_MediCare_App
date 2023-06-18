package com.example.health_medicare_application

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"

        private const val TABLE_NAME = "user_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_MOBILE = "mobile"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_BMI = "bmi"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_BLOODGRP = "bloodgrp"
        private const val COLUMN_BLOODPRES = "bloodpres"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_MOBILE TEXT, " +
                "$COLUMN_PASSWORD TEXT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_BMI INT, " +
                "$COLUMN_AGE TEXT, " +
                "$COLUMN_GENDER TEXT, " +
                "$COLUMN_BLOODGRP TEXT, " +
                "$COLUMN_BLOODPRES TEXT " +
                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(user: UserDetailDb) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_EMAIL, user.email)
        values.put(COLUMN_MOBILE, user.mobile)
        values.put(COLUMN_PASSWORD, user.password)
        values.put(COLUMN_NAME, user.name)
        values.put(COLUMN_BMI, user.bmi)
        values.put(COLUMN_AGE, user.age)
        values.put(COLUMN_GENDER, user.gender)
        values.put(COLUMN_BLOODGRP, user.bloodgrp)
        values.put(COLUMN_BLOODPRES, user.bloodpres)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getUserByUseremail(email: String): UserDetailDb? {
        val db = readableDatabase
        val cursor: Cursor =
            db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        var user: UserDetailDb? = null
        if (cursor.moveToFirst()) {
            user = UserDetailDb(
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                mobile = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)),
                password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)),
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                bmi = cursor.getInt(cursor.getColumnIndex(COLUMN_BMI)),
                age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                bloodgrp = cursor.getString(cursor.getColumnIndex(COLUMN_BLOODGRP)),
                bloodpres = cursor.getString(cursor.getColumnIndex(COLUMN_BLOODPRES)),
            )
        }
        cursor.close()
        db.close()
        return user
    }

    @SuppressLint("Range")
    fun updatePassword(email: String?, password: String?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PASSWORD, password.toString())
        db.update(TABLE_NAME, values, "email=?", arrayOf(email.toString()))
        db.close()
    }
}
