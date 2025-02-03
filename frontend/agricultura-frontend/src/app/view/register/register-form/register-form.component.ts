import { Component } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Importar FormsModule
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  standalone: true,  // Indica que es un standalone component
  imports: [CommonModule, FormsModule] // Agregar FormsModule aquí

})
export class RegisterComponent {
  registerData = { username: '', password: '' };
  message: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.register(this.registerData).subscribe({
      next: () => {
        this.message = 'Registro exitoso. Ahora puedes iniciar sesión.';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: () => {
        this.message = 'Error en el registro. Inténtalo de nuevo.';
      }
    });
  }
}
