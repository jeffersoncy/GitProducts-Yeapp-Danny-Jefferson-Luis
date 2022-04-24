package co.jdltecnology.producto.domain.service;

import java.util.List;

import co.jdltecnology.producto.domain.entity.Producto;
import co.jdltecnology.producto.presentation.rest.exceptions.ProductoDomainException;
import co.jdltecnology.producto.presentation.rest.exceptions.ResourceNotFoundException;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Producto findByID(Long id);
	
	public Producto create(Producto producto) throws ProductoDomainException;
	
	public Producto update(Long id,Producto producto) throws ResourceNotFoundException,ProductoDomainException;
	
	public void deleteByID(Long id) throws ResourceNotFoundException;
	
}
