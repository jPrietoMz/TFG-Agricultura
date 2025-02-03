import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cultivo } from '../model/cultivo.model';

@Injectable({
  providedIn: 'root'
})
export class CultivoService {
  private apiUrl = 'http://localhost:8080/api/cultivos';

  constructor(private http: HttpClient) {}

  getCultivos(): Observable<Cultivo[]> {
    return this.http.get<Cultivo[]>(this.apiUrl);
  }

  getCultivosByUsuario(usuarioId: number): Observable<Cultivo[]> {
    return this.http.get<Cultivo[]>(`${this.apiUrl}/usuario/${usuarioId}`);
  }
}
