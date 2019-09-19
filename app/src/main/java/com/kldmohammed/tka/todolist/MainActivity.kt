package com.kldmohammed.tka.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kldmohammed.tka.R
import com.kldmohammed.tka.addtodo.AddTodoActivity
import com.kldmohammed.tka.data.database
import com.kldmohammed.tka.data.model.TodoDto
import com.kldmohammed.tka.data.model.Todos
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    lateinit var todoAdapter: TodoAdapter
    lateinit var todos: List<TodoDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        todos = ArrayList()
        todoAdapter = TodoAdapter(todos)

        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)

        //  todoRecyclerView.apply {
        todoRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        todoRecyclerView.adapter = todoAdapter
        todoRecyclerView.hasFixedSize()
        todoRecyclerView.layoutAnimation = animation
        //   }

        // initTodoRecycler()

        loadTodos()

        addTodoFab.setOnClickListener {
            startActivity(Intent(applicationContext, AddTodoActivity::class.java))
        }

    }

    private fun loadTodos() {

        launch(coroutineContext) {
            this@MainActivity.database.use {
                todos = select(Todos.TABLE_NAME).parseList(classParser())
                Log.d("todo", todos.toString())
            }
        }
        todoAdapter.notifyDataSetChanged()
    }

    private fun initTodoRecycler() {
        todos = ArrayList()
        todoAdapter = TodoAdapter(todos)

        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)

        with(todoRecyclerView) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = todoAdapter
            hasFixedSize()
            layoutAnimation = animation
        }
    }


    override fun onResume() {
        super.onResume()
        loadTodos()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
