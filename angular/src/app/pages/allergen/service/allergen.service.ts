import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Allergen } from '../model/allergen.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AllergenService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) { }


  getAllergens(page:number, name:string) : Observable<any>
  {
    this.setHeader();
    return this.httpClient.get<any>(this.baseUrl + "allergens?page="+ page+"&name="+name, { headers : this.headers });
    }

  saveAllergen(allergen : Allergen): Observable<Allergen>
  {
    this.setHeader();
    console.log("Allergen Service");  
    return this.httpClient.post<Allergen>(this.baseUrl + "allergens/create", allergen, { headers : this.headers });
    
  }

  deleteAllergen(allergenId : number): Observable<any>
  {
    this.setHeader();
    console.log(allergenId);
    return this.httpClient.post<Allergen>(this.baseUrl + "allergens/delete", allergenId , { headers : this.headers });
  }

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }

  
}
