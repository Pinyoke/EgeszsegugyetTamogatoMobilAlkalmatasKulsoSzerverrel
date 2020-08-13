import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from  'rxjs/operators';
import { UploadFileResponse } from '../model/upload-file-response.model';

@Injectable({
  providedIn: 'root'
})
export class FileUploadServiceService {


  private baseUrl = 'http://localhost:8080/';
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) { }


  postFile(fileToUpload: File): Observable<UploadFileResponse>{
    this.setHeader();
    const formData: FormData = new FormData();    
    formData.append("file", fileToUpload); 
   
    return this.httpClient.post<UploadFileResponse>(this.baseUrl+"uploadFile", formData, { headers : this.headers });
    
}


setHeader(){

  this.headers = new HttpHeaders({
   // 'Content-Type': 'application/json',
   //'Accept': 'application/json',    
    'Authorization': localStorage.getItem('token')
  })
}

}
