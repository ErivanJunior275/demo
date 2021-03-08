package br.com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.demo.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	List<Aluno> findByNome(String nome);

}
