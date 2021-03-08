package br.com.example.demo.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.example.demo.dto.reponse.AlunoResponseDTO;
import br.com.example.demo.dto.request.AlunoRequestDTO;
import br.com.example.demo.model.Aluno;
import br.com.example.demo.util.GeradorMatriculaUtil;

public class AlunoResponseDTOMapper {
	
	private AlunoResponseDTOMapper() {}

	public static List<AlunoResponseDTO> from(List<Aluno> listaAlunos) {
		List<AlunoResponseDTO> listaAlunosMapeada = new ArrayList<AlunoResponseDTO>(); 
		listaAlunos.forEach(aluno -> {
			AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO();
			alunoResponseDTO.setId(aluno.getId());
			alunoResponseDTO.setNome(aluno.getNome());
			alunoResponseDTO.setCurso(aluno.getCurso());
			listaAlunosMapeada.add(alunoResponseDTO);
		});
		return listaAlunosMapeada;
	}

	public static Aluno to(AlunoRequestDTO alunoRequestDTO) {
		Aluno aluno = new Aluno();
		aluno.setNome(alunoRequestDTO.getNome());
		aluno.setCurso(alunoRequestDTO.getCurso());
		aluno.setMatricula(GeradorMatriculaUtil.gerarMatricula());
		return aluno;
	}
	

}
