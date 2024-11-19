package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer>{
}
