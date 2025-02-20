export class Cosecha {
    id: number;
    kilosObtenidos: number;
    cultivoId: number;
    precioObtenido: number;
    fechaInicio : string;
    fechaFin : string;
    usuarioId?: number; 

  
    constructor(id: number, kilosObtenidos: number, cultivoId: number, precioObtenido: number, fechaInicio: string, fechaFin: string) {
      this.id = id;
      this.kilosObtenidos = kilosObtenidos;
      this.cultivoId = cultivoId;
      this.precioObtenido = precioObtenido;
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
    }
  }
  