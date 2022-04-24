package co.jdltecnology.producto.presentation.rest.exceptions;

import java.util.List;

public class ProductoDomainException extends Exception{
	/**
	 * Listado de errores
	 */
	public final List<ProductoError> errors;
	
	public ProductoDomainException(List<ProductoError> errors) {
		this.errors = errors;
	}
}
