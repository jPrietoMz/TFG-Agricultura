import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TratamientoService } from '../../../service/tratamiento.service';
import { Tratamiento } from '../../../model/tratamiento.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';

@Component({
  selector: 'app-tratamiento-form',
  standalone: true,
  templateUrl: './tratamiento-form.component.html',
  styleUrls: ['./tratamiento-form.component.css'],
  imports: [CommonModule, FormsModule, BackButtonComponent]
})
export class TratamientoFormComponent {
  nuevoTratamiento: Tratamiento = new Tratamiento(0, 0, '', '', 0, '', '', '');

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tratamientoService: TratamientoService
  ) {}

  ngOnInit() {
    const cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
    if (!isNaN(cultivoId)) {
      this.nuevoTratamiento.cultivoId = cultivoId;
    }
  }

  guardarTratamiento() {
    this.tratamientoService.addTratamiento(this.nuevoTratamiento).subscribe(() => {
      this.router.navigate(['/cultivos', this.nuevoTratamiento.cultivoId, 'tratamientos']);
    });
  }
}
