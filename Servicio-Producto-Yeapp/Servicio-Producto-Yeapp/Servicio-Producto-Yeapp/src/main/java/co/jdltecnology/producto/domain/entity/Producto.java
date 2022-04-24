package co.jdltecnology.producto.domain.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nomproducto;
	
	@Column
	private String rutaimagen;
	
	@Column
	private String tipo;
	
	@Column
	private String estado;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomProducto() {
		return nomproducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomproducto = nomProducto;
	}

	public String getRutaimagen() {
		return rutaimagen;
	}

	public void setRutaimagen(String rutaimagen) {
		this.rutaimagen = rutaimagen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
		
}
