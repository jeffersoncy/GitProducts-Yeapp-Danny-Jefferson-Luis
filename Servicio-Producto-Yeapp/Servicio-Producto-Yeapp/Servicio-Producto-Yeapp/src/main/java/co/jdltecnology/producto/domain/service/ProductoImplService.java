package co.jdltecnology.producto.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jdltecnology.producto.access.dao.IProductoDao;
import co.jdltecnology.producto.domain.entity.Producto;
import co.jdltecnology.producto.presentation.rest.exceptions.ProductoDomainException;
import co.jdltecnology.producto.presentation.rest.exceptions.ProductoError;
import co.jdltecnology.producto.presentation.rest.exceptions.ResourceNotFoundException;


@Service
public class ProductoImplService implements IProductoService{
	@Autowired
	private IProductoDao productoDao;
	
	/**
	 * Obtiene la lista de productos en la tienda
	 * @return Lista de todos los productos
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>)productoDao.findAll();
	}

	/**
	 * Busca un producto por su id
	 * @param Id identificacion del producto
	 * @return retorna un objeto de tipo Producto
	 */
	@Override
	public Producto findByID(Long id){
		Producto prod = productoDao.findById(id).orElse(null);
		return prod;
	}

	/**
	 * Añade un nuevo producto a la base de datos
	 * @param Objeto de tipo producto que contiene la informacion del producto
	 * @return Retorna un Objeto de tipo Producto
	 */	
	@Override
	@Transactional
	public Producto create(Producto producto) throws ProductoDomainException{
		List <ProductoError> errors = validateDomain(producto);
		
		if(!errors.isEmpty()) {
			throw new ProductoDomainException(errors);
		}
		
		return productoDao.save(producto);
	}

	/**
	 * Modifica la informacion de un producto
	 * @param id Identificador del producto a modificar
	 * @param producto Objeto de tipo producto con los datos a cambiar
	 * @return Retorna un objeto de tipo Producto
	 */	
	@Override
	@Transactional
	public Producto update(Long id, Producto producto) throws ProductoDomainException,ResourceNotFoundException{
		Producto prod = this.findByID(id);
		
		if(prod == null) {
			throw new ResourceNotFoundException();
		}
		List<ProductoError> errors = validateDomain(prod);
		if(!errors.isEmpty()) {
			throw new ProductoDomainException(errors);
		}
		prod.setNomProducto(producto.getNomProducto());
		prod.setRutaimagen(producto.getRutaimagen());
		prod.setTipo(producto.getTipo());
		prod.setEstado(producto.getEstado());
		return productoDao.save(prod);
	}

	/**
	 * Elimina un producto por el id
	 * @param Id Identificiacion del producto
	 */
	@Override
	@Transactional
	public void deleteByID(Long id) throws ResourceNotFoundException{
		Producto prod = this.findByID(id);
		if(prod == null) {
			throw new ResourceNotFoundException();
		}
		productoDao.deleteById(id);
	}
	
	
	/**
	 * Aplica validaciones o reglas del dominio para un producto. Antes de ser agregado o modificado.
	 * @param prod producto a validar
	 * @return lista de errores de validación.
	 */
	private List<ProductoError> validateDomain(Producto prod){
		List<ProductoError> errors = new ArrayList<>();
		if(prod.getNomProducto() == null || prod.getNomProducto().isBlank()) {
			errors.add(new ProductoError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del producto es obligatorio"));
		}
		if(prod.getRutaimagen() == null || prod.getRutaimagen().isBlank()) {
			errors.add(new ProductoError(EnumErrorCodes.EMPTY_FIELD, "nameUser", "La ruta de la imagen no puede estar nula"));
		}
		
		if(prod.getTipo() == null || prod.getTipo().isBlank()) {
			errors.add(new ProductoError(EnumErrorCodes.EMPTY_FIELD, "tipo", "El tipo del producto es obligatorio"));
		}
		
		return errors;
	}
}
