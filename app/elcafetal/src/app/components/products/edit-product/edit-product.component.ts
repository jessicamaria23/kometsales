import { Component, OnInit } from '@angular/core';
import { Product } from '../../../model/product';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProductService } from '../../../services/product.service';
import { Router } from '@angular/router';
import {first} from 'rxjs/operators';
import swal from 'sweetalert2';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  product: Product;
  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private service: ProductService) { }

  ngOnInit() {

    const productId = localStorage.getItem('editProductId');

    if ( !productId ) {
      alert('Acción invalida');
      this.router.navigate(['list-product']);
      return;
    }

    this.editForm = this.formBuilder.group({
      id: [],
      productName: ['', Validators.required],
      unitValue: ['', Validators.required],
      isPerishable: ['', Validators.required],
      datePurchase: ['', Validators.required]
    });

    this.service.getProduct(+productId)
      .subscribe(data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    this.service.updateProduct(this.editForm.value)
      .pipe(first())
      .subscribe( data => {
        this.router.navigate(['list-Product']);
        swal({
          position: 'top',
          type: 'success',
          title: `Producto modificado exitosamente.`,
          showConfirmButton: false,
          timer: 1500
        });
      },
      error => {
        alert(error);
      });
  }
}