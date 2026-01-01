package com.in28minutes.rest.webservices.restful_web_services.todo;

import com.in28minutes.rest.webservices.restful_web_services.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    private TodoRepository todoRepository;

    public TodoJpaResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> retrieveTodos(@PathVariable String username, @PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodos(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }
}
