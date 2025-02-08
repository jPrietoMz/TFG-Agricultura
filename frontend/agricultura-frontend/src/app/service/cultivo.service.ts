// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { Cultivo } from '../model/cultivo.model';

// @Injectable({
//   providedIn: 'root'
// })
// export class CultivoService {
//   private baseUrl = 'http://localhost:8080/api/cultivos';

//   constructor(private http: HttpClient) {}

//   getCultivoById(id: number): Observable<Cultivo> {
//     return this.http.get<Cultivo>(`${this.baseUrl}/${id}`);
//   }
// }

// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { Cultivo } from '../model/cultivo.model';

// @Injectable({
//   providedIn: 'root'
// })
// export class CultivoService {
//   private apiUrl = 'http://localhost:8080/api/cultivos';

//   constructor(private http: HttpClient) {}

//   getCultivos(): Observable<Cultivo[]> {
//     return this.http.get<Cultivo[]>(this.apiUrl);
//   }

//   getCultivosByUsuario(usuarioId: number): Observable<Cultivo[]> {
//     return this.http.get<Cultivo[]>(`${this.apiUrl}/usuario/${usuarioId}`);
//   }
// }
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cultivo } from '../model/cultivo.model';

@Injectable({
  providedIn: 'root'
})
export class CultivoService {

  private apiUrl = 'http://localhost:8080/api/cultivos'; 

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Obtiene el token del Local Storage
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  getCultivos(): Observable<Cultivo[]> {
    return this.http.get<Cultivo[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }

  getCultivoById(id: number): Observable<Cultivo> {
    return this.http.get<Cultivo>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }
}

