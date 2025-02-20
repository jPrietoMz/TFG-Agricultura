import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tratamiento } from '../model/tratamiento.model';

@Injectable({
  providedIn: 'root'
})
export class TratamientoService {

  private apiUrl = 'http://localhost:8080/api/tratamientos';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token')?.trim();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  getTratamientosByCultivo(): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(`${this.apiUrl}/mis-tratamientos`, { headers: this.getAuthHeaders() });
  }

  addTratamiento(tratamiento: Tratamiento): Observable<Tratamiento> {
    if (!tratamiento.cultivoId || tratamiento.cultivoId === 0) {
      console.error(" Error: `cultivoId` es inválido en `addTratamiento`:", tratamiento);
      alert(" Error: No se ha seleccionado un cultivo válido.");
      return new Observable<Tratamiento>();
    }
  
    console.log(" Enviando petición POST a:", `${this.apiUrl}/cultivo/${tratamiento.cultivoId}`);
    console.log(" Datos enviados:", tratamiento);
  
    return this.http.post<Tratamiento>(
      `${this.apiUrl}/cultivo/${tratamiento.cultivoId}`, //  Coincide con el backend
      tratamiento, 
      { headers: this.getAuthHeaders() }
    );
  }
  
  obtenerTratamientos(): Observable<Tratamiento[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  eliminarTratamiento(id: number): Observable<void> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers });
  }

  private obtenerHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Recupera el token almacenado
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
  
}
