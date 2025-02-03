import { Component, OnInit } from '@angular/core';
import { CosechaService } from '../../../service/cosecha.service';
import { Cosecha } from '../../../model/cosecha.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cosecha-list',
  standalone: true,
  templateUrl: './cosecha-list.component.html',
  styleUrls: ['./cosecha-list.component.css'],
  imports: [CommonModule]
})
export class CosechaListComponent implements OnInit {
  cosechas: Cosecha[] = [];

  constructor(private cosechaService: CosechaService) {}

  ngOnInit() {
    this.cosechaService.getCosechas().subscribe((data) => {
      this.cosechas = data;
    });
  }
}
