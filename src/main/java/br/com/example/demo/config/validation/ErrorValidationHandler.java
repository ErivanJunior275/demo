package br.com.example.demo.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.example.demo.dto.reponse.ErrorResponseDTO;
import br.com.example.demo.dto.reponse.ResponseDTO;

@RestControllerAdvice
public class ErrorValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseDTO<?> handle(MethodArgumentNotValidException exception) {
		ResponseDTO<String> responseDTO = new ResponseDTO<>();
		List<ErrorResponseDTO> errorList = new ArrayList<ErrorResponseDTO>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(error.getField(), mensagem);
			errorList.add(errorResponseDTO);
		});
		responseDTO.setMensagens(errorList);
		responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
		return responseDTO;
	}
	
}
