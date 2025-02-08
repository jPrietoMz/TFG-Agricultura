import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cosecha } from '../model/cosecha.model';

@Injectable({
  providedIn: 'root'
})
export class CosechaService {

  private apiUrl = 'http://localhost:8080/api/cosechas'; 

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  getCosechasByCultivo(cultivoId: number): Observable<Cosecha[]> {
    return this.http.get<Cosecha[]>(`${this.apiUrl}/cultivo/${cultivoId}`, { headers: this.getAuthHeaders() });
  }

  addCosecha(cosecha: Cosecha): Observable<Cosecha> {
    return this.http.post<Cosecha>(
      `${this.apiUrl}/cultivo/${cosecha.cultivoId}`, // 🔴 Asegurar que el campo sea `cultivo_id`
      cosecha,
      { headers: this.getAuthHeaders() } // 🔴 Añadir headers con el token
    );
  }
}


// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { Cosecha } from '../model/cosecha.model';

// @Injectable({
//   providedIn: 'root'
// })
// export class CosechaService {
//   private apiUrl = 'http://localhost:8080/api/cosechas';

//   constructor(private http: HttpClient) {}

//   getCosechasByCultivo(cultivoId: number): Observable<Cosecha[]> {
//     return this.http.get<Cosecha[]>(`${this.apiUrl}/cultivo/${cultivoId}`);
//   }

//   getCosechas(): Observable<any> {
//     return this.http.get<any>(this.apiUrl);
//   }
// }
