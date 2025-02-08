import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CultivoService } from '../../../service/cultivo.service';
import { Cultivo } from '../../../model/cultivo.model';
import { RouterModule } from '@angular/router'; // 📌 Importar RouterModule

@Component({
  selector: 'app-cultivo-detail',
  templateUrl: './cultivo-detail.component.html',
  styleUrls: ['./cultivo-detail.component.css'],
  standalone: true,
  imports: [RouterModule] // 📌 Asegurar que RouterModule esté disponible
})
export class CultivoDetailComponent implements OnInit {
  cultivoId!: number;
  cultivo!: Cultivo;

  constructor(
    private route: ActivatedRoute,
    private cultivoService: CultivoService
  ) {}

  ngOnInit() {
    this.cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
    this.cultivoService.getCultivoById(this.cultivoId).subscribe((data) => {
      this.cultivo = data;
    console.log('Cultivo seleccionado:', this.cultivoId);
    });
  }
}
