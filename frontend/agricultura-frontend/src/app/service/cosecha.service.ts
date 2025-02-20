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

  getCosechasByCultivo(): Observable<Cosecha[]> {
    return this.http.get<Cosecha[]>(`${this.apiUrl}/mis-cosechas`, { headers: this.getAuthHeaders() });
  }
  
  agregarCosecha(cosecha: Cosecha): Observable<Cosecha> {
    if (!cosecha.cultivoId || cosecha.cultivoId === 0) {
      console.error("Error: `cultivoId` es inválido en `agregarCosecha`:", cosecha);
      return new Observable<Cosecha>();
    }

    return this.http.post<Cosecha>(
      `${this.apiUrl}/cultivo/${cosecha.cultivoId}`, 
      cosecha, 
      { headers: this.getAuthHeaders() }
    );
  }

  eliminarCosecha(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

  private obtenerHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Recupera el token almacenado
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
}