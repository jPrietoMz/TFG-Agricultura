import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  standalone: true,
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  imports: [FormsModule],
})
export class LoginFormComponent {
  loginData = { username: '', password: '' };

  onLogin() {
    console.log('Login con:', this.loginData);
    // Aquí iría la conexión con el backend
  }
}
