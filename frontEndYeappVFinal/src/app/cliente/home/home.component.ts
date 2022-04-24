import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels } from '@techiediaries/ngx-qrcode';
import { Producto } from 'src/app/Modelo/Producto';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public elementType = NgxQrcodeElementTypes.URL;
  public correctionLevel = NgxQrcodeErrorCorrectionLevels.HIGH;
  public value = "1000";


  cantidadProd: number;
  productos!: Producto[];
  productosfiltrado!: Producto[];
  cantidad: number = 1;
  categoria: string;
  constructor(private service: ServiceService, private router: Router) {
    this.productosfiltrado = [ ];
  }

  ngOnInit(): void {
    this.listarProductos();
    this.categoria="general";
  }

  listarProductos() {
    this.service.getProductos()
      .subscribe(data => {
        this.productos = data;
        this.cantidadProd = this.productos.length;
      })
  }


  tipo(productos:Producto[],tipo: string) {
    this.productosfiltrado = [];
    if (tipo != "general") {
      this.categoria = tipo;
      for (let index = 0; index < this.productos.length; index++) {
        if (this.productos[index].tipo == tipo) {
          this.productosfiltrado.push(this.productos[index])
        }
      }
      this.cantidadProd = this.productosfiltrado.length
      productos = this.productosfiltrado;
    } else {
      this.cantidadProd = this.productos.length
      this.categoria = "general"
    }
    
  }

  eliminarProducto(producto:Producto){
    this.service.deleteProducto(producto).subscribe(data =>{
      this.productos = this.productos.filter(p=>p!==producto);
      alert("Producto eliminado")
      window.location.reload()
    })
  }

  actualizarProducto(producto:Producto){
    localStorage.setItem("idProducto",producto.id.toString());
    this.router.navigate(["editar"]);
  }

}
