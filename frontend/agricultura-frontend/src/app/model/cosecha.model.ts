export class Cosecha {
    id: number;
    fecha: string;
    kilos_obtenidos: number;
    cultivoId: number;
    precio_obtenido: number;
    fecha_inicio: string;
    fecha_fin: string;

  
    constructor(id: number, fecha: string, kilos_obtenidos: number, cultivoId: number, precio_obtenido: number, fecha_inicio: string, fecha_fin: string) {
      this.id = id;
      this.fecha = fecha;
      this.kilos_obtenidos = kilos_obtenidos;
      this.cultivoId = cultivoId;
      this.precio_obtenido = precio_obtenido;
      this.fecha_inicio = fecha_inicio;
      this.fecha_fin = fecha_fin;
    }
  }
  