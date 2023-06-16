package com.example.health_medicare_application

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserDetailDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDetailDb)

    @Update
    suspend fun updateUser(user: UserDetailDb)

    @Delete
    suspend fun deleteUser(user: UserDetailDb)
}
