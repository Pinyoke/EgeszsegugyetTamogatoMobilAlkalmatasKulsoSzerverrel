import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../model/product.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/v1/';

  headers: HttpHeaders;
  

  constructor(private httpClient: HttpClient) {}

  getProducts(page:number, name:string): Observable<any> 
  {
    this.setHeader();
    return this.httpClient.get<any>(this.baseUrl + "products?page="+ page+"&name="+name ,{ headers : this.headers });
  }

  saveProduct(product : Product): Observable<Product>  
  {
    
    this.setHeader();   
    console.log("Create Product Service")
    return this.httpClient.post<Product>(this.baseUrl + "products/create", product, { headers : this.headers });
  }

  deleteProduct(productId : number): Observable<any>
  {
    this.setHeader();
    console.log(productId);
    return this.httpClient.post<Product>(this.baseUrl + "products/delete", productId, { headers : this.headers });
  }

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }

}
