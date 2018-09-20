import { Routes } from '@angular/router';
import { AddProductComponent } from './components/products/add-product/add-product.component';
import { EditProductComponent } from './components/products/edit-product/edit-product.component';
import { ListProductComponent } from './components/products/list-product/list-product.component';

export const ROUTES: Routes = [
    { path: 'add-product', component: AddProductComponent },
    { path: 'edit-product', component: EditProductComponent },
    { path: 'list-product', component: ListProductComponent },
    { path: '', pathMatch: 'full', redirectTo: 'list-product' },
    { path: '**', pathMatch: 'full', redirectTo: 'list-product' }
];