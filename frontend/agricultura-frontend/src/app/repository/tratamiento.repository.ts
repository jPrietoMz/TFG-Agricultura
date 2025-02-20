import { Injectable } from '@angular/core';
import { TratamientoService } from '../service/tratamiento.service';
import { Tratamiento } from '../model/tratamiento.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TratamientoRepository {
  constructor(private tratamientoService: TratamientoService) {}

  getTratamientosByCultivo(): Observable<Tratamiento[]> { 
    return this.tratamientoService.getTratamientosByCultivo(); 
  }
}
