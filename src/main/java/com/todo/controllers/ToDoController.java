package com.todo.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo.model.ToDo;
import com.todo.service.ToDoService;

@Controller
public class ToDoController {
	@Autowired
	private ToDoService service;
	@GetMapping("/")
	public String viewAllToDoItems(Model model, @ModelAttribute String message) {
		List<ToDo> items = service.getAllToDoItems();
		model.addAttribute("list",items);
		model.addAttribute("msg",message);
		return "index";
	}
	
	@GetMapping("/markCompleted/{id}")
	public String markCompleted(Model model,@PathVariable Integer id, RedirectAttributes redirect) {
		if(service.completeStatus(id)) {
			redirect.addFlashAttribute("message","UPDATE SUCCESS");
			return "redirect:/";
		}else {
		redirect.addFlashAttribute("message","UPDATE FAILURE");
		
		return "redirect:/";}
	}
	
	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo",new ToDo());
		return "addToDo";
	}
	
	
	@PostMapping("/saveToDo")
	public String saveToDoItem(ToDo todo,RedirectAttributes redirect) {
		if(service.isSave(todo)) {
		redirect.addFlashAttribute("message", "SAVE SUCCESS");
		return "redirect:/";
		}
		redirect.addFlashAttribute("message", "SAVE FAILURE");
		return "redirect:/addToDo";
	}
	
	@GetMapping("/editToDo/{id}")
	public String editToDoItem(@PathVariable Integer id, Model model) {
		model.addAttribute("todo", service.getToDoItemById(id));
		return "editToDo";
	}
	
	@PostMapping("/editSaveTodo")
	public String editSaveToDo(ToDo todo,RedirectAttributes redirect) {
		if(!service.isSave(todo)) {
			redirect.addFlashAttribute("message", "UPDATE SUCCESS");
			return "redirect:/";
			}
			redirect.addFlashAttribute("message", "SAVE FAILURE");
			return "redirect:/editToDo/"+todo.getId();
	}
	
	@GetMapping("/deleteToDo/{id}")
	public String deleteToDoItem(@PathVariable Integer id,RedirectAttributes redirect) {
		if(service.deleteToDoItem(id)) {
			redirect.addFlashAttribute("message","DELETE SUCCESS");
		}
		redirect.addFlashAttribute("message","DELETE FAILURE");
		return "redirect:/";
	}
}
