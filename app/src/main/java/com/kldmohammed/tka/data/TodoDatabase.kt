package com.kldmohammed.tka.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kldmohammed.tka.data.model.Todos
import org.jetbrains.anko.db.*

class TodoDatabase(context: Context) : ManagedSQLiteOpenHelper(context, "todo.db") {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Todos.TABLE_NAME, true,
            Todos.COLUMN_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Todos.COLUMN_TITLE to TEXT,
            Todos.COLUMN_DESC to TEXT,
            Todos.COLUMN_DATE to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(Todos.TABLE_NAME, true)
    }


    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun instance(context: Context): TodoDatabase {
            if (instance == null) {
                instance = TodoDatabase(context.applicationContext)
            }
            return instance!!
        }
    }
}


// Access property for Context
val Context.database: TodoDatabase
    get() = TodoDatabase.instance(this)
