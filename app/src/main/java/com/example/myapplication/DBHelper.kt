package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "RestaurantDB.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INTEGER PRIMARY KEY," +
                    "username TEXT UNIQUE," +
                    "password TEXT," +
                    "email TEXT," +
                    "phone TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS Users"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}


// Método para añadir un nuevo usuario
fun addUser(username: String, password: String, email: String, phone: String) {
    val db = this.writableDatabase

    val values = ContentValues().apply {
        put("username", username)
        put("password", password) // Aquí deberías usar una función de hashing para la contraseña
        put("email", email)
        put("phone", phone)
    }

    db.insert("Users", null, values)
}

// Método para validar el inicio de sesión del usuario
fun validateUser(username: String, password: String): Boolean {
    val db = this.readableDatabase

    val projection = arrayOf("id")
    val selection = "username = ? AND password = ?"
    val selectionArgs = arrayOf(username, password) // Aquí deberías comparar la contraseña hasheada

    val cursor = db.query(
        "Users",
        projection,
        selection,
        selectionArgs,
        null,
        null,
        null
    )

    val userExists = cursor.count > 0
    cursor.close()
    return userExists
}
