import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; // Ajusta la URL según el backend

  constructor(private http: HttpClient) {}

  register(user: User): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/register`, user);
  }

  login(username: string, password: string): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, { username, password });
  }
}
