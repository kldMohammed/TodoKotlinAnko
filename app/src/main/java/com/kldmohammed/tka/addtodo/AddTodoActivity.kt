package com.kldmohammed.tka.addtodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kldmohammed.tka.R
import com.kldmohammed.tka.data.database
import com.kldmohammed.tka.data.model.Todos
import com.kldmohammed.tka.ext.asString
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import java.util.*
import kotlin.coroutines.CoroutineContext

class AddTodoActivity : AppCompatActivity(), CoroutineScope {


    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        saveTodoBtn.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        val todoTitle = todoTitleEt.asString()
        val todoDescription = todoDescEt.asString()

        if (todoTitle.isEmpty()) {
            toast(R.string.title_is_empty)
            return
        }

        if (todoDescription.isEmpty()) {
            toast(R.string.title_is_empty)
            return
        }


        database.use {
            insert(
                Todos.TABLE_NAME,
                Todos.COLUMN_TITLE to todoTitle,
                Todos.COLUMN_DESC to todoDescription,
                Todos.COLUMN_DATE to Date().time
            )
        }

        finish()
    }
}
