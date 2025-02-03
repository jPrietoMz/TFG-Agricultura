import { Component, OnInit } from '@angular/core';
import { CultivoService } from '../../service/cultivo.service';

@Component({
  selector: 'app-cultivo',
  templateUrl: './cultivo.component.html',
  styleUrls: ['./cultivo.component.css']
})
export class CultivoComponent implements OnInit {
  cultivos: any[] = [];

  constructor(private cultivoService: CultivoService) {}

  ngOnInit() {
    this.cultivoService.getCultivos().subscribe({
      next: (data) => (this.cultivos = data),
      error: (err) => console.error(err)
    });
  }
}
