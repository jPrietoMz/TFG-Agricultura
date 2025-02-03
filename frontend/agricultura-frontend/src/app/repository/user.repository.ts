import { Injectable } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserRepository {
  constructor(private userService: UserService) {}

  register(user: User): Observable<string> {
    return this.userService.register(user);
  }

  login(username: string, password: string): Observable<string> {
    return this.userService.login(username, password);
  }
}
