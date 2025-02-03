import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cosecha } from '../model/cosecha.model';

@Injectable({
  providedIn: 'root'
})
export class CosechaService {
  private apiUrl = 'http://localhost:8080/api/cosechas';

  constructor(private http: HttpClient) {}

  getCosechasByCultivo(cultivoId: number): Observable<Cosecha[]> {
    return this.http.get<Cosecha[]>(`${this.apiUrl}/cultivo/${cultivoId}`);
  }

  getCosechas(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
