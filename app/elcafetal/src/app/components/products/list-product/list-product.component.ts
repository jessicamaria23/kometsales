import { Component, OnInit } from '@angular/core';
import { Product } from '../../../model/product';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProductService } from '../../../services/product.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  products: Product[];
  listForm: FormGroup;

  constructor(private router: Router, private service: ProductService) {}

  ngOnInit() {
    this.service.getProducts().subscribe(data => (this.products = data));
  }

  deleteProduct(product: Product): void {
    swal({
      title: 'Está seguro?',
      text: `¿Seguro desea eliminar el producto ${product.productName}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.value) {
        this.service.deleteProduct(product.id).subscribe(data => {
          this.products = this.products.filter(c => c !== product);
        });

        swal('Eliminado!', 'Se ha eliminado el producto correctamente.', 'success');
      }
    });
  }

  editProduct(product: Product): void {
    localStorage.removeItem('editProductId');
    localStorage.setItem('editProductId', product.id.toString());
    this.router.navigate(['edit-product']);
  }

  addProduct(): void { 
    this.router.navigate(['add-product']);
  }
}