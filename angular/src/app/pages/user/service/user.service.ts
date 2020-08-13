import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtResponse } from '../model/jwt-response.model';
import { Role } from '../model/role.model';






@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  headers: HttpHeaders;
  

  constructor(private httpClient: HttpClient) { }

  userRegistration(user: User): Observable<User>{
  
   console.log(user.name);
   console.log(user.password);
    return this.httpClient.post<User>(this.baseUrl + "user/registration", user);
  } 

  authenticate(username, password){
    return this.httpClient.post<JwtResponse>(this.baseUrl + 'authenticate', {username, password}).pipe(
    map(
    userData =>{
      localStorage.setItem('username',username);
      console.log(username);
      let tokenStr= 'Bearer '+userData.jwttoken;
      localStorage.setItem('token', tokenStr);      
      return userData;
    }
    ));
  }

  setUserRole(): Observable<Role>{
    console.log("SetUserRole");
    this.setHeader();
    
    return this.httpClient.get<Role>(this.baseUrl + "user/getactualuserrole",{ headers : this.headers });
    
  }


  isUserLoggedIn() {
    let user = localStorage.getItem('username');
    //console.log(!(user === null))
    console.log(localStorage.getItem('token'));
    console.log(localStorage.getItem('role'))
    return !(user === null)
  }

  logOut() {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }

  getUserByEmail(email : string){
    this.setHeader()
    return this.httpClient.post<User>(this.baseUrl + "user/email", email, { headers : this.headers });
  }

  getUsers(page:number, email:string) : Observable<any>
  {
    this.setHeader()
    return this.httpClient.get<any>(this.baseUrl + "users?page="+ page+"&email="+email , { headers : this.headers });
  }

  
  deleteUser(userId: number) {
    this.setHeader()
    console.log(userId);
    return this.httpClient.post<User>(this.baseUrl + "users/delete", userId, { headers : this.headers });
  }


  saveUser(user: User): Observable<User>{
    this.setHeader()
    return this.httpClient.post<User>(this.baseUrl + "user/save", user , { headers : this.headers });
  } 

  setHeader(){

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token')
    })
  }

 
}
