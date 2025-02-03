import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CultivoService {
  private apiUrl = 'http://localhost:8080/api/cultivos';

  constructor(private http: HttpClient) {}

  getCultivos(): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.get(`${this.apiUrl}/usuario/15`, { headers });
  }
}
