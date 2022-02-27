package br.com.example.demo.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO<T> {
	
	private List<T> data = new ArrayList<T>();
	private List<ErrorResponseDTO> mensagens = new ArrayList<ErrorResponseDTO>();
	private int status;
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public List<ErrorResponseDTO> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(List<ErrorResponseDTO> mensagens) {
		this.mensagens = mensagens;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

}
