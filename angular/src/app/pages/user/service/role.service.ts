import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) { }

  getRoles() : Observable<Role[]>
  {
    this.setHeader();
    return this.httpClient.get<Role[]>(this.baseUrl + "roles" , { headers : this.headers });
  }

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }
}
