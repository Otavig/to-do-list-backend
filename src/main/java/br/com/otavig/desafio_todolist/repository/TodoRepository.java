package br.com.otavig.desafio_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otavig.desafio_todolist.entity.Todo; // Certifique-se de importar @Repository

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}