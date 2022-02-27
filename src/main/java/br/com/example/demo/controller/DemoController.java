package br.com.example.demo.controller;

import java.net.URI;
import java.util.Arrays;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.example.demo.constants.DemoConstants;
import br.com.example.demo.dto.request.AlunoRequestDTO;
import br.com.example.demo.dto.response.AlunoDetalheResponseDTO;
import br.com.example.demo.dto.response.AlunoResponseDTO;
import br.com.example.demo.dto.response.ResponseDTO;
import br.com.example.demo.helper.DemoHelper;

@RestController
@RequestMapping("/aluno")
public class DemoController {
	
	@Autowired
	private DemoHelper demoHelper;
	
	@GetMapping(path="/healthCheck")
	public ResponseEntity<String> healthCheck() {
		String ok = "ok";
		
		return ResponseEntity.ok(ok);
	}
	
	@GetMapping(produces=DemoConstants.APPLICATION_JSON)
	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> listaAlunos(@RequestParam(name="nome", required=false) String nome) {
		ResponseDTO<AlunoResponseDTO> responseDTO = new ResponseDTO<AlunoResponseDTO>();
		
		return demoHelper.mapearRetorno(nome, responseDTO);
	}
	
	@GetMapping(path="/{id}", produces=DemoConstants.APPLICATION_JSON)
	public ResponseEntity<ResponseDTO<AlunoDetalheResponseDTO>> detalhaAluno(@PathVariable(required=true) Integer id) {
		ResponseDTO<AlunoDetalheResponseDTO> responseDTO = new ResponseDTO<AlunoDetalheResponseDTO>();
		
		return demoHelper.mapearRetornoDetalhe(id, responseDTO);
	}
	
	@PostMapping(produces=DemoConstants.APPLICATION_JSON, consumes=DemoConstants.APPLICATION_JSON)
	@Transactional
	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> salvaAluno(@RequestBody(required = true) @Valid AlunoRequestDTO alunoRequestDTO, UriComponentsBuilder uriBuilder) {
		AlunoResponseDTO retorno = demoHelper.salvarAluno(alunoRequestDTO);
		ResponseDTO<AlunoResponseDTO> responseDTO = new ResponseDTO<AlunoResponseDTO>();
		responseDTO.setData(Arrays.asList(retorno));
		responseDTO.setStatus(HttpStatus.CREATED.value());
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(retorno.getId()).toUri();
		
		return ResponseEntity.created(uri).body(responseDTO);
	}
	
	@PutMapping(path="/{id}", produces=DemoConstants.APPLICATION_JSON, consumes=DemoConstants.APPLICATION_JSON)
	@Transactional
	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> alteraAluno(@PathVariable(required=true) Integer id, 
			@RequestBody(required = true) @Valid AlunoRequestDTO alunoRequestDTO) {
		ResponseDTO<AlunoResponseDTO> responseDTO = new ResponseDTO<AlunoResponseDTO>();

		return demoHelper.atualizarAluno(id, alunoRequestDTO, responseDTO);
	}
	
	@DeleteMapping(path="/{id}", produces=DemoConstants.APPLICATION_JSON, consumes=DemoConstants.APPLICATION_JSON)
	@Transactional
	public ResponseEntity<ResponseDTO<AlunoResponseDTO>> deletaAluno(@PathVariable(required=true) Integer id) {
		demoHelper.deletarAluno(id);
		ResponseDTO<AlunoResponseDTO> responseDTO = new ResponseDTO<AlunoResponseDTO>();
		responseDTO.setStatus(HttpStatus.OK.value());
		
		return ResponseEntity.ok(responseDTO);
	}
	
}
