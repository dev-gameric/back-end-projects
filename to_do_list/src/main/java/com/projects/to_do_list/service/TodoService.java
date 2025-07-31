package com.projects.to_do_list.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projects.to_do_list.entiny.Todo;
import com.projects.to_do_list.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo); 
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());

        todoRepository.findAll(sort);
        return todoRepository.findAll(sort);
    }

public List<Todo> update(Todo todo) {
    Todo existiTodo = todoRepository.findById(todo.getId())
        .orElseThrow(() -> new RuntimeException("Todo não encontrado com id: " + todo.getId()));

    existiTodo.setNome(todo.getNome());
    existiTodo.setDescricao(todo.getDescricao());
    existiTodo.setPrioridade(todo.getPrioridade());
    existiTodo.setConcluido(todo.getConcluido());

    todoRepository.save(existiTodo);
    return list();
}

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }
}
