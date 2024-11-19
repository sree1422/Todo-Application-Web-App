package com.todo.service;

import java.util.List;


import com.todo.model.ToDo;

public interface ToDoService {
	public List<ToDo> getAllToDoItems();
	public ToDo getToDoItemById(Integer id);
	public boolean completeStatus(Integer id);
	public boolean isSave(ToDo toDo);
	public boolean deleteToDoItem(Integer id);
}
