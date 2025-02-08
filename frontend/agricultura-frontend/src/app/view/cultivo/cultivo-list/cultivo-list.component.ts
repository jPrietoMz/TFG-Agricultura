import { Component, OnInit } from '@angular/core';
import { CultivoService } from '../../../service/cultivo.service';
import { Cultivo } from '../../../model/cultivo.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cultivo-list',
  standalone: true,
  templateUrl: './cultivo-list.component.html',
  styleUrls: ['./cultivo-list.component.css'],
  imports: [CommonModule,
            RouterModule,
  ]
})
export class CultivoListComponent implements OnInit {
  cultivos: Cultivo[] = [];

  constructor(private cultivoService: CultivoService) {}

  ngOnInit() {
    this.cultivoService.getCultivos().subscribe((data) => {
      this.cultivos = data;
    });
  }
  
}
