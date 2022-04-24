package co.jdltecnology.producto.presentation.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	/**
	 * Método para manejar la excepcion ResourceNotFoundException
	 * 
	 * @param e ResourceNotFoundException
	 * @return la excepción en formato Json
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@RequestMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>("{\"errors\":[{\"code\":\"NOT_FOUND\"}]}", HttpStatus.NOT_FOUND);
	}

	/**
	 * Método para manejar la excepcion PlatoDomainException
	 * 
	 * @param e PlatoDomainException
	 * @return la excepción en formato Json
	 */
	@ExceptionHandler(ProductoDomainException.class)
	@RequestMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<ErrorsPayload> handleTodoDomainException(ProductoDomainException e) {
		return new ResponseEntity<>(new ErrorsPayload(e.errors), HttpStatus.UNPROCESSABLE_ENTITY);

	}
}
