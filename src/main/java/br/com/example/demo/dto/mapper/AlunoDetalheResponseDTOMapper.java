package br.com.example.demo.dto.mapper;

import br.com.example.demo.dto.response.AlunoDetalheResponseDTO;
import br.com.example.demo.model.Aluno;

public class AlunoDetalheResponseDTOMapper {
	
	private AlunoDetalheResponseDTOMapper() {}

	public static AlunoDetalheResponseDTO from(Aluno aluno) {
		AlunoDetalheResponseDTO alunoDetalheResponseDTO = new AlunoDetalheResponseDTO();
		alunoDetalheResponseDTO.setId(aluno.getId());
		alunoDetalheResponseDTO.setMatricula(aluno.getMatricula());
		alunoDetalheResponseDTO.setNome(aluno.getNome());
		alunoDetalheResponseDTO.setCurso(aluno.getCurso());
		alunoDetalheResponseDTO.setEstado(aluno.getEstado());

		return alunoDetalheResponseDTO;
	}

}
