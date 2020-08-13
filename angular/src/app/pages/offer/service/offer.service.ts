import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'app/pages/product/model/product.model';
import { Offer } from '../model/offer.model';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) {}

  getOffers() : Observable<any>
  {
    this.setHeader();
    return this.httpClient.get<any>(this.baseUrl + "offers", { headers : this.headers });
  }

  saveOffer(offer : Offer): Observable<Offer>
  {
    this.setHeader();
    return this.httpClient.post<Offer>(this.baseUrl + "offers/create", offer, { headers : this.headers });
    
  }

  deleteOffer(offerId : number): Observable<any>
  {
    this.setHeader();
    console.log(offerId);
    return this.httpClient.post<Product>(this.baseUrl + "offers/delete", offerId, { headers : this.headers });
  }

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }
}
