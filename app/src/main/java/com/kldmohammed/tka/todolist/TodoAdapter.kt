package com.kldmohammed.tka.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kldmohammed.tka.R
import com.kldmohammed.tka.data.model.TodoDto
import kotlinx.android.synthetic.main.todo_list_item.view.*
import org.jetbrains.anko.toast

class TodoAdapter(private val todoList: List<TodoDto>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: TodoDto) {

            with(itemView) {
                context.toast(todo.toString())
                todoTitleTxt.text = todo.title
                todoDescTxt.text = todo.description
                todoDateTxt.text = todo.createdAt.toString()
            }
        }

    }
}