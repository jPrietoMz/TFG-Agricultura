import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CosechaService } from '../../../service/cosecha.service';
import { Cosecha } from '../../../model/cosecha.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';

@Component({
  selector: 'app-cosecha-form',
  standalone: true,
  templateUrl: './cosecha-form.component.html',
  styleUrls: ['./cosecha-form.component.css'],
  imports: [CommonModule, FormsModule, BackButtonComponent]
})
export class CosechaFormComponent {
  nuevaCosecha: Cosecha = {
    id: 0,
    kilosObtenidos: 0,
    cultivoId: 0,
    precioObtenido: 0,
    fechaInicio: '',
    fechaFin: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cosechaService: CosechaService
  ) {}

  ngOnInit() {
    const cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
    if (!isNaN(cultivoId)) {
      this.nuevaCosecha.cultivoId = cultivoId;
    }
  }

  guardarCosecha() {
    this.cosechaService.addCosecha(this.nuevaCosecha).subscribe(() => {
      this.router.navigate(['/cultivos', this.nuevaCosecha.cultivoId, 'cosechas']);
    });
  }
}
