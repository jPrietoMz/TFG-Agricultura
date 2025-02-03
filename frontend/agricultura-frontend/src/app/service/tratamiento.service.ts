import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tratamiento } from '../model/tratamiento.model';

@Injectable({
  providedIn: 'root'
})
export class TratamientoService {
  private apiUrl = 'http://localhost:8080/api/tratamientos';

  constructor(private http: HttpClient) {}

  getTratamientosByCultivo(cultivoId: number): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(`${this.apiUrl}/cultivo/${cultivoId}`);
  }

  getTratamientos(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
