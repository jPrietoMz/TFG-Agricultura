import { Component } from '@angular/core';
import { AuthService } from '../../../service/auth.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent {
  constructor(private authService: AuthService) {}

  onSubmit(form: any) {
    const { username, password } = form;
    this.authService.login(username, password).subscribe(
      response => console.log('Login exitoso', response),
      error => console.error('Error en el login', error)
    );
  }
}
