import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEvent, HttpRequest  } from '@angular/common/http';
import { Product } from '../model/product';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/products';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor( private http: HttpClient ) {
    console.log('Servicio de productos funcionando');
  }

  getProducts(): Observable<Product[]> {
    return this.http.get(this.baseUrl).pipe(
      map(data => data as Product[])
    );
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/${id}`);
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.baseUrl, product, {headers: this.httpHeaders});
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.baseUrl, product, {headers: this.httpHeaders});
  }

  deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.baseUrl}/${id}`, {headers: this.httpHeaders});
  }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', this.baseUrl + '/fileUpload', formdata, {
      reportProgress: true,
      responseType: 'text'
    });
    
    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get('api/getallfiles');
  }
}