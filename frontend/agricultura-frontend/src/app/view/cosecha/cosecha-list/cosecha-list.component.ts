import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CosechaService } from '../../../service/cosecha.service';
import { Cosecha } from '../../../model/cosecha.model';
import { CommonModule } from '@angular/common'; // 🔥 Importa CommonModule

@Component({
  selector: 'app-cosecha-list',
  standalone: true,
  templateUrl: './cosecha-list.component.html',
  styleUrls: ['./cosecha-list.component.css'],
  imports: [CommonModule] // 🔥 Agrégalo aquí
})
export class CosechaListComponent implements OnInit {
  cosechas: Cosecha[] = [];

  constructor(
    private route: ActivatedRoute,
    private cosechaService: CosechaService
  ) {}

  ngOnInit() {
    const cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
    if (!isNaN(cultivoId)) {
      this.cosechaService.getCosechasByCultivo(cultivoId).subscribe({
        next: (data) => {
          this.cosechas = data;
        },
        error: (err) => {
          console.error('Error obteniendo cosechas:', err);
        }
      });
    }
  }
}

