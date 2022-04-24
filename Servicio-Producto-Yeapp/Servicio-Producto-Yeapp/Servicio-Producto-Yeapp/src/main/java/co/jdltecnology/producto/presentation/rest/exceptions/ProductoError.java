package co.jdltecnology.producto.presentation.rest.exceptions;

import co.jdltecnology.producto.domain.service.EnumErrorCodes;

public class ProductoError {
	public final EnumErrorCodes code;

	public final String field;

	public final String description;
	
	public ProductoError(EnumErrorCodes code, String field, String description) {
		this.code = code;
		this.field = field;
		this.description = description;
	}
}
