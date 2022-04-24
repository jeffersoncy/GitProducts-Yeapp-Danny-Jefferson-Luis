package co.jdltecnology.producto.access.dao;

import org.springframework.data.repository.CrudRepository;

import co.jdltecnology.producto.domain.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
