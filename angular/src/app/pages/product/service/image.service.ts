import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Image } from '../model/image.model';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseUrl = 'http://localhost:8080/api/v1/';

  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) { }

  uploadImage(uploadImageData: FormData) : Observable<Image>{
    //this.setHeader();
    return this.httpClient.post<Image>(this.baseUrl, uploadImageData, { headers : this.headers });
  }

  getImage(): Observable<Image>{
    //this.setHeader();
    return this.httpClient.get<Image>('http://localhost:8080/api/v1/yoda',{ headers : this.headers });
  }

  getImage2(): Observable<Image>{
    //this.setHeader();
    return this.httpClient.get<Image>('http://localhost:8080/api/v1/yoda',{ headers : this.headers });
  }


  setHeader(){

    this.headers = new HttpHeaders({
      'Authorization': localStorage.getItem('token')
    })
  }

}
