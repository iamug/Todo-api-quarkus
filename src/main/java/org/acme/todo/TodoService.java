package org.acme.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    private AtomicLong sequence = new AtomicLong(1);

    public List<Todo> listAll() {
        return todos;
    }

    public Todo create(Todo todo) {
        todo.id = sequence.getAndIncrement();
        todos.add(todo);
        return todo;
    }

    public Todo findById(Long id) {
        return todos.stream()
                .filter(t -> t.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    public Todo update(Long id, Todo newData) {
        Todo existing = findById(id);
        if (existing != null) {
            existing.title = newData.title;
            existing.completed = newData.completed;
        }
        return existing;
    }

    public boolean delete(Long id) {
        return todos.removeIf(t -> t.id.equals(id));
    }

}
