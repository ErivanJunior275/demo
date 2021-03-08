package br.com.example.demo.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AlunoRequestDTO {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String curso;

	public String getNome() {
		return nome;
	}

	public String getCurso() {
		return curso;
	}
	
}
