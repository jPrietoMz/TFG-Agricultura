import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { CultivoService } from '../service/cultivo.service';
import { Cultivo } from '../model/cultivo.model';

@Injectable({
  providedIn: 'root'
})
export class CultivoResolver implements Resolve<Cultivo[]> {
  constructor(private cultivoService: CultivoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cultivo[]> {
    return this.cultivoService.getCultivos();
  }
}
