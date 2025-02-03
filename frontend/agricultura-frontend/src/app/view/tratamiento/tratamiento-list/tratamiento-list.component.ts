import { Component, OnInit } from '@angular/core';
import { TratamientoService } from '../../../service/tratamiento.service';
import { Tratamiento } from '../../../model/tratamiento.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tratamiento-list',
  standalone: true,
  templateUrl: './tratamiento-list.component.html',
  styleUrls: ['./tratamiento-list.component.css'],
  imports: [CommonModule]
})
export class TratamientoListComponent implements OnInit {
  tratamientos: Tratamiento[] = [];

  constructor(private tratamientoService: TratamientoService) {}

  ngOnInit() {
    this.tratamientoService.getTratamientos().subscribe((data) => {
      this.tratamientos = data;
    });
  }
}
