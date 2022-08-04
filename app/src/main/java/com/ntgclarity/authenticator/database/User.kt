package com.ntgclarity.authenticator.database

import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?
)

@Dao
interface UserDao {
    @Query("select * From user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE email is :mail ")
    fun findUser(mail:String): List<User>

    @Query("SELECT * FROM user WHERE password is :pass AND email is :mail")
    fun checkPass(mail:String,pass:String): List<User>

    @Insert
    fun insertUser(user: User)


}

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}