import { Component } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';  // ✅ Importa FormsModule correctamente

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  standalone: true,  // ✅ Standalone component
  imports: [FormsModule,
            RouterModule,
  ]  // ✅ Forma correcta de importar módulos en standalone components
})
export class LoginFormComponent {
  loginData = { username: '', password: '' };

  constructor(
    private authService: AuthService, 
    private router: Router
  ) {}

  onLogin() {
    this.authService.login(this.loginData.username, this.loginData.password)
      .subscribe({
        next: (response) => {
          console.log('Login exitoso:', response);
          this.authService.saveToken(response.token);
          alert('Inicio de sesión exitoso');
          this.router.navigate(['/inicio']);
        },
        error: (error) => {
          console.error('Error al iniciar sesión:', error);
          alert('Error al iniciar sesión: Credenciales incorrectas');
        }
      });
  }
}
