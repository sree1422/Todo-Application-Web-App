package com.todo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.todo.model.ToDo;
import com.todo.repository.ToDoRepo;
@Service
public class ToDoServiceImpl implements ToDoService {
	@Autowired
	private ToDoRepo repo;

	@Override
	public List<ToDo> getAllToDoItems() {
		
		return repo.findAll();
	}

	@Override
	public ToDo getToDoItemById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean completeStatus(Integer id) {
		ToDo toDoItem = getToDoItemById(id);
		String status="Completed";
		toDoItem.setStatus(status);
		return isSave(toDoItem);
	}

	@Override
	public boolean isSave(ToDo toDo) {
		boolean status=toDo.getId()==null;
		repo.save(toDo);
		return status;
	}

	@Override
	public boolean deleteToDoItem(Integer id) {
		if(repo.findById(id).isEmpty()) {
			return false;
		}
		repo.deleteById(id);
		return true;
	}

	
	
}
