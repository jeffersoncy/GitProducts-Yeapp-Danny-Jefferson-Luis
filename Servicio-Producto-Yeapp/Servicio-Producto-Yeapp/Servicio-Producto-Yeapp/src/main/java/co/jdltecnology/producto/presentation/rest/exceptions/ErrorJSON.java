package co.jdltecnology.producto.presentation.rest.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.jdltecnology.producto.domain.service.EnumErrorCodes;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorJSON {

	public final EnumErrorCodes code;
	public final String field;
	public final String message;

	public ErrorJSON(EnumErrorCodes code, String field, String message) {
		this.code = code;
		this.field = field;
		this.message = message;
	}
}
