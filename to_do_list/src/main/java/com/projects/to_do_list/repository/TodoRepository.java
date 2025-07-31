package com.projects.to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.to_do_list.entiny.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
