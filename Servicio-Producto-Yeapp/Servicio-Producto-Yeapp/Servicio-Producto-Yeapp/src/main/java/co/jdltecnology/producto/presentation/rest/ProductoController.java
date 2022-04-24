package co.jdltecnology.producto.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.jdltecnology.producto.domain.entity.Producto;
import co.jdltecnology.producto.domain.service.IProductoService;
import co.jdltecnology.producto.presentation.rest.exceptions.ProductoDomainException;
import co.jdltecnology.producto.presentation.rest.exceptions.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Producto> findAll(){
		return (List<Producto>) productoService.findAll();
	}
	
	/**
	 * Metodo que busca un producto por el id
	 * @param Id Identificador del producto
	 * @return un objeto de tipo Producto en formato json
	 */
	@RequestMapping(value = "{Id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Producto findById(@PathVariable Long Id) {
		return productoService.findByID(Id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Producto create(@RequestBody Producto prod) throws ProductoDomainException{
		return productoService.create(prod);
	}
	
	
	@RequestMapping(value = "{Id}",method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Producto update(@RequestBody Producto prod, @PathVariable Long Id) 
			throws ProductoDomainException, ResourceNotFoundException {
		return productoService.update(Id, prod);
	}
	
	
	@RequestMapping(value = "{Id}",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long Id) throws ResourceNotFoundException{
		productoService.deleteByID(Id);
	}
	
}
