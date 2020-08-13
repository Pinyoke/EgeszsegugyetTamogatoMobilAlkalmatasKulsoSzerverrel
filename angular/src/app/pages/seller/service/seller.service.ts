import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Seller } from '../model/seller.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) { }


  getSellers(page:number, name:string) : Observable<any>
  {
    this.setHeader();
    return this.httpClient.get<any>(this.baseUrl + "sellers?page="+ page+"&name="+name , { headers : this.headers });
    }

  saveSeller(seller : Seller): Observable<Seller>
  {
    this.setHeader();
    console.log("Seller Service");  
    return this.httpClient.post<Seller>(this.baseUrl + "sellers/create", seller, { headers : this.headers });
    
  }

  deleteSeller(sellerId : number): Observable<any>
  {
    this.setHeader();
    console.log(sellerId);
    return this.httpClient.post<Seller>(this.baseUrl + "sellers/delete", sellerId , { headers : this.headers });
  }

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }
}
