import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/login'; // Ruta del backend

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password });
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token); // Guardar el token en localStorage
  }

  getToken(): string | null {
    return localStorage.getItem('token'); // Obtener el token almacenado
  }

  logout(): void {
    localStorage.removeItem('token'); // Eliminar el token al cerrar sesión
  }
}
