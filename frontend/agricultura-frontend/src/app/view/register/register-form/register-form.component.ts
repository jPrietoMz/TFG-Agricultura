import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  standalone: true,
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  imports: [FormsModule]
})
export class RegisterFormComponent {
  registerData = { username: '', password: '' };

  onRegister() {
    console.log('Registro con:', this.registerData);
    // Aquí iría la conexión con el backend
  }
}
