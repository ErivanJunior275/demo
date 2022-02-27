package br.com.example.demo.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.example.demo.dto.mapper.AlunoDetalheResponseDTOMapper;
import br.com.example.demo.dto.mapper.AlunoResponseDTOMapper;
import br.com.example.demo.dto.request.AlunoRequestDTO;
import br.com.example.demo.dto.response.AlunoDetalheResponseDTO;
import br.com.example.demo.dto.response.AlunoResponseDTO;
import br.com.example.demo.dto.response.ErrorResponseDTO;
import br.com.example.demo.dto.response.ResponseDTO;
import br.com.example.demo.model.Aluno;
import br.com.example.demo.repository.AlunoRepository;

@Component
public class DemoHelper {

	@Autowired
	private AlunoRepository alunoRepository;

	private DemoHelper() {
	}

	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> mapearRetorno(String nome,
			ResponseDTO<AlunoResponseDTO> responseDTO) {
		List<AlunoResponseDTO> listaAlunos;

		if (nome == null || nome.isEmpty()) {
			listaAlunos = AlunoResponseDTOMapper.from(alunoRepository.findAll());
		} else {
			listaAlunos = AlunoResponseDTOMapper.from(alunoRepository.findByNome(nome));
		}

		if (listaAlunos == null || listaAlunos.isEmpty()) {
			responseDTO.setStatus(HttpStatus.NO_CONTENT.value());

			return ResponseEntity.noContent().build();
		} else {
			responseDTO.setData(listaAlunos);
			responseDTO.setStatus(HttpStatus.OK.value());

			return ResponseEntity.ok(responseDTO);
		}

	}

	public AlunoResponseDTO salvarAluno(AlunoRequestDTO alunoRequestDTO) {
		Aluno aluno = AlunoResponseDTOMapper.to(alunoRequestDTO);
		alunoRepository.save(aluno);

		return retornaAlunoResponseDTO(aluno);
	}

	public ResponseEntity<ResponseDTO<AlunoDetalheResponseDTO>> mapearRetornoDetalhe(Integer id,
			ResponseDTO<AlunoDetalheResponseDTO> responseDTO) {
		Optional<Aluno> optionalAluno = alunoRepository.findById(id);
		if (optionalAluno.isPresent()) {
			responseDTO.setData(Arrays.asList(AlunoDetalheResponseDTOMapper.from(optionalAluno.get())));

			responseDTO.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(responseDTO);
		} else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			responseDTO.setStatus(status.value());

			return ResponseEntity.status(status).body(responseDTO);
		}
	}

	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> atualizarAluno(Integer id, AlunoRequestDTO alunoRequestDTO,
			ResponseDTO<AlunoResponseDTO> responseDTO) {
		Optional<Aluno> optionalAluno = alunoRepository.findById(id);
		if (optionalAluno.isPresent()) {
			Aluno aluno = optionalAluno.get();
			aluno.setNome(alunoRequestDTO.getNome());
			aluno.setCurso(alunoRequestDTO.getCurso());
			responseDTO.setData(Arrays.asList(retornaAlunoResponseDTO(aluno)));
			responseDTO.setStatus(HttpStatus.OK.value());

			return ResponseEntity.ok(responseDTO);
		} else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			List<ErrorResponseDTO> listaErro = new ArrayList<ErrorResponseDTO>();
			ErrorResponseDTO errorMsg = new ErrorResponseDTO("id", "Não existe dados para o id informado");
			listaErro.add(errorMsg);
			responseDTO.setStatus(status.value());
			responseDTO.setMensagens(listaErro);

			return ResponseEntity.status(status).body(responseDTO);
		}
	}

	public void deletarAluno(Integer id) {
		alunoRepository.deleteById(id);
	}

	private AlunoResponseDTO retornaAlunoResponseDTO(Aluno aluno) {
		AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO();
		alunoResponseDTO.setId(aluno.getId());
		alunoResponseDTO.setNome(aluno.getNome());
		alunoResponseDTO.setCurso(aluno.getCurso());

		return alunoResponseDTO;
	}

}
